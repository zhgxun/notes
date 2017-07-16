// 统计目录中内容，类似du命令
package main

import (
	"flag"
	"fmt"
	"io/ioutil"
	"os"
	"path/filepath"
	"sync"
	"time"
)

// 调用时提供 -v 参数时显示程序进度
var verbose = flag.Bool("v", false, "show verbose progress messages")

func main() {
	flag.Parse()
	roots := flag.Args()
	if len(roots) == 0 {
		roots = []string{"."}
	}

	fileSizes := make(chan int64)

	// 为了知道最后一个goroutine什么时候结束(最后一个结束并不一定是最后一个开始)，
	// 我们需要一个递增的计数器，在每一个goroutine启动时加一，在goroutine退出时减一。
	// 这需要一种特殊的计数器，这个计数器需要在多个goroutine操作时做到安全并且提供在其
	// 减为零之前一直等待的一种方法。这种计数类型被称为sync.WaitGroup
	var n sync.WaitGroup

	for _, root := range roots {
		// Add是为计数器加一，必须在worker goroutine开始之前调用，而不是在goroutine中；
		// 否则的话我们没办法确定Add是在"closer" goroutine调用Wait之前被调用。
		// 并且Add还有一个参数，但Done却没有任何参数；其实它和Add(-1)是等价的。
		// 我们使用defer来确保计数器即使是在出错的情况下依然能够正确地被减掉。
		n.Add(1)
		go walkDir(root, &n, fileSizes)
	}
	go func() {
		n.Wait()
		close(fileSizes)
	}()

	// 显示处理结果
	// 主goroutine现在使用了计时器来每500ms生成事件， 然后用select语句来等待文件大小的消息来更新总大小数据，
	// 或者一个计时器的事件来打印当前的总大小数据。
	// 如果-v的flag在运行时没有传入的话，tick这个channel会保持为nil， 这样在select里的case也就相当于被禁用了。
	var tick <-chan time.Time
	if *verbose {
		tick = time.Tick(500 * time.Millisecond)
	}
	var nFiles, nBytes int64

	// 第一个select的case必须显式地判断fileSizes的channel是不是已经被关闭了， 这里可以用到channel接收的二值形式。
	// 如果channel已经被关闭了的话，程序会直接退出循环。
	// 这里的break语句用到了标签break，这样可以同时终结select和for两个循环；
	// 如果没有用标签就break的话只会退出内层的select循环，而外层的for循环会使之进入下一轮select循环。
loop:
	for {
		select {
		case size, ok := <-fileSizes:
			if !ok {
				break loop
			}
			nFiles++
			nBytes += size
		case <-tick:
			fmt.Printf("%d files  %.1f GB\n", nFiles, float64(nBytes)/1e9)
		}
	}
}

// 递归处理目录内容
func walkDir(dir string, n *sync.WaitGroup, fileSizes chan<- int64) {
	defer n.Done()

	for _, entry := range dirContents(dir) {
		if entry.IsDir() {
			n.Add(1)
			go walkDir(filepath.Join(dir, entry.Name()), n, fileSizes)
		} else {
			fileSizes <- entry.Size()
		}
	}
}

var ema = make(chan struct{}, 20)

// 遍历当前目录下的所有文件和目录，返回每一个文件或目录的信息
// 由于这个程序在高峰期会创建成百上千的goroutine，需要用计数信号量来阻止他同时打开太多的文件
func dirContents(dir string) []os.FileInfo {
	ema <- struct{}{}
	defer func() { <-ema }()

	entries, err := ioutil.ReadDir(dir)
	if err != nil {
		fmt.Fprintf(os.Stderr, "du like: %s\n", err.Error())
		return nil
	}

	return entries
}

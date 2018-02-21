package util

import (
	"encoding/binary"
	"fmt"
	"io"
	"math/rand"
	"sort"
	"time"
)

var startTime time.Time

func init() {
	startTime = time.Now()
}

// 往通道写入可变参数的整数
func ArrSort(a ...int) <-chan int {
	out := make(chan int)

	go func() {
		for _, v := range a {
			out <- v
		}
		close(out)
	}()

	return out
}

// 按升序排序
func InMemSort(in <-chan int) <-chan int {
	out := make(chan int, 4096)

	// 启动一个协程输出排序数据
	go func() {
		var a []int
		for v := range in {
			a = append(a, v)
		}
		fmt.Println("Read done: ", time.Now().Sub(startTime))

		sort.Ints(a)
		fmt.Println("Sort done: ", time.Now().Sub(startTime))

		// 将排序好的数据输出至通道中
		for _, n := range a {
			out <- n
		}

		close(out)
	}()

	return out
}

// 按升序合并两个通道中数据
func Merge(in1, in2 <-chan int) <-chan int {
	out := make(chan int, 4096)

	go func() {
		v1, ok1 := <-in1
		v2, ok2 := <-in2
		for ok1 || ok2 {
			if !ok2 || (ok1 && v1 <= v2) {
				out <- v1
				v1, ok1 = <-in1
			} else {
				out <- v2
				v2, ok2 = <-in2
			}
		}

		close(out)
		fmt.Println("Merge done: ", time.Now().Sub(startTime))
	}()

	return out
}

// 递归进行两两归并
func MergeN(ins ...<-chan int) <-chan int {
	// 只有一个通道时直接返回
	if len(ins) == 1 {
		return ins[0]
	}

	// 取中点
	m := len(ins) / 2

	// 递归归并
	return Merge(MergeN(ins[:m]...), MergeN(ins[m:]...))
}

// 从可读位置读入数据
// 读入指定长度的数据, 为-1时一次性全部读完
func ReadSource(reader io.Reader, chunkSize int) <-chan int {
	out := make(chan int, 4096)

	go func() {
		buffer := make([]byte, 8)
		size := 0
		for {
			n, err := reader.Read(buffer)
			size += n
			if n > 0 {
				v := int(binary.BigEndian.Uint64(buffer))
				out <- v
			}
			if err != nil || (chunkSize != -1 && size >= chunkSize) {
				break
			}
		}

		close(out)
	}()

	return out
}

// 向可写输入写入数据
func WriteSink(writer io.Writer, in <-chan int) {
	for m := range in {
		buffer := make([]byte, 8)
		binary.BigEndian.PutUint64(buffer, uint64(m))
		writer.Write(buffer)
	}
}

// 生成指定数量的随机整数
func RandomSource(count int) <-chan int {
	out := make(chan int)

	go func() {
		for i := 0; i < count; i++ {
			out <- rand.Int()
		}

		close(out)
	}()

	return out
}

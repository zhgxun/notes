// 第一个goroutine是一个计数器, 用于生成0,1.2,...形式的整数序列,然后通过channel将该整数序列发送给第二个goroutine
// 第二个goroutine是一个求平方的程序, 对收到的每个整数求平方, 然后将平方后的结果通过第二个channel发送给第三个goroutine
// 第三个goroutine是一个打印程序, 打印收到的每个整数
// 类型chan<- int表示一个只发送int的channel, 只能发送不能接收
// 相反，类型<-chan int表示一个只接收int的channel, 只能接收不能发送
package main

import "fmt"

func main() {
	naturals := make(chan int)
	squares := make(chan int)
	// chan int类型的naturals隐式地转换为chan<- int类型只发送型的channel
	// 任何双向channel向单向channel变量的赋值操作都将导致该隐式转换
	go counter(naturals)
	go squarer(squares, naturals)
	printer(squares)
}

// 批量生成数字序列
// out 只发送不接收的通道
// 生成完毕后关闭通道
func counter(out chan<- int) {
	for x := 0; x < 100; x++ {
		out <- x
	}
	close(out)
}

// 对数字序列计算平方
// out 只发送不接收的通道
// in 只接收不发送的通道
func squarer(out chan<- int, in <-chan int) {
	for v := range in {
		out <- v * v
	}
	close(out)
}

// 打印通道中的元素
// in 只接收不发送的通道
func printer(in <-chan int) {
	for v := range in {
		fmt.Println(v)
	}
}

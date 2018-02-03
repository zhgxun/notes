package util

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

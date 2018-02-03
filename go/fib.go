package main

import "fmt"

func fibonacci(n int) int {
	if n < 2 {
		return n
	}
	return fibonacci(n-2) + fibonacci(n-1)
}

// 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233，377，610，987，1597，2584，4181，6765，10946，17711，28657
func main() {
	//fmt.Println(fibonacci(3))
	//fmt.Println(tail(3))
	tail(5)
}

func tail(n int64) (result int64, next int64) {
	if n == 0 {
		result, next = 0, 0
		return
	}
	if n == 1 {
		result, next = 1, 0
		return
	}
	result, next = tail(n - 1)
	fmt.Println(result, next)
	tmp := result
	result += next
	next = tmp
	return
}

package main

import (
	"fmt"
)

func main() {
	arr := []int{6, 1, 3, 5}
	fmt.Println(selectSort(arr))
	fmt.Println(bubble(arr))
}

// 选择排序
func selectSort(arr []int) []int {
	length := len(arr)
	if length <= 1 {
		return arr
	}
	
	i := 0
	min := 0
	for i = 0; i < length - 1; i++ {
		min = i
		for j := 0; j < length - i; j++ {
			if arr[min] > arr[j] {
				min = j
			}
		}
	}
	if i != min {
		temp := arr[min]
		arr[min] = arr[i]
		arr[i] = temp
	}
	return arr
}

// 冒泡排序
func bubble(arr []int) []int {
	length := len(arr)
	if length <= 1 {
		return arr
	}
	
	for i := 0; i < length; i++ {
		for j := 0; j < length; j++ {
			if arr[i] > arr[j] {
				temp := arr[j]
				arr[j] = arr[i]
				arr[i] = temp
			}
		}
	}
	
	return arr
}

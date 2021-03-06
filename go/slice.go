// 在函数间传递数组和在函数间传递切片的比较
package main

// 根据内存和性能来看，在函数间传递数组是一个开销很大的操作。在函数之间传递变量时，总是以值的方式传递的。如果这个变量是个数组，意味着整个数组，不管有多长，都会完整复制，并传递给函数。
// 为了考察这个操作，我们来创建一个包含100万个int类型元素的数组。在64位架构上，这将需要800万字节，即8M的内存。如果声明了这种大小的数组，并将其传递给函数，会发生什么？
func main() {
	// 声明一个需要8M的数组
	var array [1e6]int
	// 直接传递数组
	foo(array)
	// 传递指向数组的指针
	bar(&array)
	
	// 两种方式明显第二种更好且更有效，这次函数bar接受一个100万个整型值的数组的指针。现在将数组的地址传入函数，只需要在栈上分配8字节的内存给指针就可以了。
	// 这个操作会更有效地利用内存，性能也更好。不过要意识到，因为现在传递的是指针，所以如果改变指针指向的值，会改变共享的内存。
	
	// 切片是一种数据结构，这种数据结构便于使用和管理数据集合。切片是围绕动态数组的概念构建的，可以按需自动增长和缩小。切片的动态增长是通过内置函数append来实现的。这个函数可以快速且高效地增长切片。还可以通过对切片再次切片来缩小一个切片的大小。因为切片的底层内存也是在连续块中分配的，所以切片还能获得索引、迭代以及为垃圾回收优化的好处。
	
	// 在函数间传递切片就是要在函数间以值的方式传递切片。由于切片的尺寸很小，在函数间复制和传递切片成本也很低。
	// 创建一个100万个整型值的切片
	slice := make([]int, 1e6)
	
	// 在函数间传递24字节的数据会非常快速、简单。这也是切片效率高的地方。不需要传递指针和处理复杂的语法，只需要复制切片，按想要的方式修改数据，然后传递回一份新的切片副本。
	test(slice)
	
	
}

// 给函数传递一个数组
// 每次foo被调用时，必须在栈上分配8M的内存。之后，整个数组的值（8M的内存）被复制到刚分配的内存里。
func foo(array [1e6]int) [1e6]int {
	// do some things
	return array
}

// 虽然Go语言自己会处理这个复制操作，不过还有一种更好且更有效的方法来处理这个操作。可以只传入指向数组的指针，这样只需要复制8字节的数组而不是8M的内存数据到栈上。
func bar(array *[1e6]int) *[1e6]int {
	// do some things
	return array
}

// 给函数传递一个切片
// 在64位架构的机器上，一个切片需要24字节的内存：指针字段需要8字节，长度和容量字段分别需要8字节。由于与切片关联的数据包含在底层数组里，不属于切片本身，所以将切片复制到任意函数的时候，对底层数组大小都不会有影响。复制时只会复制切片本身，不会涉及底层数组。
func test(slice []int) []int {
	// do some things
	return slice
}

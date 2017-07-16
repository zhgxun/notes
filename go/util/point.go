// 距离
package util

import "math"

// 点
type Point struct {
	X, Y float64
}

// Distance 两点间的距离
// 由于方法和字段都是在同一命名空间, 所以如果我们在这里声明一个X方法的话, 编译器会报错, 因为在调用p.X时会有歧义
func (p Point) Distance(q Point) float64 {
	return math.Hypot(q.X - p.X, q.Y - p.Y)
}

// 线段, 即是点的集合
type Path []Point

// 线段的长度
// 对于一个给定的类型, 其内部的方法都必须有唯一的方法名, 但是不同的类型却可以有同样的方法名,
// 比如我们这里Point和Path就都有Distance这个名字的方法;
// 所以我们没有必要非在方法名之前加类型名来消除歧义, 比如PathDistance。
func (path Path) Distance() float64 {
	sum := 0.0
	for i := range path {
		// 第一个点(i = 0)无法确定一条线段, 等待第二个点开始计算线段的长度
		if i > 0 {
			sum += path[i].Distance(path[i - 1])
		}
	}
	return sum
}

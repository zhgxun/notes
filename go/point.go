package main

import (
	"notes/go/util"
	"fmt"
)

func main() {
	// 注意定义时需要显示指定字段, 虽然按照顺序赋值不影响, 当容易引起混淆
	p := util.Point{X:1, Y:2}
	q := util.Point{X:4, Y:6}
	fmt.Println(p.Distance(q))
	
	// 计算三角形的周长, 确定一个三角形, 需要定义第一个点和最后一个点重复
	// 两边之长大于第三边才算是三角形
	perimeter := util.Path{
		{X:1, Y:1},
		{X:5, Y:1},
		{X:5, Y:4},
		{X:1, Y:1},
	}
	fmt.Println(perimeter.Distance())
	
	// 排序播放列表
	util.Sort()
	
	// 颠倒的排序
	util.Reverse()
}

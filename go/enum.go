package main

import "fmt"

const (
	j = iota
	a
	b
	c
	d
	e
	f
	g
	h
)

const x = iota

func main() {
	fmt.Println(a)
	fmt.Println(b)
	fmt.Println(c)
	fmt.Println(d)
	fmt.Println(e)
	fmt.Println(f)
	fmt.Println(g)
	fmt.Println(h)
	fmt.Println(j)
	fmt.Println(x)
}

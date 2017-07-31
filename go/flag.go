// flag包实现了命令行参数的解析
package main

import (
	"flag"
	"fmt"
)

func main() {
	// 获取一个参数, 其中number是命令行中使用的, 比如go run flag.go --number=2 可以使用1个或2个 '-' 号
	num := flag.Int("number", 123, "input a int")
	// 在所有flag都注册之后，调用 flag.Parse() 来解析命令行参数写入注册到 flag 里
	flag.Parse()
	fmt.Println(*num)
}

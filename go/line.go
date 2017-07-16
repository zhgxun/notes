// go语言打印错误发生时输出文件名和行号
package main

import (
	"runtime"
	"fmt"
)

// runtime包提供和go运行时环境的互操作，如控制go程的函数。
// 有时候发生运行时错误时很难定位到具体错误的位置, 比如这个报错
/*
2017/05/27 12:27:39 sql: Scan error on column index 7: converting driver.Value type []uint8 ("2016-01-13") to a int64: invalid syntax
panic: sql: Scan error on column index 7: converting driver.Value type []uint8 ("2016-01-13") to a int64: invalid syntax

goroutine 1 [running]:
log.Panic(0xc420065bf8, 0x1, 0x1)
/usr/local/go/src/log/log.go:322 +0xc0
finance/common.Info(0x6dc5a0, 0xc4200cc1b0)
/Users/zhgxun/go/src/finance/common/check.go:13 +0xc8
finance/settlement.Fix()
/Users/zhgxun/go/src/finance/settlement/fix_rma_settle.go:114 +0x3d9
finance/settlement.Export(0x946, 0x1, 0x0, 0x0, 0x0)
/Users/zhgxun/go/src/finance/settlement/pop.go:134 +0x2d4
main.main()
/Users/zhgxun/go/src/finance/main/main.go:21 +0x174
*/
// 你除了知道是哪里错了之外, 其实你是不能定位该错误发生的位置的。
// 所以调试阶段就只能增加运行时判断了。
func main() {
	// func Caller(skip int) (pc uintptr, file string, line int, ok bool)
	// Caller报告当前go程调用栈所执行的函数的文件和行号信息。实参skip为上溯的栈帧数，
	// 0表示Caller的调用者（Caller所在的调用栈）。（由于历史原因，skip的意思在Caller和Callers中并不相同。）
	// 函数的返回值为调用栈标识符、文件名、该调用在文件中的行号。如果无法获得信息，ok会被设为false。
	pc, file, line, ok := runtime.Caller(0)
	fmt.Println(pc, file, line, ok)
}

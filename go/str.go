// 字符串包
package main

import (
	"fmt"
	"strings"
)

func main() {
	// 判断两个utf-8编码字符串（将unicode大写、小写、标题三种格式字符视为相同）是否相同
	//fmt.Println(strings.EqualFold("go", "GO"))
	// 判断s是否有前缀字符串prefix
	//fmt.Println(strings.HasPrefix("MyName", "M"))
	// 判断s是否有后缀字符串suffix
	//fmt.Println(strings.HasSuffix("MyName", "ame"))
	// 判断字符串s是否包含子串substr
	//fmt.Println(strings.Contains("MyName", "y"))
	//fmt.Println(strings.Contains("MyName", "foo"))
	//fmt.Println(strings.Contains("MyName", "name"))
	//fmt.Println(strings.Contains("MyName", ""))
	//fmt.Println(strings.Contains("MyName", " "))
	//fmt.Println(strings.Contains("", ""))
	//fmt.Println(strings.Contains("", " "))
	//fmt.Println(strings.Contains(" ", " "))
	//fmt.Println(strings.Contains(" ", ""))
	// 子串sep在字符串s中第一次出现的位置，不存在则返回-1
	//fmt.Println(strings.Index("MyName", "N1"))
	//f := func(c rune) bool {
	//	return unicode.Is(unicode.Han, c)
	//}
	//fmt.Println(strings.IndexFunc("Hello, 世界", f))
	//fmt.Println(strings.IndexFunc("Hello, world", f))
	//fmt.Println(strings.ToUpper("Gopher"))
	//fmt.Println("ba" + strings.Repeat("na", 2))
	// 返回将s中前n个不重叠old子串都替换为new的新字符串，如果n<0会替换所有old子串
	//fmt.Println(strings.Replace("oink oink oink", "k", "ky", 2))
	//fmt.Println(strings.Replace("oink oink oink", "oink", "moo", -1))
	// 用去掉s中出现的sep的方式进行分割，会分割到结尾，并返回生成的所有片段组成的切片（每一个sep都会进行一次切割，
	// 即使两个sep相邻，也会进行两次切割）。如果sep为空字符，Split会将s切分成每一个unicode码值一个字符串
	fmt.Printf("%q\n", strings.Split("a,b,c", ","))
	fmt.Printf("%q\n", strings.Split("a man a plan a canal panama", "a "))
	fmt.Printf("%q\n", strings.Split(" xyz ", ""))
	fmt.Printf("%q\n", strings.Split("", "Bernardo O'Higgins"))
	// 将一系列字符串连接为一个字符串，之间用sep来分隔
	s := []string{"foo", "bar", "baz"}
	fmt.Println(strings.Join(s, ", "))

	var sn string
	rungIndex := strings.Index("419040169433050401-", "-")
	if rungIndex == -1 {
		cnCommaIndex := strings.Index("419040169433050401-", "，")
		if cnCommaIndex == -1 {
			enCommaIndex := strings.Index("419040169433050401-", ",")
			if enCommaIndex > 0 {
				sn = "419040169433050401-"[:enCommaIndex]
			}
		} else {
			sn = "419040169433050401-"[:cnCommaIndex]
		}
	} else {
		sn = "419040169433050401-"[:rungIndex]
	}
	fmt.Println("sn: ", sn)
}

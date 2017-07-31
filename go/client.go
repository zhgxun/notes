// 首先程序将用户的输入作为参数service传入net.ResolveTCPAddr获取一个tcpAddr,
// 然后把tcpAddr传入DialTCP后创建了一个TCP连接conn, 通过conn来发送请求信息,
// 最后通过ioutil.ReadAll从conn中读取全部的文本, 也就是服务端响应反馈的信息
package main

import (
	"fmt"
	"io/ioutil"
	"net"
	"os"
)

func main() {
	// 获取提供服务的服务器ip地址
	service := os.Args[1]
	// ResolveTCPAddr获取一个TCPAddr, 一个TCPAddr类型, 他表示一个TCP的地址信息
	tcpAddr, err := net.ResolveTCPAddr("tcp4", service)
	checkError(err)
	// 通过net包中的DialTCP函数来建立一个TCP连接, 并返回一个TCPConn类型的对象
	// 当连接建立时服务器端也创建一个同类型的对象, 此时客户端和服务器端通过各自拥
	// 有的TCPConn对象来进行数据交换
	//
	// 一般而言, 客户端通过TCPConn对象将请求信息, 发送到服务器端, 读取服务器端响应的信息
	// 服务器端读取并解析来自客户端的请求并返回应答信息, 这个连接只有当任一端关闭了连接之后才失效,
	// 不然这连接可以一直在使用。
	conn, err := net.DialTCP("tcp", nil, tcpAddr)
	checkError(err)
	// 向连接的服务器端写入信息
	_, err = conn.Write([]byte("HEAD / HTTP/1.0\r\n\r\n"))
	checkError(err)
	// 读取服务器端响应的全部内容
	result, err := ioutil.ReadAll(conn)
	checkError(err)
	// 输出获取到的内容
	fmt.Println(string(result))
	os.Exit(0)
}

func checkError(err error) {
	if err != nil {
		fmt.Fprintf(os.Stderr, "Fatal error: %s", err.Error())
		os.Exit(1)
	}
}

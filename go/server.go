// 在服务器端我们需要绑定服务到指定的非激活端口, 并监听此端口
// 当有客户端请求到达的时候可以接收到来自客户端连接的请求
package main

import (
	"fmt"
	"net"
	"os"
	"strings"
	"time"
)

func main() {
	// 监听的服务器端口
	service := "127.0.0.1:1200"
	tcpAddr, err := net.ResolveTCPAddr("tcp4", service)
	if err != nil {
		fmt.Fprintf(os.Stderr, "Fatal error: %s", err.Error())
		os.Exit(1)
	}
	// 在服务器端我们需要绑定服务到指定的非激活端口, 并监听此端口,
	// 当有客户端请求到达的时候可以接收到来自客户端连接的请求
	//
	// func ListenTCP(net string, laddr *TCPAddr) (*TCPListener, error)
	// ListenTCP在本地TCP地址laddr上声明并返回一个*TCPListener, net参数必须是"tcp", "tcp4", "tcp6",
	// 如果laddr的端口字段为0, 函数将选择一个当前可用的端口, 可以用Listener的Addr方法获得该端口
	listener, err := net.ListenTCP("tcp", tcpAddr)
	if err != nil {
		fmt.Fprintf(os.Stderr, "Fatal error: %s", err.Error())
		os.Exit(1)
	}
	for {
		// 接受每一种请求
		// func (l *TCPListener) Accept() (Conn, error)
		// Accept用于实现Listener接口的Accept方法
		// 他会等待下一个呼叫, 并返回一个该呼叫的Conn接口
		conn, err := listener.Accept()
		// 当有错误发生的情况下最好是由服务端记录错误, 然后当前连接的客户端直接报错而退出, 从而不会
		// 影响到当前服务端运行的整个服务
		if err != nil {
			continue
		}
		// 处理客户端的连接
		go handleClient(conn)
	}
}

func handleClient(conn net.Conn) {
	// 超时时间, 当一定时间内客户端无请求发送, conn便会自动关闭, 下面的for循环即会因为连接已关闭而跳出
	conn.SetReadDeadline(time.Now().Add(1 * time.Minute))
	// request在创建时需要指定一个最大长度以防止flood attack; 每次读取到请求处理完毕后,
	// 需要清理request, 因为conn.Read()会将新读取到的内容append到原内容之后
	// request 为提供的读取的最长缓冲区, 缓冲区满后会自动回写到客户端接收, 因此客户端需要注意接收到数据的完整性后在处理
	// 但是也并非都是写满后才返回给客户端, 有可能提前返回数据, 需要一个协议
	// 如果该缓冲区给的不够, 可能造成客户端传递过来的信息被截断, 无法得到预期的结果
	request := make([]byte, 9)
	defer conn.Close()
	for {
		// 不断读取客户端发来的请求, 由于我们需要保持与客户端的长连接, 所以不能在读取完一次请求后就关闭连接
		// 将读取到的数据追加保存到request中
		readLen, err := conn.Read(request)
		if err != nil {
			fmt.Println(err)
			break
		}

		// 如果没有读取到客户端任何信息, 则默认客户端已经关闭
		if readLen == 0 {
			break
		} else if strings.TrimSpace(string(request[:readLen])) == "timestamp" {
			date := time.Now().Format("2006-01-02 15:04:05")
			conn.Write([]byte(date))
		} else {
			conn.Write([]byte(request[:readLen]))
		}

		// 每次发送写完毕后都清除缓存空间, 防止追加
		request = make([]byte, 8)
	}
}

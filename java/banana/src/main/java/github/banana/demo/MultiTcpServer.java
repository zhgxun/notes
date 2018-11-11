package github.banana.demo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多线程
 * 
 * @author zhgxun
 *
 */
public class MultiTcpServer {

    /**
     * 多线程服务端
     * 
     * @param args
     */
    public static void main(String[] args) {
        try {
            // 启动Socket监听9000端口
            @SuppressWarnings("resource")
            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("Socket start...");
            // 服务器端使用无限循环监听客服端请求
            for (;;) {
                // 每次accept()返回后，创建新的线程来处理客户端请求
                Socket socket = serverSocket.accept();
                System.out.println("Accepting from to: " + socket.getRemoteSocketAddress());
                // 使用多线程处理类处理, 每个客户端请求对应一个服务线程
                TCPHandler handler = new TCPHandler(socket);
                // 启动多线程
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

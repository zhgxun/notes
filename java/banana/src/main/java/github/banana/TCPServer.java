package github.banana;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * TCP服务端
 * 
 * @author zhgxun
 *
 */
public class TCPServer {

    /**
     * 服务只能运行一次
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // 启动给一个Socket实例
        try (ServerSocket serverSocket = new ServerSocket(9000)) {
            System.out.println("TCP server Socket start...");
            // 接收请求
            try (Socket socket = serverSocket.accept()) {
                // 读取客服端请求
                try (BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {
                    // 写会数据到客户端
                    try (BufferedWriter bufferedWriter = new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {
                        // 读取一行
                        String line = bufferedReader.readLine();
                        // 如果读到客户端约定的字段则返回时间
                        if ("time".equals(line)) {
                            bufferedWriter.write(LocalDateTime.now().toString());
                            bufferedWriter.flush();
                        } else {
                            bufferedWriter.write("None, please request 'time'");
                            bufferedWriter.flush();
                        }
                    }
                }
            }
        }
        System.out.println("Socket done");
    }

}

package github.banana.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * TCP客户端
 * 
 * @author zhgxun
 *
 */
public class TCPClient {

    /**
     * 向服务端请求一个日期
     * 
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // 获取本机回环地址127.0.0.1
        InetAddress address = Inet4Address.getLoopbackAddress();
        // System.out.println(address);
        // 启动Socket连接
        try (Socket socket = new Socket(address, 9000)) {
            // 将数据读取到缓冲中
            try (BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {
                // 将数据写入输出
                try (BufferedWriter bufferedWriter = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {
                    // 向服务端写入信息
                    bufferedWriter.write("time\n");
                    bufferedWriter.flush();
                    // 从服务端读取响应
                    String resp = bufferedReader.readLine();
                    System.out.println("response time: " + resp);
                    // 停留1秒后继续发送请求
                    Thread.sleep(1000);
                    // 向服务端写入一个退出标识
                    bufferedWriter.write("q\n");
                    bufferedWriter.flush();
                    // 尝试从服务端读取信息
                    resp = bufferedReader.readLine();
                    System.out.println("response end: " + resp);
                }
            }
        }
    }

}

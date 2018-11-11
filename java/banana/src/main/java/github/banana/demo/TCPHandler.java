package github.banana.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * 多线程处理
 * 
 * @author zhgxun
 *
 */
public class TCPHandler extends Thread {
    Socket socket;

    /**
     * 构造化
     * 
     * @param socket
     */
    public TCPHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // 读缓冲
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(this.socket.getInputStream(), StandardCharsets.UTF_8))) {
            // 写缓冲
            try (BufferedWriter bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(this.socket.getOutputStream(), StandardCharsets.UTF_8))) {
                // 循环读写每个请求中的请求数据
                for (;;) {
                    String line = bufferedReader.readLine();
                    // 如果需要退出
                    if ("q".equals(line)) {
                        bufferedWriter.write("Good bye!\n");
                        bufferedWriter.flush();
                        break;
                    } else if ("time".equals(line)) {
                        bufferedWriter.write(LocalDateTime.now().toString() + "\n");
                        bufferedWriter.flush();
                    } else {
                        bufferedWriter.write("None!");
                        bufferedWriter.flush();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 不管是否处理异常, 都尝试关闭连接
            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

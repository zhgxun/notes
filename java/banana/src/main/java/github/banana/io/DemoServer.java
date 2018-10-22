package github.banana.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 一个简单的demo服务器
 */
public class DemoServer extends Thread {
    private ServerSocket serverSocket;

    private int getPort() {
        return serverSocket.getLocalPort();
    }


    @Override
    public void run() {
        try {
            // 端口 0 表示自动绑定一个空闲端口
            serverSocket = new ServerSocket(0);
            System.out.println("port: " + serverSocket.getLocalPort());
            // 线程池方式
            // 如果连接数并不是非常多，只有最多几百个连接的普通应用，这种模式往往可以工作的很好。
            // 但是，如果连接数量急剧上升，这种实现方式就无法很好地工作了，因为线程上下文切换开销会在高并发时变得很明显，
            // 这是同步阻塞方式的低扩展性劣势。
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            while (true) {
                Socket socket = serverSocket.accept();
                RequestHandler handler = new RequestHandler(socket);
                executorService.execute(handler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        DemoServer server = new DemoServer();
        server.start();
        System.out.println("server start...");

        // 暂停2秒, 避免服务后启动
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 一次请求， 发起一个客户端连接
        for (int i = 0; i < 10; i++) {
            try (Socket client = new Socket("127.0.0.1", server.getPort())) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                reader.lines().forEach(line -> System.out.println("READ: " + line));
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 模拟服务端的返回, 打印一个日期
 */
class RequestHandler extends Thread {

    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            out.println("DATE: " + new Date());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

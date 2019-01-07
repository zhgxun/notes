package github.banana.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 一个简单的demo服务器
 */
public class DemoServer extends Thread {
    private static final CountDownLatch latch = new CountDownLatch(1);
    private static volatile boolean isRunning = true;
    private ServerSocket serverSocket;

    private int getPort() {
        return serverSocket.getLocalPort();
    }

    @Override
    public void run() {
        try {
            // 端口 0 表示自动绑定一个空闲端口
            serverSocket = new ServerSocket(0);
            latch.countDown();
            System.out.println("port: " + serverSocket.getLocalPort());
            // 线程池方式
            // 如果连接数并不是非常多, 只有最多几百个连接的普通应用, 这种模式往往可以工作的很好
            // 但是, 如果连接数量急剧上升, 这种实现方式就无法很好地工作了, 因为线程上下文切换开销会在高并发时变得很明显
            // 这是同步阻塞方式的低扩展性劣势
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            while (isRunning) {
                // 该处是同步阻塞的操作方式, 需要客户端另外触发连接
                executorService.execute(new RequestHandler(serverSocket.accept()));
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
        System.exit(0);
    }

    public static void main(String[] args) throws IOException {
        DemoServer server = new DemoServer();
        server.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Server start...");

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
        // 结束服务端标识
        isRunning = false;
        // 需要多触发一次退出程序, 否则服务端需要有超时或者心跳相关的检测, 避免僵尸进程, 否则服务端最后一次请求会永远阻塞
        // 正常的服务是, 客服端无法干预服务端的运行状态的, 客户端连接或者处理失败就是失败
        // 不需要关注服务端的运行状态
        new Socket("127.0.0.1", server.getPort());
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

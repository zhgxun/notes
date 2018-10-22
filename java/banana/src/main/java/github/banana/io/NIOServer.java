package github.banana.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class NIOServer extends Thread {

    @Override
    public void run() {
        try {
            // 创建Selector和Channel
            // Selector 一般称 为选择器 ，当然你也可以翻译为 多路复用器 。
            // 它是Java NIO核心组件中的一个，用于检查一个或多个NIO Channel（通道）的状态是否处于可读、可写。
            // 如此可以实现单线程管理多个channels,也就是可以管理多个网络链接。
            Selector selector = Selector.open();
            ServerSocketChannel channel = ServerSocketChannel.open();
            // 绑定一个服务端口
            channel.bind(new InetSocketAddress("127.0.0.1", 8888));
            // 使用非阻塞模式, 否则不可注册选择
            channel.configureBlocking(false);
            // 注册服务并使用新的连接请求
            channel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                // 阻塞等待
                selector.select();

                // 选择器类型
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> iterator = set.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    say((ServerSocketChannel) key.channel());
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对所有客户端请求, 简单的返回当前日期
     *
     * @param channel 每一个处理channel
     */
    private void say(ServerSocketChannel channel) {
        try {
            SocketChannel client = channel.accept();
            String message = "Date: " + new Date();
            client.write(Charset.defaultCharset().encode(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer();
        server.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ByteBuffer buffer = ByteBuffer.allocate(32);

        // 打开一个通道
        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress("127.0.0.1", 8888));
        buffer.clear();
        int temp = client.read(buffer);
        if (temp > 0) {
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            System.out.println("READ: " + new String(bytes));
        }
    }
}

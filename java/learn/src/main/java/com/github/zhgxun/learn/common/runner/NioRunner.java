package com.github.zhgxun.learn.common.runner;

import org.springframework.boot.CommandLineRunner;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Nio
 * <p>
 * This is a Java runner-side program written in NiO
 * <p>
 * 该类的启动会一直阻塞服务往下运行, 比如无法进行单元测试
 */
//@Component
public class NioRunner implements CommandLineRunner {

    // 主线程, 即当前示例main方法
    // Channel[Server, Client]
    // Selector
    // Register
    // ByteBuffer
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner 开始运行 ...");
        // 开启一个连接, 作为boss线程, 专门负责轮询selector
        ServerSocketChannel channel = ServerSocketChannel.open();

        // 必须设置为非阻塞模式
        channel.configureBlocking(false);

        // 绑定一个本地可用端口
        channel.bind(new InetSocketAddress(8081));

        // 开启选择器
        Selector selector = Selector.open();

        // 将 channel 进行注册到选择器上, 并选择一个事件, 即等待客户端连接
        // 事件将以Set<SelectionKey>的形式注册
        channel.register(selector, SelectionKey.OP_ACCEPT);

        // 轮询监听
        while (true) {
            // 阻塞等待一个事件
            int select = selector.select();
            if (select == 0) {
                continue;
            }

            // 获取该选择器上所有的注册事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            // 迭代所有的注册事件
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            // 处理每一个事件, 即是客户端先要连接, 然后才能读
            while (iterator.hasNext()) {
                // 处理每一个客户端连接
                SelectionKey key = iterator.next();

                // 是一个 OP_ACCEPT 接收事件, 该事件由服务端开启并注册, 其实就是一个boss线程
                if (key.isAcceptable()) {
                    // 从事件中获取已有连接通道
                    ServerSocketChannel channel1 = (ServerSocketChannel) key.channel();

                    // 建立一个客户端连接
                    SocketChannel client = channel1.accept();
                    System.out.println("服务端监听到新的客户端连接: " + client.getRemoteAddress());

                    // 设置为非阻塞模式
                    client.configureBlocking(false);

                    // 反馈给客户端已经连接成功
                    client.write(ByteBuffer.wrap("客户端连接成功...\n".getBytes()));

                    // 将客户端连接标识为读事件, 客户端可继续写入信息, 否则会变成一个空连接
                    client.register(selector, SelectionKey.OP_READ);
                }

                // 是一个 OP_READ 可读事件, 该事件由客户端发出, 发送数据或者接收数据, 其实就是一堆的worker线程
                if (key.isReadable()) {
                    SocketChannel channel1 = (SocketChannel) key.channel();

                    // 为每一个读通道分配, 否则数据混混淆
                    // 分配缓存, 其实就是分配一定容量的byte[]数组
                    // 需要自己做TCP的拆包和粘包, 比较麻烦, 而且客户端还有超时, 断开后重新连接等情况
                    // 数据接收太多时, 缓冲满即自动读完一次, 释放后继续读未读完的数据
                    // 需要根据一定的自定义协议获取到完整的数据才能进行处理
                    ByteBuffer buffer = ByteBuffer.allocate(16);

                    boolean isClose = false;

                    // 将客户端发送的请求信息读入缓冲中
                    try {
                        // 如果读取到的数据为空或者无时表明客户端连接被断开, 断开需要关闭该通道
                        if (channel1.read(buffer) <= 0) {
                            isClose = true;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(String.format("客户端 %s 读异常, 可认为已经断开连接", channel1.getRemoteAddress()));
                    }

                    // 客户端关闭时关闭该通道, 继续处理其它通道
                    if (isClose) {
                        System.out.println(String.format("正在关闭客户端 %s 读通道...", channel1.getRemoteAddress()));
                        channel1.close();
                        continue;
                    }

                    // 已字符串的方式表示读取到的数据
                    String request = new String(buffer.array()).trim();

                    // 释放缓冲区
                    buffer.clear();

                    // 打印读取信息
                    System.out.println(String.format("服务端接收到来自客户端: %s 的数据: %s", channel1.getRemoteAddress(), request));

                    // 将消息回写回客户端, 并拼接当前时间
                    // nc localhost 8081 连接时, 不主动换行, 回车导致数据发送黏贴
                    channel1.write(ByteBuffer.wrap(String.format("%s:%s\n", new Date(), request).getBytes()));
                }

                // 移除被消费的事件
                iterator.remove();
            }
        }
    }
}

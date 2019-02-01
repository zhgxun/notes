package com.github.zhgxun.learn.runner;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.Executors;

@Slf4j
public class Aio {

    public static void main(String[] args) {

        try {
            // AsynchronousChannelGroup 组
            AsynchronousChannelGroup group = AsynchronousChannelGroup.withFixedThreadPool(10, Executors.defaultThreadFactory());
            // AsynchronousServerSocketChannel 绑定到组
            AsynchronousServerSocketChannel channel = AsynchronousServerSocketChannel.open(group);
            // 做一些tcp选项设置
            channel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            channel.setOption(StandardSocketOptions.SO_RCVBUF, 16 * 1024);
            // 绑定到一个端口， 等待链接的队列长度为100
            channel.bind(new InetSocketAddress(InetAddress.getLocalHost(), 9001), 100);
            log.info("服务端启动... {}", 9001);
            channel.accept(null, new AcceptCompletionHandler());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.github.zhgxun.learn.common.runner;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

@Slf4j
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel> {

    @Override
    public void completed(AsynchronousSocketChannel result, AsynchronousServerSocketChannel attachment) {
        try {
            log.info("接收到远程连接: {}", result.getRemoteAddress());
            log.info("继续接收客户端连接...");
            attachment.accept(null, this);
            // 处理本身也是一个完成句柄
            final ByteBuffer buffer = ByteBuffer.allocate(1024);
            final long timeout = 10L;
            result.read(buffer, timeout, TimeUnit.SECONDS, null, new CompletionHandler<Integer, Object>() {
                @Override
                public void completed(Integer res, Object attachment) {
                    log.info("准备读取: " + res);
                    // 读取完毕
                    if (res == -1) {
                        try {
                            log.info("正在关闭: {}", result.getRemoteAddress());
                            result.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    buffer.flip();
                    log.info("接收到消息: {}", Charset.forName("UTF-8").decode(buffer));
                    buffer.clear();
                    result.read(buffer, timeout, TimeUnit.SECONDS, null, this);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    exc.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {
        log.error("出错...");
        exc.printStackTrace();
    }
}

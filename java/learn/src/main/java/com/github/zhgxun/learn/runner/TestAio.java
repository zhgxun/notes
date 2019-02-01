package com.github.zhgxun.learn.runner;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class TestAio {

    public static void main(String[] args) throws Exception {
        final AsynchronousServerSocketChannel channel =
                AsynchronousServerSocketChannel.open().bind(new InetSocketAddress("0.0.0.0", 8888));
        channel.accept(null, new CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>() {
            @Override
            public void completed(final AsynchronousSocketChannel client, AsynchronousServerSocketChannel attachment) {
                try {
                    System.out.println("新客户端连接: " + client.getRemoteAddress());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                channel.accept(null, this);

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                client.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result_num, ByteBuffer attachment) {
                        if (result_num == -1) {
                            return;
                        }
                        attachment.flip();
                        CharBuffer charBuffer = CharBuffer.allocate(1024);
                        CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
                        decoder.decode(attachment, charBuffer, false);
                        charBuffer.flip();
                        String data = new String(charBuffer.array(), 0, charBuffer.limit());
                        System.out.println("read data:" + data);
                        buffer.clear();
                        charBuffer.clear();
                        client.read(buffer, buffer, this);
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {
                        System.out.println("read error");
                        exc.printStackTrace();
                    }
                });
            }

            @Override
            public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {
                System.out.println("accept error");
                exc.printStackTrace();
            }
        });
        while (true) {
        }
    }
}

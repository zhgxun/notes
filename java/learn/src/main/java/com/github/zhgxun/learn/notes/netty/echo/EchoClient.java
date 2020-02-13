package com.github.zhgxun.learn.notes.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * echo 客户端示例
 */
public class EchoClient {

    public static void main(String[] args) {
        // 客户端就相对简单, 只有一个线程池处理
        // 也不用像原生nio一样, 需要自己去处理 ServerSocketChannel 和 SocketChannel 的转化
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            // 客户端启动器
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            // 客户端 channel
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast(new EchoClientHandler());
                }
            });

            ChannelFuture future = bootstrap.connect("127.0.0.1", 12121).sync();

            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}

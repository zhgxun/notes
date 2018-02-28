package com.flight;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 时间服务器端
 */
public class TimeServer {

    public static void main(String args[]) {
        EventLoopGroup boss = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(boss).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new TimeServerHandler());
            }
        });
        b.childOption(ChannelOption.SO_KEEPALIVE, true);
        try {
            ChannelFuture f = b.bind(8091).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
        }
    }
}

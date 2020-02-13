package com.github.zhgxun.learn.notes.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * echo 服务服务端示例
 */
public class EchoServer {

    public static void main(String[] args) {
        // 主线程池, 一般均使用主从 Reactor 模式, 通常是一主多从模式
        // 主线程接收连接, 不处理具体的工作请求
        EventLoopGroup bossEventLoopGroup = new NioEventLoopGroup(1);
        // 工作线程池, 处理工作请求
        EventLoopGroup workEventLoopGroup = new NioEventLoopGroup();

        try {
            // 服务启动器
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 添加主从线程组
            bootstrap.group(bossEventLoopGroup, workEventLoopGroup);

            // 选择channel类型, 接收连接的通道类型一般为 ServerSocketChannel
            bootstrap.channel(NioServerSocketChannel.class);

            // 一些额外的选项, 可不配置
            bootstrap.option(ChannelOption.SO_BACKLOG, 1);

            // 一些 handler, 可不配置, 比如配置日志记录处理器
            bootstrap.handler(new LoggingHandler(LogLevel.DEBUG));

            // 子 handler, 这个是实现编解码和工作任务的地方, 也是需要自行实现的地方
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    // 从通道中获取pipe line
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    // 将自己定制化的处理器一次追加至通道中
                    pipeline.addLast(new EchoServerHandler());
                }
            });

            // 绑定服务端口启动服务
            ChannelFuture future = bootstrap.bind(12121).sync();

            // 优雅的等待关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 优雅停止线程池
            bossEventLoopGroup.shutdownGracefully();
            workEventLoopGroup.shutdownGracefully();
        }
    }
}

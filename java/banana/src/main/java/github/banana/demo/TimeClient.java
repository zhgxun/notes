package github.banana.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 时间显示客户端, 只能运行一次, 多次启动多次运行
 */
public class TimeClient {
    public static void main(String[] args) {
        String host = "localhost"; //args[0];
        Integer port = 8091; //Integer.parseInt(args[1]);

        // 如果您只指定一个EventLoopGroup，它将既用作老板组又用作工作组。
        // 虽然老板工人不是用于客户端的。
        EventLoopGroup work = new NioEventLoopGroup();

        // Bootstrap与ServerBootstrap类似，但它适用于非服务器通道，如客户端或无连接通道
        Bootstrap b = new Bootstrap();
        b.group(work);
        b.channel(NioSocketChannel.class);

        // 请注意，我们在这里不使用childOption()，因为客户端SocketChannel没有父节点。
        b.option(ChannelOption.SO_KEEPALIVE, true);
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new TimeClientHandler());
            }
        });

        try {
            // 我们应该调用connect()方法而不是bind()方法。
            ChannelFuture f = b.connect(host, port).sync();

            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭Netty应用程序通常与关闭通过shutdownGracefully()创建的所有EventLoopGroups一样简单。
            // 它会返回一个Future，当EventLoopGroup完全终止并且属于该组的所有通道都已关闭时通知您。
            work.shutdownGracefully();
        }
    }
}

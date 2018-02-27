package github.banana;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

// netty文档：http://netty.io/wiki/user-guide-for-4.x.html
public class DiscardServer {

    private int port;

    private DiscardServer(int port) {
        this.port = port;
    }

    private void run() throws Exception {
        // NioEventLoopGroup 是一个处理I/O操作的多线程事件循环。
        // Netty为不同类型的传输提供了各种EventLoopGroup实现。
        // 在这个例子中，我们正在实现一个服务器端应用程序，因此将使用两个NioEventLoopGroup。 第一个通常被称为“老板”，接
        // 受传入的连接。 第二个通常称为“工作者”，一旦老板接受连接并将接受的连接注册给工作人员，则处理接受连接的流量。 使
        // 用多少个线程以及它们如何映射到创建的通道取决于EventLoopGroup的实现，甚至可以通过构造函数进行配置。
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // ServerBootstrap 是设置服务器的辅助类。 您可以直接使用Channel设置服务器。 但是，请注意，这是一个乏味的过程，
        // 在大多数情况下您不需要这样做。
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
                // 在这里，我们指定使用NioServerSocketChannel类来实例化一个新的Channel来接受传入的连接。
                .channel(NioServerSocketChannel.class)
                // 此处指定的处理程序将始终由新接受的通道评估。
                // ChannelInitializer是一个特殊的处理程序，旨在帮助用户配置新的通道。
                // 您很可能希望通过添加一些处理程序（如DiscardServerHandler）来配置新通道的ChannelPipeline，以实现您的网络应用程序。
                // 随着应用程序变得复杂，您可能会向流水线添加更多处理程序，并最终将此匿名类提取到顶级类中。
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new DiscardServerHandler());
                    }
                })
                // 您还可以设置特定于Channel实现的参数。
                // 我们正在编写一个 TCP/IP 服务器，因此我们可以设置套接字选项，如tcpNoDelay和keepAlive。
                // 请参阅ChannelOption的apidocs和特定的ChannelConfig实现，以获得有关支持的ChannelOptions的概述。
                //
                // 你有没有注意到option()和childOption(), option()用于接受传入连接的NioServerSocketChannel。
                // childOption()适用于父ServerChannel接受的通道，在这种情况下是NioServerSocketChannel。
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        // 剩下的就是绑定到端口并启动服务器。
        // 在这里，我们绑定到机器中所有NIC（网络接口卡）的端口8080。
        // 您现在可以根据需要多次调用bind()方法（使用不同的绑定地址）。
        ChannelFuture f = b.bind(port).sync();

        // 等待直到服务器被关闭
        f.channel().closeFuture().sync();

        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8090;
        }
        new DiscardServer(port).run();
    }
}

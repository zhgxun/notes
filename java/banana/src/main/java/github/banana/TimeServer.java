package github.banana;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Netty 拆包粘包和服务启动流程分析
 *
 * @link http://www.cnblogs.com/itdragon/p/8365694.html
 * <p>
 * 时间服务器端
 */
public class TimeServer {

    /**
     * ServerBootstrap 服务器启动和连接过程：
     * 第一步：是给Channel设置options和attrs，
     * 第二步：复制childGroup，childHandler，childOptions和childAttrs等待服务器和客户端连接，
     * 第三步：实例化一个ChannelInitializer，添加到Pipeline的末尾。
     * 第四步：当Channel注册到NioEventLoop时，ChannelInitializer触发initChannel方法，pipeline装入自定义的Handler，给Channel设置一下child配置。
     * <p>
     * 小结：
     * 1 group，options，attrs，handler，是在服务器端初始化时配置，是AbstractBootstrap的方法。
     * 2 childGroup，childOption，childAttr，childHandler，是在服务器与客户端建立Channel后配置，是ServerBootstrap的方法。
     * 3 Bootstrap 和 ServerBootstrap 都继承了AbstractBootstrap类。
     * 4 若不设置childGroup，则默认取group值。
     * 5 Bootstrap 和 ServerBootstrap 启动服务时，都会执行验证方法，判断必填参数是否都有配置。
     *
     * @param args
     */
    public static void main(String args[]) {
        // 本质是个线程池，继承了 ScheduledExecutorService 定时任务线程池。
        EventLoopGroup boss = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        // 是用来处理NIO通信模式的线程池。每个线程池有N个NioEventLoop来处理Channel事件，每一个NioEventLoop负责处理N个Channel。
        b.group(boss).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new TimeServerHandler());
            }
        });
        b.childOption(ChannelOption.SO_KEEPALIVE, true);
        try {
            ChannelFuture f = b.bind(8091).sync();
            System.out.println("服务端启动...");
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
        }
    }
}

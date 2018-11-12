package github.banana.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * 服务端处理
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    // 读取数据
    // 每当从客户端收到新的数据时， 这个方法会在收到消息时被调用
    // 这个例子中，收到的消息的类型是ByteBuf
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 打印客户端传递过来的输入
        try {
            ByteBuf in = (ByteBuf) msg;
            System.out.println(in.toString(CharsetUtil.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // ByteBuf是一个引用计数对象，这个对象必须显示地调用release()方法来释放
            // 请记住处理器的职责是释放所有传递到处理器的引用计数对象
            ReferenceCountUtil.release(msg);
        }
    }

    // 读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    // 异常关闭
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}

package github.banana;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * DiscardServerHandler扩展了ChannelInboundHandlerAdapter，它是ChannelInboundHandler的一个实现。
 * ChannelInboundHandler提供了可以覆盖的各种事件处理程序方法。
 * 目前，只需扩展ChannelInboundHandlerAdapter而不是自己实现处理程序接口即可。
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 我们在这里覆盖channelRead()事件处理程序方法。
     * 每当接收到来自客户端的新数据时，都会使用收到的消息调用此方法。
     * 在这个例子中，接收到的消息的类型是ByteBuf。
     *
     * 一个ChannelHandlerContext对象提供了各种操作，使您能够触发各种I/O事件和操作。
     * 在这里，我们调用write(Object)逐字写入收到的消息。
     * 请注意，我们没有发布收到的消息，与我们在DISCARD示例中所做的不同。
     * 这是因为当Netty写出来时，Netty会为你发布它。
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 为了实现DISCARD协议，处理程序必须忽略收到的消息。
        // ByteBuf是一个引用计数的对象，必须通过release()方法明确释放。
        // 请记住，处理程序的职责是释放传递给处理程序的任何引用计数的对象。
        // ((ByteBuf) msg).release();

        // 只要收到数据，channelRead()方法就会被调用。
        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) { // (1)
                System.out.print((char) in.readByte());
                System.out.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    /**
     * 当由于 I/O 错误或由于在处理事件时抛出异常而导致的处理程序实现导致Netty引发异常时，会使用Throwable调用exceptionCaught()事件处理程序方法。
     * 在大多数情况下，应该记录捕获到的异常并关闭其相关通道，尽管此方法的实现可能因您想要处理异常情况而需要做的不同而有所不同。
     * 例如，您可能希望在关闭连接之前发送包含错误代码的响应消息。
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

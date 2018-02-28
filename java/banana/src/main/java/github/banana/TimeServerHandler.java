package com.flight;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 时间显示
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当连接建立并准备好产生流量时，channelActive()方法将被调用。 我们来写一个32位整数来表示此方法中的当前时间。
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        // 要发送一条新消息，我们需要分配一个新的缓冲区，它将包含该消息。
        // 我们将写一个32位整数，因此我们需要一个容量至少为4个字节的ByteBuf。
        // 通过ChannelHandlerContext.alloc()获取当前的ByteBufAllocator并分配一个新的缓冲区。
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int)(System.currentTimeMillis() / 1000L + 2208988800L));
        // ByteBuf没有这样的方法，因为它有两个指针;一个用于读取操作，另一个用于写入操作。
        // 当您向ByteBuf写入内容时，写入器索引会增加，而阅读器索引不会更改。
        // 阅读器索引和写入器索引分别表示消息开始和结束的位置。
        // 相反，NIO缓冲区不提供一个干净的方式来确定消息内容的起始和结束位置，而不调用翻转方法。
        // 当您忘记翻转缓冲区时，您将遇到麻烦，因为没有任何或不正确的数据将被发送。
        // 这种错误在Netty中不会发生，因为我们对不同的操作类型有不同的指针。
        // 你会发现它让你的生活变得更加轻松，因为你已经习惯了它 - 一种无需翻身的生活！
        // 另一点要注意的是ChannelHandlerContext.write()（和writeAndFlush()）方法返回一个ChannelFuture。
        // ChannelFuture表示尚未发生的I/O操作。
        // 这意味着，任何请求的操作可能尚未执行，因为所有操作在Netty中都是异步的。
        // 例如，即使在发送消息之前，以下代码可能会关闭连接：
        // 因此，需要在ChannelFuture完成后调用close()方法，该方法由write()方法返回，并在写入操作完成时通知其侦听器。
        // 请注意，close()也可能不会立即关闭连接，并返回一个ChannelFuture。
        final ChannelFuture f = ctx.writeAndFlush(time);
//        f.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture future) throws Exception {
//                assert f == future;
//                ctx.close();
//            }
//        });
        // 使用Lambda表达式替换，覆写操作完成时关闭连接
        // 当写请求完成后，我们如何得到通知？
        // 这与将ChannelFutureListener添加到返回的ChannelFuture一样简单。
        // 在这里，我们创建了一个新的匿名ChannelFutureListener，在操作完成后关闭Channel。
        // 或者，您可以使用预定义的侦听器简化代码：f.addListener(ChannelFutureListener.CLOSE);
        f.addListener((future) -> {
            assert f == future;
            ctx.close();
        });
    }

    /**
     * 异常处理，发生异常时打印异常信息，关闭连接
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

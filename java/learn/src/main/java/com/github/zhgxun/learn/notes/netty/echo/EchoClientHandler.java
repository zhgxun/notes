package com.github.zhgxun.learn.notes.netty.echo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    private String message = "来自客服端的问候";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("EchoClientHandler channelActive ...");
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("EchoClientHandler channelRead :" + msg.toString());
        ctx.write(message);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("EchoClientHandler channelReadComplete ...");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}

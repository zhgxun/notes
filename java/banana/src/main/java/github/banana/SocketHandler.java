package github.banana;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.util.Date;

public class SocketHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;
    private static String URI = "ws://127.0.0.1:8001/websocket";

    // 客户端与服务端创建连接时调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketConfig.group.add(ctx.channel());
        System.out.println("客服端与服务端连接开启...");
    }

    // 客户端与服务端断开连接时调用
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SocketConfig.group.remove(ctx.channel());
        System.out.println("客服端与服务端连接断开");
    }

    // 服务端与客户端发送数据结束时调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        System.out.println("服务端与客户端发送数据结束");
    }

    // 异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    // 服务端处理客户端websocket时的核心
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("开始处理...");
        if (o instanceof WebSocketFrame) {
            System.out.println("Socket请求...");
            handWebSocketFrame(channelHandlerContext, (WebSocketFrame) o);
        } else if (o instanceof FullHttpRequest) {
            System.out.println("Http请求...");
            handHttpRequest(channelHandlerContext, (FullHttpRequest) o);
        }
    }

    /**
     * 处理http请求
     *
     * @param ctx
     * @param request
     */
    private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        if (!request.getDecoderResult().isSuccess() || !"websocket".equals(request.headers().get("Upgrade"))) {
            handHttpResponse(ctx, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            System.out.println("请求不成功");
            return;
        }

        System.out.println("请求成功继续处理...");
        WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(URI, null, false);
        handshaker = factory.newHandshaker(request);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), request);
        }
    }

    /**
     * 向客户端发送响应内容
     *
     * @param ctx
     * @param request
     * @param response
     */
    private void handHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, DefaultFullHttpResponse response) {
        if (response.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(response.getStatus().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(buf);
            buf.release();
        }

        ChannelFuture f = ctx.channel().writeAndFlush(response);

        if (response.getStatus().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 处理客户端socket请求
     *
     * @param ctx
     * @param frame
     */
    private void handWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 关闭指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
        }

        // ping
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        // 非二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            throw new RuntimeException("不支持的消息类型");
        }

        // 获取客服端向服务端发送的消息
        String request = ((TextWebSocketFrame) frame).text();
        System.out.println("服务端收到客户端信息...");
        TextWebSocketFrame ws = new TextWebSocketFrame(new Date().toString() + ctx.channel().id() + ">>>>" + request);

        // 服务端向每个连接的客户端发送
        SocketConfig.group.writeAndFlush(ws);
    }
}

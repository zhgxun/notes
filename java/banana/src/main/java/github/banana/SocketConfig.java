package github.banana;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 保存全局的Channel实例
 */
public class SocketConfig {
    public static String URI = "ws://127.0.0.1:8001/websocket";
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}

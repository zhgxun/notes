package github.banana.view;

import io.netty.buffer.ByteBuf;

/**
 * 编解码技术
 *
 * <p>
 * Netty 常用的默认编解码包
 * 通常我们习惯将编码(Encode)称为序列化(Serialization), 它将对象序列化为字节数组, 用于网络传输, 数据持久化或其它用途
 * 解码(Decode)称为反序列化(Deserialization), 它把网络, 磁盘等读取的字节数组还原成原始对象, 通常是原始对象的拷贝, 以便后续的业务处理
 * <p>
 * 一般接触到的第一种序列化或者编解码技术就是Java默认提供的序列化机制, 需要序列化的对象只需要实现 {@link java.io.Serializable} 接口
 * 并生成序列化ID, 这个类就能通过 {@link java.io.ObjectOutput} 和 {@link java.io.ObjectInput} 反序列化和序列化
 * <p>
 * 可以直接把对象作为可存储的字节数组写入文件, 也可以传输到网络上, 对程序员来说, 基于JDK默认的序列化机制可以避免操作底层的字节数组
 * <p>
 * 序列化的目的是网络传输, 对象持久化, 缺点就是无法跨语言, 序列化后码流太大, 序列化性能太低, 因此衍生出了多种编解码技术和框架, 这些编解
 * 码框架实现消息的高效序列化, 例如 hessian, protobuf, thrift, kryo, msgpack, avro, fst 等
 * <p>
 * Netty 自带的编解码器
 * 编解码器框架使得编写自定义的编解码器很容易, 并且也很容易重用和封装, Netty 提供了
 * {@link io.netty.handler.codec.MessageToMessageEncoder}
 * 和 {@link io.netty.handler.codec.MessageToMessageDecoder}
 * 接口, 方便我们扩展编解码
 * <p>
 * Codec 编解码器
 * 编解码器由编码器和解码器组成, 解码器负责将消息从字节或其它序列化形式转成指定的消息对象, 编码器和解码器的结构很简单, 消息被编码后解码后
 * 会自动通过 {@link io.netty.util.ReferenceCountUtil#release(Object)} 释放, 如果不想释放消息可以使用
 * {@link io.netty.util.ReferenceCountUtil#retain(Object)} 这将会使引用数量增加而没有消息发布, 大多时候不需要这么做
 * <p>
 * <p>
 * <p>
 * Encoder 编码器
 * Netty 提供了一个基类, 编码器有以下两种类型:
 * 消息对象编码成消息对象 {@link io.netty.handler.codec.MessageToMessageEncoder}
 * 消息对象编码成字节码 {@link io.netty.handler.codec.MessageToByteEncoder}
 * <p>
 * 相对于解码器, 编码器少了一个 byte-to-byte 的类型, 因为出站数据这样做没有意义
 * 编码器的作用就是处理好的数据转成字节码以方便在网络中传输
 * <p>
 * Netty 分别提供了两个抽象类
 * {@link io.netty.handler.codec.MessageToMessageEncoder}
 * <p>
 * {@link io.netty.handler.codec.MessageToByteEncoder}
 * 只需要实现 {@code io.netty.handler.codec.MessageToByteEncoder#encode(ChannelHandlerContext, Object, ByteBuf)} 即可
 * <p>
 * Decoder 解码器
 * Netty 提供了丰富的解码器抽象基类, 我们可以很容易的实现这些基类来自定义解码器
 * 解码字节到消息
 * {@link io.netty.handler.codec.ByteToMessageDecoder} {@link io.netty.handler.codec.ReplayingDecoder}
 * 解码消息到消息
 * {@link io.netty.handler.codec.MessageToMessageDecoder}
 * <p>
 * 解码器负责处理入站数据从一种格式到另一种格式, 处理入站数据是抽象 {@link io.netty.channel.ChannelInboundHandler} 的实现
 * 实践中使用解码器很简单, 就是将入站数据转换格式后传递到 {@link io.netty.channel.ChannelPipeline} 中的下一个
 * {@link io.netty.channel.ChannelInboundHandler} 进行处理
 * 这样的处理很灵活, 可以将解码器放在 {@link io.netty.channel.ChannelPipeline} 中重用逻辑
 * <p>
 * {@link io.netty.handler.codec.ByteToMessageDecoder}
 * 通常你需要将消息从字节解码成消息或者从字节解码成其他的序列化字节
 * 这是一个常见的任务, Netty 提供了抽象基类, 我们可以使用它来实现
 * Netty 中提供的 {@code ByteToMessageDecoder} 可以将字节消息解码成 POJO 对象
 * {@code io.netty.handler.codec.ByteToMessageDecoder#decode(ChannelHandlerContext, ByteBuf, List)}
 * 这个方法是唯一的一个需要自己实现的抽象方法, 作用是将 {@link io.netty.buffer.ByteBuf} 数据解码成其它形式的数据
 * {@code io.netty.handler.codec.ByteToMessageDecoder#decodeLast(ChannelHandlerContext, ByteBuf, List)}
 * 实际上调用的是上面的 {@code decode(...args)} 方法
 * <p>
 * {@link io.netty.handler.codec.ReplayingDecoder}
 * byte-to-message 解码的一种特殊的抽象基类, 读取缓冲区的数据之前需要检查是否有足够的字节, 使用 {@code ReplayingDecoder} 就无需自己
 * 检查
 * 若 {@code ByteBuf} 中有足够的字节, 则会正常读取, 若没有足够的字节则会停止解码, 也正是因为这样的包装使得带有一定的局限性
 * 不是所有的操作都被 ByteBuf 支持, 如果一个调用不支持的操作会抛出 {@link io.netty.handler.codec.DecoderException}
 * {@link ByteBuf#readableBytes()} 大部分时间不会返回期望值
 * <p>
 * 如果你能忍受上面列出的限制, 相比 {@code ByteToMessageDecoder}, 你可能更喜欢 {@code ReplayingDecoder}
 * 在满足需求的情况下推荐使用 {@code ByteToMessageDecoder}, 因为处理比较简单, 没有那么复杂, {@code ReplayingDecoder} 继承自
 * {@code ByteToMessageDecoder}, 所以它们提供的接口是相同的
 * <p>
 * {@link io.netty.handler.codec.MessageToMessageDecoder}
 * 将消息对象转换成消息对象, 它是一个抽象类, 需要我们自己实现 {@code decode()}
 * message-to-message 同 byte-to-message 的处理机制一样
 * <p>
 * <p>
 * Netty TCP 拆包和粘包
 * <p>
 * 特殊分隔符解码器 {@link io.netty.handler.codec.DelimiterBasedFrameDecoder}
 * 定长解码器 {@link io.netty.handler.codec.FixedLengthFrameDecoder}
 * 基于包头不固定长度解码器 {@link io.netty.handler.codec.LengthFieldBasedFrameDecoder}
 * 包头添加总包长度解码器 {@link io.netty.handler.codec.LengthFieldPrepender}
 * <p>
 */
public class EncodeTest {

    public static void main(String[] args) {

    }
}

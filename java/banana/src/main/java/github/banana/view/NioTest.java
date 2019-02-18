package github.banana.view;

/**
 * Java 的几种IO操作模型
 * <p>
 * BIO
 * 流模型, 同步阻塞, 简单直观, 但是效率存在局限, 容易成为应用性能的瓶颈
 * <p>
 * NIO
 * 定义包含数据的类, 通过以块的形式处理数据, 多路复用, 同步非阻塞, 提供更接近操作系统底层的高性能数据操作方式
 * <p>
 * AIO
 * 基于事件和回调, 异步非阻塞, 应用操作不阻塞直接返回, 后台处理完成操作系统会通知响应线程进行后续工作
 * <p>
 * IO或者输入/输出, 指计算机与外部世界或者一个程序与计算机其余部分之间的接口
 * 这对于任何计算机系统都非常关键, 因而所有IO操作的主体实际上是内置在操作系统中的, 单独的程序一般是让系统为它们完成大部分的工作
 * <p>
 * 在 Java 编程中, 直到最近一直使用流的方式完成IO, 所有IO都被视为单个字节的移动, 通过一个称为 {@link java.util.stream.Stream}
 * 的对象一次移动一个字节, 流IO用于与外部世界接触, 它也在内部使用, 用于将对象转换为字节, 然后再转换回对象
 * <p>
 * NIO 与原来的 IO 有同样的作用和目的, 但是它使用不同的方式, 块IO, NIO将最耗时的 IO 操作即填充和提取缓冲区转移会操作系统, 因而可以极大地提高速度
 * <p>
 * 流与块的比较
 * 1. 原来的 IO 库 java.io.* 中, 与 NIO最重要的区别是数据打包和传输的方式, 原来的IO以流的方式处理数据, 而 NIO 以块的方式处理数据
 * 2. 面向流的 IO 系统一次一个字节地处理数据, 一个输入流产生一个字节的数据, 一个输出流消费一个字节的数据, 为流式数据创建过滤器非常容易
 * 链接几个过滤器, 以便每个过滤器只负责单个复杂处理机制的一部分, 这样也是相对简单的, 不利的一面是面向 IO 通常相当慢
 * 3. 一个面向块的IO系统以块的形式处理数据, 每一个操作都在一步中产生或者消费一个数据块, 按块处理数据比较流式的字节处理数据要快得多, 但是
 * 面向块的 IO 缺少一些面向流的 IO 所具有的优雅性和简单性
 * <p>
 * IO扩展
 * 1. 同步是一种可靠的有序运行机制, 异步通常依靠事件, 回调等机制来实现任务间的次序关系
 * 2. 在进行阻塞操作时, 当前线程会处于阻塞状态, 无法从事其它任务, 只有当条件就绪才能继续, 非阻塞则不管IO操作是否结束, 直接返回, 响应操作在后台继续处理
 * 3. 不能一概而论同步阻塞就是低效, 具体要看应用和系特征
 * 4. IO不仅仅是对文件的操作, 网络编程中, 比如 Socket 通信, 都是典型的IO操作
 * 5. 输入流, 输出流是用于读取和写入字节的
 * 6. {@link java.io.Reader} 和 {@link java.io.Writer} 是用于操作字符, 增加了字符编解码等功能, 适用于类似从文件中读取或者写入文本信息
 * 本质上计算机操作的都是字节, 不管是网络通信还是文件读取, Reader/Write 相当于构建了应用逻辑和原始数据之间的桥梁
 * 7. {@link java.io.BufferedOutputStream} 等待缓冲区的实现, 可以避免频繁的磁盘读写, 进而提高IO处理效率, 这种设计利用了缓冲区, 将批量数据经过一次操作
 * 8. 很多IO工具都实现了 {@link java.io.Closeable} 接口, 因为需要对资源进行释放
 * <p>
 * NIO的组成
 * 1. {@link java.nio.Buffer}
 * 高效的数据容器, 除了布尔型, 所有原始数据类型都有相应的Buffer实现
 * 2. {@link java.nio.channels.Channel}
 * 类似在 Linux 之类操作系统上看到的文件描述符, 是NIO中被用来支持批量式操作IO操作的一种抽象
 * 3. {@link java.io.File} 或者 {@link java.net.Socket}, {@link java.nio.channels.Channel}
 * 通常被认为是比较高层次的抽象, 而 Channel 则是更加操作系统底层的一种抽象, 这也使得NIO得以充分利用现代操作系统底层机制, 获得特定场景下的性能优化
 * 4. {@link java.nio.channels.Selector}
 * 是NIO实现多路复用的基础, 它提供了一种高效的机制, 可以检测到注册在 Selector 上的多个 Channel 中, 是否有 Channel 处于就绪状态, 进而实现
 * 了单线程对过 Channel 的高效管理, Selector 同样基于底层操作系统机制, 不同模式, 不同版本都存在区别, Linux 上基于 epoll 轮询实现
 * 5. {@link java.nio.charset.Charset}
 * 提供 Unicode 字符串定义, NIO 也提供了相应的编解码器, 例如 <code>Charset.defaultCode().echo("Hello")<code/>
 * <p>
 * <p>
 * NIO编程模型
 * 1. 开启 {@link java.nio.channels.ServerSocketChannel} Channel
 * <pre><code>
 * // 开启一个连接, 作为boss线程, 专门负责轮询selector
 * ServerSocketChannel channel = ServerSocketChannel.open();
 * // 必须设置为非阻塞模式
 * channel.configureBlocking(false);
 * // 绑定一个本地可用端口
 * channel.bind(new InetSocketAddress(8081));
 * <code/><pre/>
 *
 * 2. 打开一个 {@link java.nio.channels.Selector} 选择器
 * <pre><code>
 * // 开启选择器
 * Selector selector = Selector.open();
 * <code/><pre/>
 *
 * 3. Channel 和 Selector 绑定
 * <pre><code>
 * // 将 channel 进行注册到选择器上, 并选择一个事件, 即等待客户端连接
 * // 事件将以Set<SelectionKey>的形式注册
 * channel.register(selector, SelectionKey.OP_ACCEPT);
 * <code/><pre/>
 * 绑定时并且将事件注册为接收类型, 即等待客户端连接建立
 *
 * 4. 轮询监听
 * <pre><code>
 * // 轮询监听
 * while (true) {
 *     // 阻塞等待一个事件
 *     int select = selector.select();
 *     if (select == 0) {
 *         continue;
 *     }
 *     // ...
 * }
 * <code/><pre/>
 *
 * 5. 获取事件
 * 一旦有事件触发, 则获取所有事件来轮询, 因为是多线程, 可能瞬间触发多个线程的事件
 * <pre><code>
 * // 获取该选择器上所有的注册事件
 * Set<SelectionKey> selectionKeys = selector.selectedKeys();
 * <code/><pre/>
 *
 * 6. 迭代事件
 * <pre><code>
 * // 迭代所有的注册事件
 * Iterator<SelectionKey> iterator = selectionKeys.iterator();
 *
 * // 处理每一个事件, 即是客户端先要连接, 然后才能读
 * while (iterator.hasNext()) {
 *     // 处理每一个客户端连接
 *     SelectionKey key = iterator.next();
 *     // ...
 * }
 * <code/><pre/>
 *
 * 7. 转换事件
 * <pre><code>
 * // 是一个 OP_ACCEPT 接收事件, 该事件由服务端开启并注册, 其实就是一个boss线程
 * if (key.isAcceptable()) {
 *     // 从事件中获取已有连接通道
 *     ServerSocketChannel channel1 = (ServerSocketChannel) key.channel();
 *
 *     // 建立一个客户端连接
 *     SocketChannel client = channel1.accept();
 *     System.out.println("服务端监听到新的客户端连接: " + client.getRemoteAddress());
 *
 *     // 设置为非阻塞模式
 *     client.configureBlocking(false);
 *
 *     // 反馈给客户端已经连接成功
 *     client.write(ByteBuffer.wrap("客户端连接成功...\n".getBytes()));
 *
 *     // 将客户端连接标识为读事件, 客户端可继续写入信息, 否则会变成一个空连接
 *     client.register(selector, SelectionKey.OP_READ);
 * }
 * <code/><pre/>
 * 这一步相当重要, 尤其是 Channel 的事件转换
 * 首先将 事件池 中的事件取出, 将 channel 转换为 ServerSocketChannel, 进而将该 服务端的通道转换为客户端的通道 SocketChannel, 并
 * 进入接收模式, 此时关注的事件将转换为 可允许读, 即读状态, 等待客户端输入数据, 一样也需要设置非阻塞模式, 这个转换非常重要, 是接下来继续进行的基础
 *
 * 总体上来说, 就是一个事件被获取到, 如果是可接受的状态, 需要将其转换为客户端连接, 并注册进事件池中, 同时状态变为可接受客户端输入, 等待下一次被获取
 *
 * 8. 处理读事件
 * <pre><code>
 * // 是一个 OP_READ 可读事件, 该事件由客户端发出, 发送数据或者接收数据, 其实就是一堆的worker线程
 * if (key.isReadable()) {
 *     SocketChannel channel1 = (SocketChannel) key.channel();
 *
 *     // 为每一个读通道分配, 否则数据混混淆
 *     // 分配缓存, 其实就是分配一定容量的byte[]数组
 *     // 需要自己做TCP的拆包和粘包, 比较麻烦, 而且客户端还有超时, 断开后重新连接等情况
 *     // 数据接收太多时, 缓冲满即自动读完一次, 释放后继续读未读完的数据
 *     // 需要根据一定的自定义协议获取到完整的数据才能进行处理
 *     ByteBuffer buffer = ByteBuffer.allocate(16);
 *
 *     boolean isClose = false;
 *
 *     // 将客户端发送的请求信息读入缓冲中
 *     try {
 *         // 如果读取到的数据为空或者无时表明客户端连接被断开, 断开需要关闭该通道
 *         if (channel1.read(buffer) <= 0) {
 *             isClose = true;
 *         }
 *     } catch (Exception e) {
 *         e.printStackTrace();
 *         System.out.println(String.format("客户端 %s 读异常, 可认为已经断开连接", channel1.getRemoteAddress()));
 *     }
 *
 *     // 客户端关闭时关闭该通道, 继续处理其它通道
 *     if (isClose) {
 *         System.out.println(String.format("正在关闭客户端 %s 读通道...", channel1.getRemoteAddress()));
 *         channel1.close();
 *         continue;
 *     }
 *
 *     // 已字符串的方式表示读取到的数据
 *     String request = new String(buffer.array()).trim();
 *
 *     // 释放缓冲区
 *     buffer.clear();
 *
 *     // 打印读取信息
 *     System.out.println(String.format("服务端接收到来自客户端: %s 的数据: %s", channel1.getRemoteAddress(), request));
 *
 *     // 将消息回写回客户端, 并拼接当前时间
 *     // nc localhost 8081 连接时, 不主动换行, 回车导致数据发送黏贴
 *     channel1.write(ByteBuffer.wrap(String.format("%s:%s\n", new Date(), request).getBytes()));
 * }
 * <code/><pre/>
 * 需要注意的是, 每种情况下的 Channel 角色变换, 这个比较微妙
 *
 * 9. 移除事件池
 * <pre><code>iterator.remove();<code/><pre/>
 * 这一步是必须的, 否则事件将一直存在于池中, 无法继续处理其它事件
 *
 * 对NIO最好的实现方式, 就是大名鼎鼎的网络框架 Netty, 基本能用 Nio 解决的事情都可以交给 Netty 来解决, 而且提供了好几种编解码包,
 * 后续等我找好工作了, 将重点研究这个框架, 避免了 TCP 拆包粘包操作, 性能也保证, 而且已经相当成熟
 */
public class NioTest {

    public static void main(String[] args) {

    }
}

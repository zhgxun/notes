package github.banana.vm;

/**
 * 类加载过程
 *
 * <p>
 * 1. 加载阶段 Loading
 * Java 将字节码数据从不同的数据源读取到 JVM 中, 并映射为 JVM 认可的数据结构 (Class 对象)
 * 这里的数据源可能是各种各样的形态, 如 jar 文件, class 文件, 甚至是网络数据源等
 * 如果输入数据不是 {@link sun.tools.java.ClassFile} 的结构, 则会抛出 {@link ClassFormatError} 错误
 * 加载阶段是用户参与的阶段, 我们可以自定义类加载器, 去实现自己的类加载过程
 * <p>
 * 2. 链接 Linking
 * 这是核心的步骤, 简单说是把原始的类定义信息平滑地转化入 JVM 运行的过程, 有三个步骤
 * <p>
 * 2.1 验证 Verification
 * 这是虚拟机安全的重要保障, JVM 需要核验字节信息是符合 Java 虚拟机规范的, 否则就被认为是 {@link VerifyError}, 这样就防止了恶意信息或者
 * 不合规的信息危害 JVM 的运行, 验证阶段有可能触发更多 class 的加载
 * 2.2 准备 Preparation
 * 创建类或接口中的静态变量, 并初始化静态变量的初始值
 * 但这里的初始化和下面的显示初始化阶段是有区别的, 侧重点在于分配所需要的内存空间, 不会去执行更进一步的 JVM 指令
 * 2.3 解析 Resolution
 * 这一步会将常量池中的符号引用替换为直接引用
 * 在 Java 虚拟机规范中, 详细介绍了类, 接口, 方法和字段等各个方面的解析
 * <p>
 * 3. 初始化 Initialization
 * 这一步真正去执行类初始化的代码逻辑, 包括静态字段赋值的操作, 以及执行类定义的静态初始化块的逻辑, 编译器在编译阶段就会把这部分逻辑整理好
 * 父类型的初始化逻辑优先于当前类型的初始化逻辑
 * <p>
 * 4. 使用
 * <p>
 * 5. 卸载
 * <p>
 * 双亲委派模型
 * <p>
 * 当前类加载器试图加载某个类型的时候, 除非父加载器找不到相应类型, 否则尽量将这个任务代理给当前加载器的父类加载器去做
 * 使用委派模型的目的是避免重复加载 Java 类型
 * <p>
 * 没有人能够精确的理解和记忆所有信息, 如果碰到这种问题, 有直接答案当然最好, 没有的话, 就说说自己的思路
 * <p>
 * 在 Java 9 中, Jigsaw 项目为 Java 提供了原生的模块化支持, 内建的类加载器和机制发生了明显的变化
 * <p>
 * 从架构的角度一起看看 Java 8 以前各种类加载器的结构
 * 1. 启动类加载器 Bootstrap Class-Loader
 * 加载 jre/lib 下面的 jar 文件, 如 rt.jar
 * 它是个超级公民, 即使是在开启了 Security Manager 的时候, JDK 仍赋予它加载的程序 AllPermission
 * 一般可以使用 {@link ClassLoader#getParent()} 获取父类加载器, 但是在通常的 JDK/JRE 实现中, 扩展类加载器 getParent() 都只能返回 null
 * 2. 扩展类加载器 Extension or Ext Class-Loader
 * 负责加载我们放到 jre/lib/ext 目录下面的 jar 包, 这就是所谓的 extension 机制, 该目录也可以通过设置 "java.ext.dirs" 来覆盖
 * 3. 应用类加载器 Application or App Class-Loader
 * 加载我们最熟悉的 classpath 下面的内容, 一般就是项目中用到的 jar 包等
 * 这里有一个容易混淆的概念, 系统比(System)类加载器, 通常来说, 其默认就是 JDK 内建的应用类加载器, 但它同样是可能修改的
 * 比如 "java -Djava.system.class.loader=com.*.*"
 * 如果我们指定了这个参数, JDK 内建的应用类加载器就会成为定制加载器的父亲, 这种方式通常用在类似需要改变双亲委派模式的场景
 * <p>
 * <p>
 * 类加载的双亲委派模型
 * <p>
 * <pre><code>
 *           Bootstrap Class-Loader     -------->             rt.jar
 *                      ^
 *                      |
 *                      |
 *              Ext Class-Loader        -------->             jre/lib/ext
 *                      ^
 *                      |
 *                      |
 *           App(System) Class-Loader   -------->             classpath
 *              ^              ^
 *              |              |
 *              |              |
 *  Custom Class-Loader  Custom Class-Loader
 * </code></pre>
 * 通常类加载机制有三个基本特征:
 * 1. 双亲委派模型
 * 这是定义的标准类加载模型, 但不是所有类加载都遵守这个模型, 有的时候, 启动类加载器所加载的类型, 是可能要加载用户代码的
 * 比如 JDK 内部的 {@link jdk.internal.module.ServicesCatalog.ServiceProvider}/
 * {@link com.sun.jndi.ldap.ServiceLocator} 机制, 用户可以在标准API框架上, 提供自己的实现
 * JDK 也需要提供些默认的参考实现
 * 例如 Java 中 JNDI, JDBC, 文件系统, Cipher 等很多方面, 都是利用的这种机制, 这种情况就不会用双亲委派模型去加载
 * 而是利用所谓的上下文加载器
 * 2. 可见性
 * 子类加载器可以访问父加载器的类型, 但是反过来是不允许的, 不然因为缺少必要的隔离, 我们就没有办法利用类加载器去实现容器的逻辑
 * 3. 单一性
 * 由于父加载器的类型对于子加载器是可见的, 所以父加载器中加载的类型, 就不会在子加载器中重复加载, 但是要注意, 类加载器"邻居"间,
 * 同一类型仍然可以被加载多次, 因为互相不可见
 * <p>
 * 但是上述方案在 Java 9 中, 由于 Jigsaw 项目引入了 Java 平台模块化(JPMS), Java SE 源代码被划分为一系列模块, 类加载器, 类文件容器等
 * 都发烧了非常大的变化
 * 1. 启动类加载变化
 * -Xbootclasspath参数不可用, API 已经被划分到具体的模块, 所以上下文中, 利用 "-Xbootclasspath/p" 替换某个 Java 核心类型代码
 * 实际上变成了相应的模块进行的修补, 可以采用下面的解决方案
 * 首先确认要修改的类文件已经编译好, 并按照对应的模块(假设 java.base)结构存放, 然后给模块打补丁 "java --patch-module java.base=*path app"
 * 2. 扩展类加载器变化
 * 扩展类加载器被重命名为平台类加载器(Platform Class-Loader), 而且 extension 机制则被移除
 * 也就意味着, 如果我们指定 java.ext.dirs 环境变量, 或者 lib/ext 目录存在, JVM 将直接返回错误, 建议解决方法是将其放入普通的 classpath 中
 * 3. 权限管理变化
 * 部分不需要 {@link java.security.AllPermission} 的 Java 基础模块, 被降级到平台类加载器中, 相应的权限也被更精细粒度地限制起来
 * 4. 核心类库变化
 * rt.jar 和 tools.jar 同样被移除
 * JDK 的核心库以及相关资源, 被存储在 jimage 文件中, 并通过新的 JRT 文件系统访问, 而不是原有的 jar 文件系统
 * 虽然看起来很惊人, 但幸好对于大部分软件的兼容性影响, 其实是有限的
 * 更直接地影响是 IDE 等软件, 通常只要升级到新版本就可以了
 * 5. 新增 Layer 层抽象
 * JVM 启动默认创建 BootLayer, 开发者也可以自己去自定义和实例化 Layer, 可以更加方便的实现类似容器一般的逻辑抽象
 * <p>
 * <p>
 * 结合Layer, 目前的 JVM 内部结构就变成了下面的层次, 内建类加载器都在 BootLayer 中, 其它 Layer 内部有自定义的类加载器, 不同版本模块可以同时
 * 工作在不同的 Layer, 新的类加载机制如下所示:
 *
 * <pre><code>
 * |---------------------------------------------------------------------------------------
 * |                Custom loader1            |          Custom loader2                    |
 * |                hadoop                    |         custom module                      |
 * |                                          |                                            |
 * |                Hadoop Layer              |         JavaScript Layer                   |
 * |---------------------------------------------------------------------------------------
 * |         Bootstrap loader            Platform loader         Application loader        |
 * |        (java.base, java.logging)   (java.net.http)         (classpath)                |
 * |                                                                                       |
 * |                                        BootLayer                                      |
 * |---------------------------------------------------------------------------------------
 * |                                    Java 平台模块化系统                                  |
 * |---------------------------------------------------------------------------------------
 * |                                        Java 虚拟机                                     |
 * |---------------------------------------------------------------------------------------
 * </code></pre>
 * <p>
 * 自定义类加载器常见的场景
 * 1. 实现类似进程内隔离
 * 类加载器实际上用作不同的命名空间, 以提供类似的容器, 模块化的效果
 * 例如两个模块依赖于某个类库的不同版本, 如果分别被不同的容器加载, 就可以互相不干扰, 这个方面的集大成者是 Java EE 和 OSGI, JPMS等框架
 * 2. 类定义信息
 * 应用更需要从不同的数据源获取类定义信息, 例如网络数据源, 而不是本地文件系统
 * 3. 操作字节码
 * 需要自己操纵字节码, 动态修改或者生成类型
 * <p>
 * 我们可以总体上简单理解自定义类加载过程:
 * 1. 指定名称
 * 通过指定名称, 找到其二进制实现, 这里往往就是自定义类加载器会"定制"的部分, 例如在特定数据根据名字获取字节码, 或者修改或者生成字节码
 * 2. 创建 Class 对象
 * 然后创建 Class 对象, 并完成类加载过程
 * 二进制信息到 Class 对象的转换, 通常依赖 {@link ClassLoader#defineClass(String, byte[], int, int)} 我们无需自己实现
 * 它是 final 方法, 有了 Class 对象, 后续完成加载过程就顺利成章了
 * <p>
 * 降低类加载的开销
 * 1. AOT
 * 相当于直接编译成机器码, 降低的其实主要是解释和编译的开销
 * 但是目前还是个试验性的, 支持的平台也有限, 比如 JDK 9 仅支持 Linux x64 平台, 所以局限性太大
 * 2. AppCDS
 * Application Class-Data Sharding, CDS 在 Java 5 中被引进, 但仅限于 Bootstrap Class-loader
 * 在8u40中实现了 AppCDS, 支持其他的类加载器, 在目前 2018 年初发布的 JDK 10 中已经开源
 */
public class ClassLoadTest {
    // 三种变量的区分, 其实一般就两种, 普通静态变量和静态常量, 常量就是携带 final 关键字的变量, 因为这个静态相当于是不可改变的
    // 编译器就能知道变量的值
    // 普通静态变量
    public static int a = 100;
    // 原始类型静态常量
    public static final int A = 1000;
    // 引用类型静态常量
    public static final Integer B = 10000;

    public static void main(String[] args) {
        // 反编译后字节码查阅得知
        /*
         *  0: bipush        100
         *  2: putstatic     #2                  // Field a:I
         *  5: sipush        10000
         *  8: invokestatic  #3                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
         * 11: putstatic     #4                  // Field B:Ljava/lang/Integer;
         */
        // 可以清楚的看到 变量 a 和变量 B 都需要借助额外调用 putstatic 指令操作, 所以只能在显示初始化, 即第三步进行的
        // 只有原始类型静态常量在编译时确定
    }
}

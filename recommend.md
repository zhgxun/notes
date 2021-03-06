# 推荐阅读

## 一、PHP

1、[使用phar上线你的代码包](https://segmentfault.com/a/1190000002166235)

其实这种方法没有什么特别的限制，只有一条，你的程序是单一入口的，对web项目也就是说，所有的http请求都只有一个php文件作为处理方（大多数程序就是index.php）。

如果你的程序结构是这样的，那么基本可以无障碍切换到这种上线模式。实际上绝大多数现代的php框架构建的项目都是单一入口的结构了。

2、[利用dns解析来实现网站的负载均衡](https://segmentfault.com/a/1190000002578457)

其实DNS可以玩的花样远不止这些，还可以做故障转移，也可以按地区解析等等。域名从互联网诞生之初就开始存在了，但是对它的研究以及衍生出来的使用方法才刚刚开始发掘，随着大家对互联网利用的提升，这类技术肯定会越来越多。

3、[邮件发送服务AWS SES，Mailgun以及SendCloud](https://segmentfault.com/a/1190000000340133)

前段时间看到知乎把邮件服务从Mailgun部分迁移到SendCloud了，其实我也建议大家这么做。针对国内部分邮箱，使用SendCloud来发，其它邮箱就用Mailgun。如果是群发周报这种对到达率要求不高的邮件，则可以选用SES这种价格便宜量又足的服务。最后几点忠告

* DKIM是必须要做的
* 把你的发信域名与主域名分开，用子域名比如mail.xxx.com，这样如果某个域名不慎被封还有回旋的余地
* 如果是土豪，建议单独购买独立ip的发信服务，这样被封杀的可能性最小，而且有了ip就可以跟服务商谈加入白名单了。目前Mailgun和SendCloud都有这个服务。

4、[用PHP实现一个Amazon SES的代理服务器](https://segmentfault.com/a/1190000000264774)

SES的全称是Simple Email Service，它是亚马逊公司推出的一个邮件基础服务。作为AWS基础服务的一部分，它继承了AWS的传统优势 -- 便宜。

5、[从输入URL到页面加载发生了什么](https://segmentfault.com/a/1190000006879700)

查找www.google.com的IP地址过程。首先在本地域名服务器中查询IP地址，如果没有找到的情况下，本地域名服务器会向根域名服务器发送一个请求，如果根域名服务器也不存在该域名时，本地域名会向com顶级域名服务器发送一个请求，依次类推下去。直到最后本地域名服务器得到google的IP地址并把它缓存到本地，供下次查询使用。从上述过程中，可以看出网址的解析是一个从右向左的过程: `com -> google.com -> www.google.com`。但是你是否发现少了点什么，根域名服务器的解析过程呢？事实上，真正的网址是www.google.com.，并不是我多打了一个.，这个.对应的就是根域名服务器，默认情况下所有的网址的最后一位都是.，既然是默认情况下，为了方便用户，通常都会省略，浏览器在请求DNS的时候会自动加上，所有网址真正的解析过程为: `. -> .com -> google.com. -> www.google.com.`。

6、[PHP 程序员进阶学习书籍参考指南](https://mp.weixin.qq.com/s?__biz=MzA3MjEyNTE4MQ==&mid=2652726194&idx=1&sn=ad0c1f99b6d8dd9a1a07651d5c485370&chksm=84cadc20b3bd55361b2bf392a84fff9334473c29017614540af922efcae2aef84e65970855fe&scene=27#wechat_redirect)

7、[PHP版本支持](http://php.net/supported-versions.php)

PHP每个版本都有官方维护的生命周期。


## 二、MySQL
1、[MySQL索引专题一 认识索引](https://segmentfault.com/a/1190000010264071)

1. B-Tree索引使用最广泛，主流引擎都支持。
2. 哈希索引性能高，适用于特殊场合。
3. R-Tree不常用。
4. 全文索引适用于海量数据的关键字模糊搜索。
5. B-Tree只是底层的算法实现，唯一索引，主键索引，普通索引都是基于B-Tree索引算法的


2、[MySQL大表优化方案](https://segmentfault.com/a/1190000006158186)

除非单表数据未来会一直不断上涨，否则不要一开始就考虑拆分，拆分会带来逻辑、部署、运维的各种复杂度，一般以整型值为主的表在千万级以下，字符串为主的表在五百万以下是没有太大问题的。而事实上很多时候MySQL单表的性能依然有不少优化空间，甚至能正常支撑千万级以上的数据量。

## 三、Swoole

1、[Swoole编程指南-2.9 Process进程](http://www.catplanet.me/?id=11)

Process的signal方法是一个异步方法，其底层会开启事件循环，因此使用了signal方法的进程在主代码执行完后并不会主动退出，需要调用exit、发送信号等方式关闭。

1、[Swoole编程指南-4.3 Nginx代理配置](http://www.catplanet.me/?id=17)

Swoole用作HttpServer虽然性能已经足够强大，但是还是不能直接替代传统的Web服务器比如Nginx。Swoole的强大之处只在于处理PHP逻辑的能力上。因此，当我们需要开发Web服务的时候，最合适的方法就是结合使用Nginx和Swoole，从而取代fpm。

## 四、Go

1、[Google Go 语言从入门到应用所需要的开源项目](https://www.ctolib.com/topics-102797.html)

由于目前国内并没有比较好的 Go 语言书籍，而国外的优秀书籍因为英文的缘故在一定程度上也为不少 Go 语言爱好者带来了一些学习上的困扰，尽管《The Way to Go》这本名对目前 Go 语言版本来说有小部分内容相对过时，但是为当下不可多得的好书，部分内容已获得作者同意根据当前 Go 语言版本进行修改。并且以开源的形式免费分享给有需要的 Go 语言爱好者。

2、[Go包管理的前世今生](http://www.infoq.com/cn/articles/history-go-package-management)

Golang团队成员也召开了大会，非常赞同社区里各种包管理工具的理念，确实有必要对包管理提出一个统一的规则，来解决上面的问题。但是问题不是没有规则，而是规则太多了。往往就是一个意见不合，一下子就杀出来一个新的工具。仅官方推荐的包管理工具就有15种之多。

3、[Golang错误和异常处理的正确姿势](http://www.jianshu.com/p/f30da01eea97)

错误和异常是两个不同的概念，非常容易混淆。很多程序员习惯将一切非正常情况都看做错误，而不区分错误和异常，即使程序中可能有异常抛出，也将异常及时捕获并转换成错误。从表面上看，一切皆错误的思路更简单，而异常的引入仅仅增加了额外的复杂度。
但事实并非如此。众所周知，Golang遵循“少即是多”的设计哲学，追求简洁优雅，就是说如果异常价值不大，就不会将异常加入到语言特性中。

错误和异常处理是程序的重要组成部分，我们先看看下面几个问题：

1. 错误和异常如何区分？
2. 错误处理的方式有哪几种？
3. 什么时候需要使用异常终止程序？
4. 什么时候需要捕获异常？
5. ...

4、[Go支付宝实现](https://github.com/smartwalle/alipay)

支付宝 AliPay SDK for Go, 集成简单，功能完善。

## 五、架构

1、 [如何解决分布式集群会话？](https://mp.weixin.qq.com/s/b8O2to5XqNluBImNdn7GPA)

Session集中存储，session越多、tomcat容器越多的时候他的优势就更明显、没有单点故障的问题。扩展方便、适合集群数据量大使用。需要接入redis、mysql集群维护、增加网络开销。

## 六、Java

1、[多研究些架构，少谈些框架](https://segmentfault.com/a/1190000013018777)

2、[HashMap的工作原理](http://www.importnew.com/7099.html)

HashMap基于hashing原理，我们通过put()和get()方法储存和获取对象。当我们将键值对传递给put()方法时，它调用键对象的hashCode()方法来计算hashcode，让后找到bucket位置来储存值对象。当获取对象时，通过键对象的equals()方法找到正确的键值对，然后返回值对象。HashMap使用链表来解决碰撞问题，当发生碰撞了，对象将会储存在链表的下一个节点中。 HashMap在每个链表节点中储存键值对对象。

3、[Netty入门教程](https://www.jianshu.com/p/b9f3f6a16911)

Netty 是一个利用 Java 的高级网络的能力，隐藏其背后的复杂性而提供一个易于使用的 API 的客户端/服务器框架。

4、[Netty 拆包粘包和服务启动流程分析](http://www.cnblogs.com/itdragon/p/8365694.html)

Channel : Netty最核心的接口。NIO通讯模式中通过Channel进行Socket套接字的读，写和同时读写操作。

ChannelHandler : 因为直接使用Channel会比较麻烦，所以在Netty编程中通过ChannelHandler间接操作Channel，从而简化开发。

ChannelPipeline : 可以理解为一个管理ChandlerHandler的链表。对Channel进行操作时，Pipeline负责从尾部依次调用每一个Handler进行处理。每个Channel都有一个属于自己的ChannelPipeline。

ChannelHandlerContext : ChannelPipeline通过ChannelHandlerContext间接管理每个ChannelHandler。

5、[Netty序章之BIO NIO AIO演变](http://www.cnblogs.com/itdragon/p/8337234.html)

IO：阻塞同步通信模式，客户端和服务器连接需要三次握手，使用简单，但吞吐量小；NIO：非阻塞同步通信模式，客户端与服务器通过Channel连接，采用多路复用器轮询注册的Channel。提高吞吐量和可靠性；AIO：非阻塞异步通信模式，NIO的升级版，采用异步通道实现异步通信，其read和write方法均是异步方法。

## 七、安全

1、 [Token 认证的来龙去脉](https://segmentfault.com/a/1190000013010835)

如果我们把所有状态信息都附加在 Token 上，服务器就可以不保存。但是服务端仍然需要认证 Token 有效。不过只要服务端能确认是自己签发的 Token，而且其信息未被改动过，那就可以认为 Token 有效——“签名”可以作此保证。平时常说的签名都存在一方签发，另一方验证的情况，所以要使用非对称加密算法。但是在这里，签发和验证都是同一方，所以对称加密算法就能达到要求，而对称算法比非对称算法要快得多（可达数十倍差距）。更进一步思考，对称加密算法除了加密，还带有还原加密内容的功能，而这一功能在对 Token 签名时并无必要——既然不需要解密，摘要（散列）算法就会更快。可以指定密码的散列算法，自然是 HMAC。

2、 [保证分布式系统数据一致性的6种方案](https://segmentfault.com/a/1190000012973658)

在工程实践上，为了保障系统的可用性，互联网系统大多将强一致性需求转换成最终一致性的需求，并通过系统执行幂等性的保证，保证数据的最终一致性。但在电商等场景中，对于数据一致性的解决方法和常见的互联网系统（如 MySQL 主从同步）又有一定区别，群友的讨论分成以下 6 种解决方案。

## 七、工具

1、[IntelliJ IDEA 注册码](http://idea.lanyus.com)

注册码有效期为2017年10月15日至2018年10月14日，使用前请将“0.0.0.0 account.jetbrains.com”添加到hosts文件中。

2、[Nginx 教程 #1：基本概念](https://www.oschina.net/translate/nginx-tutorial-basics-concepts)

Nginx 最初是作为一个 Web 服务器创建的，用于解决 C10k 的问题。作为一个 Web 服务器，它可以以惊人的速度为您的数据服务。但 Nginx 不仅仅是一个 Web 服务器，你还可以将其用作反向代理，与较慢的上游服务器（如：Unicorn 或 Puma）轻松集成。你可以适当地分配流量（负载均衡器）、流媒体、动态调整图像大小、缓存内容等等。

基本的 nginx 体系结构由 master 进程和其 worker 进程组成。master 读取配置文件，并维护 worker 进程，而 worker 则会对请求进行实际处理。

3、[Nginx 教程 #2：性能](https://www.oschina.net/translate/nginx-tutorial-performance)

worker_process 指令会指定：应该运行多少个 worker。默认情况下，此值设置为 1。最安全的设置是通过传递 auto 选项来使用核心数量。

但由于 Nginx 的架构，其处理请求的速度非常快 - 我们可能一次不会使用超过 2-4 个进程（除非你正在托管 Facebook 或在 nginx 内部执行一些 CPU 密集型的任务）。

4、[Nginx 教程 #3：SSL 设置](https://www.oschina.net/translate/nginx-tutorial-ssl-setup)

SSL（Socket Secure Layer 缩写）是一种通过 HTTP 提供安全连接的协议。

SSL 1.0 由 Netscape 开发，但由于严重的安全漏洞从未公开发布过。SSL 2.0 于 1995 年发布，它存在一些问题，导致了最终的 SSL 3.0 在 1996 年发布。

TLS（Transport Layer Security 缩写）的第一个版本是作为 SSL 3.0 的升级版而编写的。之后 TLS 1.1 和 1.2 出来了。现在，就在不久之后，TLS 1.3 即将推出（这确实值得期待），并且已经被一些浏览器所支持。

从技术上讲，SSL 和 TLS 是不同的（因为每个协议都描述了协议的不同版本），但其中使用的许多名称是可以互换的。

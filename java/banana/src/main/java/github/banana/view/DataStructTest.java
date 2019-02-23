package github.banana.view;

import java.util.List;

/**
 * 算法和数据接口
 * <p>
 * 1. 数组
 * 一种线性表数据结构, 用一组连续的内存空间, 存储一组具有相同类型的数据
 * <p>
 * 线性表是数据排列成一条线一样的数据结构, 每个线性表上的数据最多只有前和后两个方向, 其实链表, 队列, 栈也都是线性表, 相对应的就是非线性表,
 * 比如树, 图, 堆等, 因为数据不是简单的前后关系
 * <p>
 * 数据因为具有连续的内存地址和相同的数据类型, 故只需要知道数组的首地址, 就可以随机根据数据下标访问数据, 但是插入和删除, 则需要大量数据搬移
 * <p>
 * 因此数组的插入和删除操作衍生出了一些高效的方案
 * 比如 插入数据时, 仅仅是将待插入的位置的元素移动到数组的末尾, 避免了数据搬移
 * 而删除也是类似的道理, 不直接删除数据, 而是将其进行标记为删除状态, 待需要时在搬移数据进行删除, 类似垃圾回收中的标记清除, 在特定的时候统一进行
 * 数据搬移, 而不是每次都搬移数据
 * <p>
 * 数组还有个特点是大小是固定的, 搬移数据需要申请一份大的内存空间, 将旧数据拷贝到新的内存地址, 资源消耗相当大
 * <p>
 * 2. 缓存
 * 缓存的大小有限, 当缓存被用满时, 需要使用缓存淘汰策略, 常见的策略有三种:
 * FIFO 先进先出策略
 * LFU 最少使用策略
 * LRU 最近最少使用策略
 * <p>
 * 3. 链表
 * 链表通过指针将一组零散的内存块串联起来使用
 * 最常见的三种: 单链表, 双链表和循环链表
 * <p>
 * 4. 栈
 * 特定的数据结构对应特定的场景, 数据或链表暴露了太多的操作接口
 * 栈主要包括2个操作, 入栈和出栈, 栈顶插入一个数据栈顶删除一个数据
 * 栈既可以用数组实现, 也可以用链表实现
 * 用数组实现的栈叫顺序栈, 用链表实现的栈叫链式栈
 * 我们说空间复杂度的时候, 是指除了原本的数据存储空间, 算法运算还需要额外的存储空间, 这部分才算空间复杂度
 * {@link ArrayStack} 参考这个简单的实现
 * <p>
 * 5. 队列
 * 入队, 放一个数据到队列尾部
 * 出队, 从队列头部取一个元素
 * 队列跟栈一样, 也是一直操作受限的线性表数据结构
 * 作为一种基础的数据结构, 队列在应用也非常方便, 特别是一些具有某些额外特性的队列, 比如循环队列, 阻塞队列, 并发队列
 * 它们在很多偏底层系统, 框架, 中间件的开发中起着关键性的作用, 比如 Java 中的 {@link java.util.concurrent.ArrayBlockingQueue}, 甚至
 * 实现了公平锁和非公平锁的实现
 * <p>
 * 用数组实现的队列是顺序队列, 用链表实现的队列是链式队列
 * {@link ArrayQueue} 队列简单实现
 * 对于栈来说, 只需要一个栈顶指针即可, 但是对于队列来说, 需要两个指针来标识队列的头和尾
 * <p>
 * 队列也有个问题, 即是不断的入队到队列满时, 尾指针达到最大值, 头指针也达到最大值, 即元素都出队结束, 队列其实为空, 但是却不可用
 * 数据搬移, 类似扩索容操作, 重新初始化队列的头尾指针
 * <p>
 * 环形队列
 * 针对数组, 访问比较快速, 但是数据搬移操作实际上比较耗资源
 * 因此衍生出环形队列, 变相的解决数据搬移操作, 但依然存在的问题是, 队列满时无法再继续入队数据了
 * 循环队列队列满的判断比较特殊 (tail + 1) % n = head
 * 队列为空依然是 head = tail
 * 环形队列会浪费一个数组位置的空间
 * <p>
 * 衍生出 Java 自身提供的阻塞队列 {@link java.util.concurrent.ArrayBlockingQueue}, 并发队列等实现
 * <p>
 * 线程池没有空闲线程时一般有两种处理策略, 一种是直接拒绝请求, 另一种是阻塞的处理方式, 将请求排队等待有空闲线程时取出排队的线程继续处理
 * <p>
 * 如何存储排队的请求?
 * 队列这种数据结构一般是先进先出就比较适合排队
 * 但是有一点需要留意, 链表队列其实是不限制长度的, 如果请求响应等待时间比较长, 会影响服务, 可以直接拒绝或者更好的解决方案
 * <p>
 * 对于大部分资源有限的场景, 当没有空闲资源时, 基本上都可以通过队列这种数据结构来解决
 * <p>
 * 5. 排序算法
 * 排序算法时间复杂度概览
 * |----------------------------------------
 * | 排序算法名称     | 时间复杂度 | 是否基于比较
 * |----------------------------------------
 * | 冒泡, 插入, 选择 | O(n^2)    | √
 * |----------------------------------------
 * | 快排, 归并       | O(nlogn) | √
 * |----------------------------------------
 * | 桶, 计数, 基数   | O(n)     | ×
 * |----------------------------------------
 * 需要注意的是 O(n) 和 O(nlogn) 的大小其实实际上还是不那么明显, 大多数时候 logn 其实是一个很小的数, 只是理论上
 * 要比 O(n) 而已
 * <p>
 * 实际的项目开发里, 插入排序会比冒泡排序得到更多的应用
 * <p>
 * 如何分析一个算法的执行效率
 * 5.1 最好情况, 最坏情况, 平均情况的时间复杂度
 * 5.2 时间复杂度常数, 系数和低阶
 * 5.3 比较次数和交换次数(意味着数据被频繁移动的次数)
 * <p>
 * 排序算法的内存消耗
 * 原地排序, 就是指空间复杂度为 O(1) 的排序算法, 就是说排序不需要借助额外的内存空间即可完成排序
 * <p>
 * 排序的稳定性
 * 待排序的序列中相同的元素, 排序之后, 元素之间先后顺序不改变, 因为在数组中位置改变, 意味着一次数据的交换
 * <p>
 * 比如要给电商交易系统中订单排序, 订单有两个属性, 下单时间和交易金额
 * 如果我们现在有10万条订单数据, 希望按照金额从小到大对订单数据排序, 对于有相同金额的订单, 希望按照下单时间从早到晚排序
 * <p>
 * 预想是先按照订单金额排序, 再遍历订单数据, 对金额相同的订单按时间排序, 思路理解起来不难, 但是实现起来非常困难, 因为数据都是无法预知的
 * <p>
 * 借助稳定排序算法, 先按照下单时间给订单排序, 注意要按下单时间不是金额, 排序完毕后再用稳定排序算法, 按照金额排序, 两遍排序之后订单数据就
 * 是按金额从小到大, 相同金额的订单按下单时间从早到晚排序的数据了
 * <p>
 * 稳定排序算法可以保持金额相同的两个对象, 在排序之后的前后顺序不变
 * <p>
 * 5.4 冒泡排序
 * 是稳定的原地排序
 * <p>
 * 5.5 插入排序
 * 将数组中的数据分为两个区间, 已排序区间和未排序区间
 * 初始已排序区间只有一个元素, 就是数组的第一个元素
 * 插入排序的核心思想是取未排序区间中的元素, 在已排序区间中找到合适的插入位置将其插入, 并保证已排序区间数据一直有序, 重复这个过程, 直到
 * 未排序区间中元素为空, 算法结束
 * <p>
 * 插入排序也包含两种操作, 一种是元素的比较, 一种是元素的移动
 * 当我们需要将一个数据插入到已排序区间时就要拿这个数据与已排序区间的数据一次比较大小, 找到合适的位置, 找到插入点之后, 还需要将插入点之
 * 后的元素顺序往后移动一位, 这样才能腾出位置为当前数据插入
 * 插入的过程其实就是一个互相间数据交换的过程, 一次只能交换一个位置
 * <p>
 * 插入排序也是原地稳定的排序算法
 * <p>
 * 5.6 选择排序
 * 跟插入排序思路类似, 也是将数组分为两个已排序区间和未排序区间, 不同之处在于选择排序每次都会从未排序区间找到最小的元素, 将其放到已排序区间的末尾
 * 原地不稳定的排序算法, 会对相同的元素进行交换, 因此相对插入排序和冒泡排序, 选择排序这点就稍显逊色了
 * <p>
 * 冒泡排序跟插入排序相比不管怎么优化元素的交换次数都省不了, 因此实际表现上插入排序会比冒泡排序要优的多
 * <p>
 * 总体上冒泡排序, 插入排序, 选择排序时间复杂度都是 O(n^2), 只适合小规模数据的排序, 接下来这两种排序算法归并排序和快速排序时间复杂度
 * 都是 O(nlogn) 适合大规模数据排序
 * <p>
 * 5.7 归并排序
 * <p>
 * 归并排序和快速排序都用到了分治思想, 非常巧妙
 * <p>
 * 归并排序的核心思想还是蛮简单, 如果要排序一个数组, 先把数组从中间分成前后两部分, 然后对前后两部分分别排序, 再将排好序的两部分合并在一起
 * 这样整个数组就都有序了
 * <p>
 * 分而治之就是将一个大问题分解成小的子问题来解决, 小的子问题解决了, 大问题也就解决了
 * 分治思想跟递归很像, 分治算法一般用递归来实现, 分治是一种解决问题的处理思想, 递归是一种编程技巧
 * <p>
 * 归并排序有个缺点是最后合并两个区间时, merge(arr[p...r], arr[p...q], arr[q+1...r])
 * 就是说最后排序完毕还需要申请一段内存空间来保存最后一次的数据, 不停将两个有序的数组移动到一个固定的临时数组中, 最后再把临时数组中的数据拷贝回
 * 原始数组
 * <p>
 * 归并排序是一个稳定的排序算法
 * <p>
 * 5.8 快速排序
 * 习惯性简称快排, 快排利用的也是分治思想, 初看起来有点像归并排序, 但是思路其实完全不一样
 * 核心思想是如果要排序的数组中下标从p到r之间的一组数据, 我们选择p到r之间的任意一个数据作为 pivot 分区点
 * 我们遍历p到r之间的数据, 将小于 pivot 的放到左边, 将大于 pivot 的放到右边, 将 pivot 放在中间, 经过这一步之后数组就被分为三部分
 * 前面p到q-1之间的都小于 pivot, 中间 pivot, 后面 q+1到r之间都大于 pivot
 * <p>
 * 然后根据分治的思想, 区间继续递归, 直到区间缩小为1就说明所有数据都排序完毕
 * 要完成原地操作, 避免占用额外的内存空间
 * <p>
 * 快速排序不是稳定的排序算法
 * <p>
 * 归并排序的处理过程是由下到上, 先处理子问题然后在合并
 * 快排刚好相反, 处理过程由上到下, 先分区, 在处理子问题
 * <p>
 * 理解归并排序的重点是理解递推公式和合并函数
 * 理解快排的重点是理解递推公式和分区函数, 分区比较讲究
 * <p>
 * 归并排序在任何情况下时间复杂度都是比较稳定的排序算法, 这也是它致命的缺点, 即归并排序不是原地排序, 空间复杂度比较高, 因此没有快排用广泛
 * 快排在最坏的情况下时间复杂度是 O(n^2), 但是平均情况下时间复杂度都是 O(nlogn), 快速退化到 O(n^2) 的概率非常小, 可以通过合理的分区函数来避免
 * <p>
 * 6. 线性排序
 * 接下来就是见证奇迹的时刻, 三种特殊用途, 时间复杂度是 O(n) 的排序算法通排序, 计数排序和基数排序
 * <p>
 * 这些排序算法的时间复杂度是线性的, 所以把这类排序算法叫做线性排序, 之所以能做到线性的时间复杂度, 主要原因是这三个算法是非基于比较的排序算法,
 * 都不涉及元素之间的比较操作
 * <p>
 * 这几种排序算法理解起来都不难, 时间空间复杂度分析起来也很简单, 但是对要排序的数据要求很苛刻, 重点掌握这些排序算法的适用场景
 * <p>
 * 6.1 桶排序
 * 核心思想是将要排序的数据分到几个有序的桶里, 每个桶里的数据再单独进行排序, 桶内排序完毕后再把每个桶里的数据按照顺序依次取出, 组成的序列就
 * 是有序的了
 * <p>
 * 桶里可以使用快排等排序思路进行排序
 * <p>
 * 桶排序对数据要切分成严苛
 * 要排序的数据需要很容易就能划分到顺序的桶里, 并且桶与桶之间有着天然的顺序, 这样每个桶内的数据都排序完毕后, 桶与桶之间的数据不需要在进行排序
 * 数据在各个桶之间分布是比较均匀的, 如果数据经过桶的划分之后, 有些桶里的数据非常多, 有些非常少, 那桶内排序的时间复杂度就不是常数级, 极端
 * 情况下, 数据都在一个桶内, 就退化为大桶内的排序了
 * <p>
 * 桶排序比较适合在外部排序中使用, 外部排序就是数据存储在外部磁盘中, 数据量比较大, 内存有限, 无法将数据全部加载到内存中, 比如我们有10万
 * 的订单数据, 希望按金额进行排序, 但内存有限, 只有几百M, 没办法一次性排序, 就只能按订单金额, 来将订单数据划分到不同的通中, 使用桶排序即可
 * 但是如果订单金额分布不均匀, 就不要选择适合的通算法来平衡不同桶内的数据量
 * <p>
 * 6.2 计数排序
 * <p>
 * 计数排序其实是桶排序的一种特殊情况
 * <p>
 * 当要排序的n个数据, 所处的范围并不大的时候, 比如最大值是k, 我们就可以把数据划分成k个桶, 每个桶内的数据值都是相同的, 省掉了桶内排序的时间
 * 比如高考, 总分加入为900分, 就可以将考生的成绩划分到900个桶内处理
 * <p>
 * 计数排序的算法比较巧妙, 后续再研究
 * <p>
 * 计数排序只能用在数据范围不大的场景中, 如果数据范围k比要排序的数据n大很多, 就不适合计数排序了
 * 而且技术排序只能给非负整数排序, 如果要排序的数据是其它类型的, 要将其在不改相对大小的情况下转化为非负整数
 * <p>
 * 6.3 基数排序
 * <p>
 * 对10万个手机号码进行排序, 11位的数字太大, 桶排序和计数排序就不适合, 衍生了基数排序
 * 对每一位进行排序时需要使用稳定的排序算法, 相同数据的位置不能改变, 比如手机号前面的数字大就不用比较后面的数字, 即数字本身具有天然的大小
 * <p>
 * 有时候排序的数据并不是等长的, 比如单词, 但可以类似补零的方式来实现数据等长
 * <p>
 * 基数排序对要排序的数据是有要求的, 需要可以分割出独立的位来比较, 比如单词的字母, 手机号码的每一位, 而且位之间有递进的关系, 即天然的大小性
 * 如果a数据的高位比b数据的高位大, 那剩下的地位就不用比较了
 * 除此之外, 每一位的数据范围不能太大, 要可以用线性排序算法来排序, 否则基数排序的时间复杂度就无法做到 O(n)
 * <p>
 * 7. 如何实现一个通用的高性能的排序函数
 * 排序算法一览表
 * |------------------------------------------------
 * |         | 时间复杂度         | 稳定排序 | 原地排序
 * |------------------------------------------------
 * | 冒泡排序 | O(n^2)            | √       | √
 * |------------------------------------------------
 * | 插入排序 | O(n^2)            | √       | √
 * |------------------------------------------------
 * | 选择排序 | O(n^2)            | ×       | √
 * |------------------------------------------------
 * | 快速排序 | O(nlogn)          | ×       | √
 * |------------------------------------------------
 * | 归并排序 | O(nlogn)          | √       | ×
 * |------------------------------------------------
 * | 计数排序 | O(n+k) k是数据范围 | √        | ×
 * |------------------------------------------------
 * | 桶排序   | O(n)             | √        | ×
 * |------------------------------------------------
 * | 基数排序 | O(dn) d是维度     | √        | ×
 * |------------------------------------------------
 * <p>
 * 7.1 线性排序时间复杂度比较低, 适用场景比较特殊, 所以要写一个通用的排序函数, 不能选择线性排序算法
 * 7.2 如果对小规模的数据进行排序, 可以选择时间复杂度是 O(n^2) 的算法
 * 如果对大规模的数据进行排序, 时间复杂度是 O(nlogn) 的算法更加高效
 * 所以为了兼顾任意规模数据的排序, 一般都会首选时间复杂度是 O(nlogn) 的排序算法来实现排序函数
 * <p>
 * 时间复杂的是 O(nlogn) 的排序算法不止一个, 有归并和快排, 还有堆排序
 * 堆排序和快速排序都有很多方面的应用, 比如 Java 语言采用堆排序实现排序函数, C语言使用快速排序实现排序函数
 * <p>
 * 使用归并排序的情况其实并不多, 因为最好和平均的时候时间复杂度不变, 还有就是需要申请新的内存空间地址
 * <p>
 * 7.3 快速排序比较适合实现排序函数
 * 快排在最糟糕的情况下时间复杂度是 O(n^2), 但是这个复杂度恶化是可以优化的
 * <p>
 * 如何优化快排?
 * 最理想的分区点, 被分区点分开的两个分区中, 数据的数量差不多
 * <p>
 * 三数取中法
 * 从区间的首尾中间分别取出一个数比较大小, 用中间值作为分区点, 还有类似的五数取中, 十数取中等
 * <p>
 * 随机法
 * 每次从要排序的区间随机选择一个元素作为分区点, 从概率上看, 平均选择的分区都是比较好的
 * <p>
 * 可以分析Java的排序算法, 有两个
 * {@link java.util.Arrays#sort(int[])} {@link java.util.Collections#sort(List)}
 * <p>
 * 参考解读: https://time.geekbang.org/column/article/42359
 * <p>
 * 8. 二分查找
 * <p>
 * 二分查找应用场景的局限性
 * 二分查找依赖的是顺序表结构, 简单点说就是数组, 因为二分查找需要随机访问下标, 否则数组外的数据结构无该特性
 * 二分查找针对的是有序数据, 数据必须是有序的, 否则需要先进行一次排序后才能使用二分查找
 * 排序成本可以均摊, 如果是静态数据还好, 如果数据有频繁的修改删除, 每次都触发重排序, 则成本相当高
 * 数据量太小, 也不适合二分查找, 遍历就够了
 * 数据量太大也不适合二分查找, 二分查找依赖数组, 比如1G的文件, 内存中可能无法申请到这么大连续的内存空间
 * <p>
 * 二分查找的变形问题
 * <p>
 * 十个二分九个错
 * <p>
 * 唐纳德-克努特在计算机程序设计艺术的第三卷排序和查找中说道: 尽管第一个二分查找算法于1946年出现, 然而第一个完全正确的二分查找算法实现直到
 * 1962年才出现
 * <p>
 * 4种常见的二分查找变形问题
 * 普通的二分查找
 * {@link DataStructTest#binSearch(int[], int)}
 * <p>
 * 8.1 查找第一个值等于给定值的元素
 * 其实变形的二分查找也不是那么的难, 主要是太追求完美, 其实效率跟阅读比起来, 简单易懂更好一些, 除非是在性能追求的场合, 必须精确到最优的时候
 * {@link DataStructTest#binSearch1(int[], int)}
 * <p>
 * <p>
 * 8.2 查找最后一个值等于给定值的元素
 * 类似第一种, 往后寻找即可
 * <p>
 * 8.3 查找第一个大于等于给定值的元素
 * <p>
 * 8.4 查找最后一个小于等于给定值的元素
 * <p>
 * 针对变种, 其实一个元素的关系就大于, 等于, 小于三种情况, 所有操作其实都在这些边界上做文章即可获得结果
 * <p>
 * 9. 跳表
 * 一般的数据结构和算法书不怎么提跳表, 但它是一种各方面性能都比较优秀的动态数据结构, 可以支持快速的插入, 删除, 查找操作, 写起来也不复杂,
 * 甚至可以代替红黑树
 * <p>
 * Redis 中的有序集合就是用跳表来实现的, 如果你有一定的基础, 应该知道红黑树也可以实现快速插入, 删除, 查找操作
 * <p>
 * 链表加多级索引的结构就是跳表
 *
 * <pre><code>
 *
 *   跳表的结构, 即每隔两个元素提取一个指针, 在数据量大的时候, 检索非常快
 *   每个元素都用给一个下降指针关联到原始数据上, 便于快速取出数据
 *
 *   1 ----------------> 7 ------------------> 13                第二级索引
 *  down                down                  down
 *   |                   |                     |
 *   |                   |                     |
 *   |                   |                     |
 *   v                   v                     v
 *   1 ------> 4 ------> 7 ------> 9 -------> 13 --------> 17    第一级索引
 *  down      down     down      down        down         down
 *   |         |         |         |           |           |
 *   |         |         |         |           |           |
 *   |         |         |         |           |           |
 *   v         v         v         v           v           v
 * | 1 -> 3 -> 4 -> 5 -> 7 -> 8 -> 9 -> 10 -> 13 -> 16 -> 17     原始链表
 * </code></pre>
 * <p>
 * 跳表中查询任意数据的时间复杂度是O(logn), 这个查找时间复杂度跟二分查找是一样的, 换句话说, 我们基于单链表实现了二分查找, 不过这种效率的
 * 提升, 前提是建了很多索引, 听起来跳表浪费更多的内存
 * <p>
 * 跳表的空间复杂度是 O(n), 如果将包含 n 个节点的单链表构成跳表, 需要额外的接近 n 个节点的存储空间
 * 如果每3个或者5个抽取一个节点...
 * 空间复杂度还是 O(n) 但是索引减少了一半甚至更多
 * <p>
 * 实际上在软件开发中, 不必太在意索引占用的额外空间
 * 我们习惯性地把处理的数据看成整数, 但是实际开发中, 原始链表中存储的可能是很大的对象, 而索引节点只需要存储关键值和几个指针, 并不需要存储对象
 * 所以当对象比索引节点大很多时, 那索引占用的额外空间就可以忽略了
 * <p>
 * 当我们不停往跳表中插入数据时, 如果我们不更新索引, 有可能某2个节点之间数据会非常多, 极端情况下, 跳表退化为链表
 * 作为一种动态数据结构, 需要某种手段来维护索引与原始链表大小之间的平衡, 也就是说, 如果链表中节点多了, 索引节点就相应地增加一些, 避免复杂度退化
 * 以及查找插入删除操作性能下降
 * <p>
 * 因此跳表通过随机函数来维护跳表的平衡性
 * 当我们往跳表中插入数据时, 我们可以选择同时将这个数据插入到部分索引层中
 * 我们通过一个随机函数, 来决定将这个节点插入到哪几级索引中, 比如随机函数生成了值K, 那我们就将这个节点添加到第一级到第K级这K级索引中
 * 随机函数的选择很有讲究, 从概率上讲, 能够保证跳表的索引大小和数据大小平衡性, 不至于性能过度退化
 * <p>
 * 跳表的实现还是稍微有点复杂的
 * <p>
 * Redis 中有序集合就是通过跳表来实现的, 严格来说还用到了散列表
 * Redis 有序集合支持的核心操作主要有:
 * 插入, 删除, 查找, 按照区间查找数据, 迭代输出有序序列
 * <p>
 * 插入删除查找这几个操作跟红黑树一样, 时间复杂度也一样, 但是按区间来查找数据红黑树效率没跳表高
 * 对于按照区间查找数据这个操作, 跳表可以做到 O(logn) 的时间复杂度定位区间的起点, 然后在原始链表中顺序往后遍历就可以
 * Redis之所以用跳表来实现有序集合, 还有其它原因, 比如跳表代码更容易实现, 虽然跳表的实现也不简单, 但是比起红黑树来说还是好懂, 好写多了
 * 而简单就意味着可读性好, 不容易出错, 还有跳表更加灵活, 它可以通过改变索引构建策略, 有效平衡执行效率和内存消耗
 * 跳表也不能完全替代红黑树, 因为红黑树要比跳表的出现早一些, 很多编程语言中的Map类型都是通过红黑树来实现的,
 * 我们做业务开发直接拿来用就行, 不用自己手写红黑树, 但是跳表并没有一个现成的实现, 所以在开发中, 如果你想使用跳表, 必须自己实现
 * <p>
 * 10. 散列表
 * 散列表用的是数组支持按照下标随机访问数据的特性, 所以散列表其实就是数组的一种扩展, 由数组演化而来, 可以说, 没有数组就没有散列表
 * <p>
 * 散列冲突
 * 再好的散列函数也无法避免冲突, 有个比较好的例子就是鸽巢, 就是有10个鸽子窝, 但是有11只鸽子, 其中就有一个必须会冲突
 * 散列冲突的解决方法一般有两类: 开放寻找法和链表法
 * <p>
 * 开放寻址法
 * 如果出现散列冲突就重新探测一个空闲位置, 将其插入
 * 如何重新探测新的位置?
 * 一个比较简单的探测方法是线性探测, 当我们往散列表中插入数据时, 如果某个数据经过散列函数散列后存储位置已经被占用, 就从当前位置开始依次往后查找,
 * 看是否有空闲位置, 直到找到为止
 * 一般情况下, 我们会尽可能保证散列表中有一定比例的空闲槽位, 我们用装载因子来表示空位的多少
 * 装载因子 = 元素个数 / 散列表长度
 * 装载因子越大, 空闲位置越少, 冲突越多, 散列表性能会下降
 * <p>
 * 链表法
 * 散列值相同的元素都放在相同的槽位对应的链表中
 * <p>
 * 11. 哈希算法的应用
 * 11.1 安全加密
 * 11.2 唯一标识
 * 11.3 数据校验
 * 11.4 散列函数
 * 11.5 负载均衡
 * 11.6 分布式存储
 * 11.7 数据分片
 * <p>
 * 12. 树
 * 节点的高度: 节点到叶子节点的最长路径(边数)
 * 节点的深度: 根节点到这个节点所经历的边的个数
 * 节点的层数: 节点的深度 + 1
 * 树的高度: 根节点的高度
 * <p>
 * 树的结构多种多样, 最常用的还是二叉树
 * <p>
 * 满二叉树
 * 叶子节点全都在最底层, 除了叶子节点之外, 每个节点都有左右两个子节点
 * <p>
 * 完全二叉树
 * 叶子节点都在最底下两层, 最后一层的叶子节点都靠左排列, 并且除了最后一层, 其它层的节点个数都要达到最大
 * <p>
 * 要想存储一颗二叉树, 一种是基于指针或者引用的二叉链式存储法, 一种是基于数组的顺序存储法
 * <p>
 * 链式存储法
 * 有个节点有三个字段, 其中一个存储数据, 另外两个是指向左右子节点的指针, 通过根节点, 通过左右子节点指针, 就可以把整棵树串起来
 * 这种存储方式比较常用, 大部分二叉树代码都是通过这种结构来实现的
 * <p>
 * 顺序存储法
 * 如果节点 x 存储在数组中下标为 i 的位置, 下标为 2*i 的位置存储左子节点, 下标为 2*i+1 存储右子节点, 反过来, 下标为 i/2 的位置存储父节点
 * 通过这种方式, 只需要知道根节点存储的位置, 一般情况下, 为了方便计算, 根节点会存储在下标为 1 的位置, 这样就可以通过下标计算, 把整棵树串起来
 * <p>
 * 所以完全二叉树仅仅浪费了数组下标 0 的空间, 但是非完全二叉树浪费的空间就会比较多
 * 完全二叉树用数组来存储无疑是最节省内存的一种方式, 因为数组不需要像链式那样存储额外的指针, 这也是为什么把完全二叉树拧出来的原因, 也是为
 * 什么完全二叉树要求最后一层的子节点必须靠左的原因, 靠右浪费数组空间
 * <p>
 * 二叉树的遍历
 * 前序遍历
 * 对于树中的任意节点来说, 先打印这个节点, 在打印它的左子树, 最后打印它的右子树
 * <p>
 * 中序遍历
 * 对于树中的任意节点来说, 先打印它的左子树, 在打印这个节点, 最后打印它的右子树
 * <p>
 * 后序遍历
 * 对于树中的任意节点来说, 先打印它的左子树, 在打印它的右子树, 最后打印这个节点
 * <p>
 * 二叉树遍历操作的时间复杂度, 跟节点个数 n 成正比, 遍历二叉树的时间复杂度是 O(n)
 * <p>
 * 12.1 二叉查找树
 * 二叉查找树不仅支持快速查找一个数据, 也支持快速插入, 删除一个数据
 * 二叉查找树要求, 在树中的任意一个节点, 其左子树中的每个节点的值都要小于这个节点的值, 右子树中的值都大于这个节点的值
 * <p>
 * 二叉树的查找
 * 先选取根节点, 如果他等于我们要查找的数据直接返回, 如果查找的数据比根节点小, 就往左子树遍历, 如果要查找的数据比根节点大, 往右子树遍历
 * <p>
 * 二叉树的插入
 * 如果要查找的数据比根节点的数据大并且右子树为空则直接插入到右子树上
 * 如果不为空, 则需要遍历右子树查找插入位置
 * 如果要查找的数据比节点数值小, 并且左子树为空, 则插入到左子树上
 * 如果不为空, 遍历左子树, 查找插入位置
 * <p>
 * 二叉树的删除
 * 查找和插入都比较好理解, 删除就比较复杂一些
 * <p>
 * 针对要删除的节点分三种情况来处理
 * 如果要删除的节点没有子节点只需要将父节点的指向指针设置为null即可
 * 如果要删除的节点只有一个子节点只需要更新父节点中指向该节点的指针为null即可
 * 如果要删的节点有两个节点就比较复杂, 需要找到这个节点右子树中的最小节点来替换这个值
 * <p>
 * 实际上, 二叉树的删除操作有一个非常简单取巧的方法就是单纯将要删除的节点标记为已删除, 但是并不真正从树中将这个节点去掉, 这样原本需要删除
 * 的节点还需要存储在内存中, 比较浪费内存空间, 但是删除操作变得简单了很多, 而且这种处理方法也并没有增加插入和查找操作的代码的实现难度
 * <p>
 * 支持重复数据的二叉查找树
 * 实际的软件开发中, 二叉树中存储的通常是一个包括很多字段的对象, 我们利用对象的某个字段作为键来构建二叉查找树, 我们把对象中的其它字段叫做卫星数据
 * <p>
 * 二叉查找树中键相同时, 有两种方法处理
 * 第一种是二叉树中每个节点不仅可以存储一个数据, 我们可以通过链表和支持动态扩容的数组等数据结构来存储, 把相同的数据都存储在同一个节点上
 * 第二种还是保持每个节点仅有一个数据, 但是更加优雅, 理解起来比较费劲一些
 * 在查找插入位置的过程中, 如果碰到一个节点的值与要插入数据的值相同, 我们就将这个要插入的数据放到这个节点的右子树, 把这个心插入的数据当做大于这个节点的值来处理
 * 其实就是将原来的大于小于和等于退化为大于等于和小于来处理, 其实也可以是小于等于和大于, 只是一个是往左子树处理, 一个是往右子树处理而已, 下面把相同的值当做大于当前值处理
 * <p>
 * 当要查找数据的时候, 遇到值相同的节点, 我们并不停止查找, 而是继续在左右子树中继续查找, 直到遇到叶子节点为止, 这样就把键值等于目标值的所有节点都找出来了
 * <p>
 * 删除操作也是, 按照单个删除, 遍历删除为止
 * <p>
 * 相较于散列表, 二叉树存在的原因?
 * 散列表中的数据是无序的, 如果要输出有序数据需要先进行排序, 而对于二叉查找树只需要遍历即可
 * 散列表扩容耗时多, 当遇到散列冲突时性能不稳定, 尽管二叉树的性能不稳定, 但是工程中, 我们常用的平衡二叉查找树性能非常稳定, 时间复杂度也稳定在 O(logn)
 * 笼统的说散列表查找操作的时间复杂度是常量级, 但是因为哈希冲突, 常量未必就比 O(logn) 要小, 实际上查找未必就比 O(logn) 快, 加上哈希函数的耗时, 也就
 * 未必比平衡二叉树查找效率高
 * 散列表的构造比二叉查找树要复杂, 需要考虑的东西很多, 比如散列函数的设计, 冲突的解决方法, 扩容, 缩容等, 平衡二叉查找树只需要考虑平衡性这一个问题, 而且这个问题
 * 的解决方案比较成熟和固定
 * 为了避免过多的散列冲突, 散列表装载因子不能太大, 特别是基于开放寻址法解决冲突的散列表, 不然会浪费一定的存储空间
 * <p>
 * 二叉查找树在频繁的动态更新中, 可能会出现树的高度远大于 log2n 的情况, 从而导致各个操作效率下降, 极端情况下, 会退化为链表, 要解决这个问题,
 * 需要设计一种平衡的二叉查找树, 就是接下来的红黑树
 * <p>
 * 13. 红黑树
 * <p>
 * 在工程中, 很多用到平衡二叉树的地方都会用红黑树
 * <p>
 * 平衡二叉树的严格定义是这样的, 二叉树中任意一个节点的左右子树的高度相差不能大于1
 * <p>
 * 最先被发明的平衡二叉查找树是 AVL树, 它严格符合平衡二叉查找树的定义, 即任何节点的左右子树高度相差都不超过1, 是一种高度平衡的二叉查找树
 * 但很多平衡二叉查找树并没有严格符合上面的定义, 树中的任意一个节点的左右子树的高度差可能大于1, 比如红黑树, 根节点到个叶子节点的最长路径
 * 可能比最短路径大一倍
 * <p>
 * 发明平衡二叉查找树这种数据结构的初衷是解决普通二叉查找树在频繁的输入, 删除等动态更新的情况下, 出现时间复杂度退化的问题
 * 所以平衡二叉查找树中平衡的意思, 其实就是让整棵树左右看起来比较对称, 比较平衡, 不要出现左子树很高, 右子树很矮等情况, 这就
 * 能让整棵树的高度相对来说低一些, 响应的插入, 删除, 查找等操作的效率就高一些
 * <p>
 * 红黑树的应为是 Red-Black Tree, 简称 R-B Tree, 它的出镜率甚至比平衡二叉查找树更多一些
 * <p>
 * 一棵红黑树需要满足下面的要求:
 * 根节点是黑色
 * 每个叶子节点都是黑色的空节点(null), 也就是说叶子节点不存储数据
 * 任何相邻的节点都不能同时为红色, 也就是说红色节点是被黑色节点隔开的
 * 每个节点从该节点达到其可达叶子节点的所有路径, 都包含相同数量的黑色节点
 * <p>
 * 很多同学都觉得红黑树很难, 的确, 它算是最难掌握的一种数据结构, 其实红黑树最难的地方在于它的实现, 我们不要太关注它的实现, 要学习它的由来
 * 特性, 以及适用的场景以及它能解决的问题
 * <p>
 * 14. 堆排序
 * <p>
 * 堆也是一种特殊的树, 堆这种数据结构应用的场景非常多, 最经典的某过于堆排序, 是一种原地时间复杂度为 O(nlogn) 的排序算法
 * 快速排序的时间复杂度也一样, 但实际的软件开发中, 快速排序的性能要比堆排序好
 * <p>
 * 堆是一个完全二叉树
 * 堆中每一个节点的值都必须大于等于或者小于等于其子树中每个节点的值
 * <p>
 * 大顶堆
 * 每个节点的值都大于等于其子树中每个节点的值, 即只有堆顶是最大值, 其它子树则是乱序的
 * <p>
 * 小顶堆
 * 每个节点的值都小于等于其子树中每个节点的值, 即只有堆顶是最小值, 其它子树则是乱序的
 * <p>
 * 如何实现一个堆?
 * 堆是完全二叉树, 完全二叉树用数组来存储相当节省内存, 而且可以使用数组通过下标随机访问的特性
 * <p>
 * 堆核心的操作是 插入一个元素和删除堆顶元素
 * <p>
 * 以下均说大顶堆, 小顶堆类似
 * <p>
 * 往堆中插入一个元素, 需要继续满足堆的特性
 * 如果我们把新插入的元素放在堆的最后, 极其可能不符合堆的特性, 需要进行调整, 让其重新满足堆的特性, 这个过程就叫堆化(heapify)
 * 堆化其实有两种, 从下往上和从上往下, 插入元素时就是从下往上堆化的过程
 * <p>
 * 堆化非常简单, 就是顺着节点所在的路径, 向上或者向下不停的对比和交换
 * {@link Heap#insert(int)} 实现插入元素完成建堆
 * <p>
 * 删除堆顶元素
 * 堆顶元素是堆中的最大或最小值
 * <p>
 * 对于大顶堆来说, 删除堆顶, 第二大的元素就是左子节点或者右子节点中, 但是第二大元素挪动到堆顶后, 子树本身又空洞了, 需要继续挪动子树, 而且
 * 数据可能就出现空洞
 * <p>
 * 实际上正确的做法不是直接删除, 而是把最后一个节点放到堆顶, 因为最后一个元素的变动不造成数组空洞, 而且是直接留出位置, 然后进行从上到下堆化
 * 的过程, 直到满足堆的条件为止
 * {@link Heap#remove()}
 * 堆化的时间复杂度跟树的的高度成正比, 插入数据和删除堆顶元素的主要逻辑就是堆化
 * <p>
 * 如何实现堆排序?
 * 首先是建堆
 * 将数组原地建成一个堆, 不借助另一个数组, 在原数组上操作, 有两种思路
 * 第一种是在堆中插入一个元素的思路, 尽管数组中包含了 n 个数据, 但是我们可以假设, 期初堆中只要一个元素, 就是数组下标为 1 的元素
 * 然后不断的插入, 将数组下标 2,n 的数据依次插入到堆中
 * 第二种跟第一种相反, 第一种处理过程是从前往后处理数组数据, 并且每个数据插入堆中时都是从下往上堆化, 第二种是从后往前处理数据, 并且每个数据
 * 都是从上往下堆化
 * {@link Heap#build(int)}
 * 注意, 采用从上往下堆化的过程, 首先是确认堆顶元素, 跟删除类似
 * 堆顶元素其实可以理解为最后一个元素的父节点, 即数组长度的一半, 可以理解为数组的中位
 * <p>
 * 其实只需要从下标 n/2,1 的数据进行堆化, 下标是 (n/2 + 1),n 的节点是叶子节点, 不需要堆化, 实际上, 对于完全二叉树来说, 下标从 (n/2 + 1), n 的节点都是叶子节点
 * <p>
 * 实际上堆排序的建堆时间复杂度是 O(n)
 * <p>
 * 然后就是堆排序
 * 建堆结束之后, 数组中的数据已经按照大顶堆的特性来组织数据了, 数组中的第一个元素就是堆顶, 也就是最大值, 我们把它跟最后一个元素交换, 把最大元素放在下标为 n 的位置
 * 这个过程有点像删除堆顶的操作, 当堆顶元素移除之后, 我们把下标为 n 的元素放到堆顶, 然后通过堆化的方法将剩下的 n-1 个元素重建构建堆,
 * 堆化完成之后, 我们再次取堆顶元素放到下标为 n-1 的位置, 一直重复这个过程, 直到最后堆中只剩下下标为1的一个元素, 排序工作就完成了
 * {@link Heap#sort(int)}
 * <p>
 * 堆排序和快速排序
 * 堆排序数据访问的方式没有快速排序友好, 快排是顺序访问, 堆排序是跳跃访问
 * 对于同样的数据, 堆排序数据交换的次数要比快排多
 * <p>
 * 堆的应用
 * 优先队列
 * 合并有序小文件
 * 高性能定时器
 * <p>
 * Top K
 * 一类是静态数据, 另一类是动态数据集合
 * 总体思路是维护一个大小为K的小顶堆, 当有数据添加到集合中时, 就拿它与堆顶元素比较, 如果比堆顶元素大, 就把堆顶元素删除, 并且将这个元素插入堆中
 * 如果比堆顶元素小, 则不处理
 * <p>
 * 中位数
 * 如果有 n 个数据, n 是偶数, 我们从小到大排序, 那前 n/2 个数据存储在大顶堆中, 后 n/2 个数据存储在小顶堆中, 这样大顶堆中的堆顶元素就是我们要找
 * 的中位数
 * 如果 n 是奇数, 情况类似, 大顶堆存储 n/2 + 1 个数据, 小顶堆存储 n/2 个数据
 */
public class DataStructTest {

    public static void main(String[] args) {
        // 1. 栈
        System.out.println("栈");
        ArrayStack stack = new ArrayStack(5);
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        // return null
        stack.push("e");
        for (int i = 0; i < 5; i++) {
            System.out.println(stack.pop());
        }

        // 2. 顺序队列
        System.out.println("顺序队列");
        ArrayQueue queue = new ArrayQueue(5);
        queue.enQueue("aa");
        queue.enQueue("bb");
        queue.enQueue("cc");
        queue.enQueue("dd");
        queue.enQueue("ee");
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.deQueue());
        }
        // 此时入队成功
        queue.enQueue("ff");
        System.out.println(queue.deQueue());

        // 3. 链式队列
        System.out.println("链式队列");
        @SuppressWarnings("unchecked")
        LinkQueue<String> linkQueue = new LinkQueue();
        linkQueue.en("abc");
        linkQueue.en("abd");
        linkQueue.en("abe");
        linkQueue.en("abf");
        linkQueue.en("abg");
        // 遍历链式队列, 需要知道头指针
        LinkQueue.Node head = linkQueue.getHead();
        while (head != null) {
            // 直接出队即可
            System.out.println(linkQueue.de());
            head = head.next;
        }

        LinkQueue<Integer> linkQueue1 = new LinkQueue<>();
        linkQueue1.en(100);
        linkQueue1.en(200);
        System.out.println(linkQueue1.de());
        System.out.println(linkQueue1.de());

        // 4. 环形队列
        System.out.println("环形队列");
        // 容量为3的话, 其实只有2个元素位置始终是可用的
        CircularQueue<String> circularQueue = new CircularQueue<>(3);
        circularQueue.en("这是");
        circularQueue.en("一个");
        System.out.println(circularQueue.de());
        System.out.println(circularQueue.de());
        circularQueue.en("测试");
        circularQueue.en("是的测试");
        System.out.println(circularQueue.de());
        System.out.println(circularQueue.de());

        // 5. 二分查找
        System.out.println("二分查找");
        int[] source = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(binSearch(source, 1));
    }

    /**
     * 二分折半查找
     *
     * @param source 原始数据
     * @param target 目标数据
     * @return 查找结果, 无时返回-1
     */
    private static int binSearch(int[] source, int target) {
        // 记录数组搜索位置
        int low = 0;
        int high = source.length - 1;
        int mid;
        while (low <= high) {
            // 找中位数
            // 这样写法有问题, 可能会被溢出
            mid = (low + high) / 2;
            if (source[mid] > target) {
                high = mid - 1;
            } else if (source[mid] < target) {
                low = mid + 1;
            } else {
                return source[mid];
            }
        }
        return -1;
    }

    /**
     * 查找第一个值等于给定值的元素
     *
     * @param source 原始升序数据
     * @param target 目标数据
     * @return 是否存在
     */
    private static int binSearch1(int[] source, int target) {
        int low = 0;
        int high = source.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            // 其实就是找到相等元素时需要判断与它相近的上一个元素是否相同, 如果不相同则为目标元素
            if (source[mid] > target) {
                high = mid - 1;
            } else if (source[mid] < target) {
                low = mid + 1;
            } else {
                // 当前已经是第一个元素
                // 并且前一个值不相等
                if (mid == 0 || source[mid - 1] != target) {
                    return mid;
                } else {
                    // 需要更新
                    // 说明上一个元素也是一样的, 还要继续往前移
                    /*
                     * 如果搜索12第一次出现的位置, 可以分析
                     * 第一次 mid = ( 0 + 8 ) / 2 = 4 source[4] = 9 比12小, 往后移动
                     * 第二次 low = mid + 1 = 4 + 1 = 5; high = 8 mid = (5 + 8 ) / 2 = 6 source[6] = 12 但是 source[6-1] = 12
                     * 因此上一个数据相等不是第一次出现的位置
                     * 第三次 low = 5 high = 5 mid = ( 5 + 5 ) / 2 = 5 source[5] = 12 source[5-1] = 9 满足条件
                     *
                     *                            mid
                     * |----------------------------------------
                     * | 3 | 5 | 7 | 8 | 9 | 12 | 12 | 12 | 23 |
                     * |----------------------------------------
                     *   0   1   2   3   4   5     6    7    8
                     *
                     */
                    // 这就是所谓的想当时往前移一位的分析
                    high = mid - 1;
                }
            }
        }
        return 0;
    }
}

/**
 * 实现一个简单的栈结构
 * 不支持动态扩容
 */
class ArrayStack {
    // 存储原始数据
    private String[] items;
    // 栈的大小
    private int length;
    // 栈中元素的个数
    private int count;

    /**
     * 初始化一个栈, 栈的大小为容量大小, 栈中的元素为0个
     *
     * @param length 栈的容量
     */
    ArrayStack(int length) {
        items = new String[length];
        this.length = length;
        count = 0;
    }

    /**
     * 入栈一个元素
     *
     * @param item 待入栈的元素
     * @return 入栈结果
     */
    public boolean push(String item) {
        // 栈空间已满, 直接拒绝
        if (count == length) {
            return false;
        }
        // 将元素存储在当前的位置
        items[count] = item;
        ++count;
        return false;
    }

    // 出栈
    public String pop() {
        // 栈中没有元素
        if (count <= 0) {
            return null;
        }
        // 获取最新入栈的栈对象
        String s = items[count - 1];
        --count;
        return s;
    }
}

/**
 * 简单队列
 * 无法利用空间, 满之后不可再使用
 * 补充数据搬移操作
 */
class ArrayQueue {
    // 队列底层数据结构
    private String[] items;
    // 队列的长度
    private int capacity;
    // 队列头
    private int head;
    // 队列尾
    private int tail;

    /**
     * 一个概念就是
     * 头尾是相对的, 随着数据的入队和出队而变化
     * 入队的时候尾指针往后移动, 即增加
     * 出队的时候, 头指针往后移动, 也是增加
     */

    ArrayQueue(int capacity) {
        items = new String[capacity];
        this.capacity = capacity;
        this.head = 0;
        this.tail = 0;
    }

    /**
     * 入队
     * 需要考虑数据搬移操作
     *
     * @param item 待入队的对象
     * @return 入队结果
     */
    public boolean enQueue(String item) {
        // 队列头尾指针相撞
        if (tail == capacity) {
            // 队列是否还有空间, 看元素数量是否是最大容量
            // 即看出队的头指针移动位置, 0则为未出队, 队列暂无更多空间
            // 搬移也无法操作, 只能扩容到更大的数组
            if (head == 0) {
                return false;
            }
            // 否则进行数据搬移
            // 看头指针的位置, 进行数据搬移
            // 其实就是将尾部元素移动到头部去
            /*
             *                               head             tail
             * |--------------------------------------------------
             * |  |  |  |  |  |  |  |  |  |  | 3 | 5 | 8 | 2 | 4 |
             * |--------------------------------------------------
             */
            // 类似上述队列, 方向无所谓, 统一按队列一边入队, 一边出队即可
            // 此时 tail 就到达容量处, 但是 head 之前有大量的空缺, 故此时触发数据搬移工作
            // 即将数据全部搬移到 head = 0 开始, 并更新 tail 指针
            if (head < tail) {
                for (int i = head; i < tail; ++i) {
                    items[i - head] = items[i];
                }
                // 更新头尾指针
                head = 0;
                tail -= head;
            } else {
                /*                   head
                 * |---------------------
                 * |  |  |  |  |  |  |  |
                 * |---------------------
                 *                   tail
                 * 这种情况比较特殊, 无数据可搬移, 需要重新移动指针到开始位置即可
                 */
                // 如果队列满后再入队, 更新指针为初始值即可
                head = tail = 0;
            }
        }
        // 往队尾添加元素, 不影响, 只关注尾指针即可
        items[tail] = item;
        ++tail;
        return true;
    }

    /**
     * 其实出队不需要关心数据是否搬移, 正常出队即可
     *
     * @return
     */
    public String deQueue() {
        // 队列为空
        if (tail == head) {
            return null;
        }
        // 头部元素出队
        String s = items[head];
        // 头部元素后移
        ++head;
        return s;
    }
}

/**
 * 链式队列
 * 可以理解为链式为无限队列, 受系统资源限制
 */
class LinkQueue<T> {

    // 保存队列的头和尾指针
    private Node head;
    private Node tail;

    LinkQueue() {
        head = null;
        tail = null;
    }

    /**
     * 链表
     */
    static class Node<T> {
        // 存储当前对象
        T value;
        // 下一个节点
        Node next;

        Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    @SuppressWarnings("unchecked")
    void en(T item) {
        // 首次入队, 头尾指针都指向同一个节点
        if (tail == null) {
            /**
             * head
             * |--------------
             * | item | next | -> null
             * |--------------
             * tail
             */
            // 节点的下一个节点默认为null, 暂时还不存在节点
            Node node = new Node(item, null);
            head = node;
            tail = node;
        } else {
            // 需要实时更新队尾数据
            /**
             * head 位置
             * |--------------    |--------------
             * | item | next | -> | item | next | -> null
             * |-------------|    |--------------
             *                    tail 位置
             */
            // 队尾, 入队都把数据添加到队尾, 先进后出顺序
            tail.next = new Node(item, null);
            // 更新当前队尾的位置其实就是最后一个入队的数据
            tail = tail.next;
        }
    }

    @SuppressWarnings("unchecked")
    T de() {
        // 没有元素要出队
        if (head == null) {
            return null;
        }
        T value = (T) head.value;
        head = head.next;
        // 最后一个位置, 队列中已无元素
        if (head == null) {
            tail = null;
        }
        return value;
    }

    /**
     * 暴露头指针, 提供遍历链式队列
     *
     * @return 头指针
     */
    Node getHead() {
        return head;
    }
}

/**
 * 环形队列
 */
class CircularQueue<E> {
    // 底层存储对象, 传入泛型参数来处理
    private Object[] items;
    private int capacity;
    private int head;
    private int tail;

    CircularQueue(int capacity) {
        // 泛型参数不可实例化, 因为泛型是编译器模板替换的, 不是一种类型, 无法实例化
        items = new Object[capacity];
        this.capacity = capacity;
        head = 0;
        tail = 0;
    }

    boolean en(E item) {
        // 队列是否已满, 这个判断比较关键
        // 即是即将用来存储数据的位置是否是最后一个空位
        // 即该空位往下一个锁分配的位置是否为当前的头指针位置
        // 这样的话队列就已经满
        if ((tail + 1) % capacity == head) {
            return false;
        }
        // 否则计算 tail 的位置, 存入数据即可
        // 该处的 tail 也是上一次计算好的, 分配在数组中的位置
        items[tail] = item;
        // 计算 tail 即将分配在数组中的位置
        // 下一个位置 tail + 1
        // 分配位置
        tail = (tail + 1) % capacity;
        return true;
    }

    @SuppressWarnings("unchecked")
    E de() {
        // 队列为空
        if (head == tail) {
            return null;
        }

        // 出队头指针元素
        E value = (E) items[head];
        // 指向下一个头指针
        // 跟容量取余分配到容量位置上
        // 下一个指针=当前指针 + 1
        // 还需要看分配到那个位置
        head = (head + 1) % capacity;
        return value;
    }
}

/**
 * 10万URL访问日志, 按访问次数排序
 */
class Url {
    // 最大访问次数
    int maxCount;

    void handle() {
        // 对地址进行hash, 可以认为不发生哈希冲突, 忽略不计
        // 转化为数组下标
        /**
         * 地址哈希后映射为数组下标
         *  Address 每个元素存储的是地址对象
         * |------------------------
         * | 0 | 1 | 2 | 3 | 4 | ...
         * |------------------------
         *
         */
        // 记录下最大的访问次数 maxCount
        // 如果该值不大用桶排序
        // 桶就是访问次数从1开始到最大值maxCount 0, maxCount - 1
        // 此时Url已经去重, 并得到了访问次数, 接下来就是排序
    }

    /**
     * 记录地址信息
     */
    class Address {
        // 访问地址和访问次数
        String uri;
        int count;
    }
}

/**
 * 二叉树相关
 */
class Tree {
    // 二叉树
    private Node tree;

    /**
     * 二叉搜索值
     *
     * @param target 搜索的目标值
     * @return 搜索结果
     */
    int find(int target) {
        // 不能直接操作树, 破坏引用关系
        Node root = tree;
        while (root != null) {
            if (root.item > target) {
                root = root.left;
            } else if (root.item < target) {
                root = root.right;
            } else {
                return root.item;
            }
        }
        // 实践中看返回需求
        throw new RuntimeException("不存在");
    }

    /**
     * 二叉树的插入有一个概念一定要记住
     * 除根节点外, 所有数据最终都写入到叶子节点上, 其实根节点也是一个特殊的叶子节点
     *
     * @param target 待插入的目标值
     * @return 插入结果
     */
    boolean insert(int target) {
        // 首次插入即根节点也为空
        if (tree == null) {
            tree = new Node(target);
            return true;
        }

        // 否则遍历树
        Node root = tree;
        while (root != null) {
            // 目标值比根节点要小, 存入左子树
            if (root.item > target) {
                // 如果左子树为空, 则直接存入左子树
                if (root.left == null) {
                    root.left = new Node(target);
                    return true;
                }
                // 节点更新为左子节点
                root = root.left;
            } else {
                if (root.right == null) {
                    root.right = new Node(target);
                    return true;
                }
                // 节点更新为右子节点
                root = root.right;
            }
        }

        return false;
    }

    /**
     * 二叉树中删除值
     *
     * @param target 待删除的目标值
     * @return 删除结果
     */
    boolean delete(int target) {
        // 本身没有树
        if (tree == null) {
            return false;
        }

        // 当前根节点
        Node root = tree;
        // 当前根节点的父节点
        Node rootParent = null;
        // 遍历找到目标元素
        while (root != null && root.item != target) {
            // 每次都记录父节点
            rootParent = root;
            if (root.item > target) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        // 目标值不存在于树中
        if (root == null) {
            return false;
        }

        // 找到当前目标位置
        // root 存储当前目标值
        // rootParent 存储当前目标值的父节点

        // 最复杂的情况, 要删除的节点有两个子节点
        // 那其实目标很明确, 需要在右子树中查找最小的子节点
        if (root.left != null && root.right != null) {
            // 查找最小的子节点
            Node minP = root.right;
            // 记录当前子节点的父节点
            // 当前节点右子树的最小节点其实在右子树中的最后一个左子树叶子节点上
            Node minPP = root;
            while (minP != null) {
                minPP = minP;
                minP = minP.left;
            }

            // 将当前的值更新的到待删除的部分, 这个待删除的部分左右子节点是不需要变动的, 只需要将当前值更新为目标值即可
            // 这一步非常重要, 非常重要, 非常重要
            // 不要理解错误导致未删除成功
            root.item = minP.item;

            // 这一步是将有两个子节点的节点替换为只有一个子节点的操作替换方式
            // 之后待删除的部分就为最新的最小值引用了
            root = minP;
            rootParent = minPP;
        }

        // 要删除的节点是叶子节点或者只有一个节点, 直接更新父节点的指向即可

        // 记录当前待删除节点的子节点
        Node child;
        if (root.left != null) {
            child = root.left;
        } else if (root.right != null) {
            child = root.right;
        } else {
            child = null;
        }

        // 待删除节点为根节点
        if (rootParent == null) {
            tree = child;
        } else if (rootParent.left == root) {
            // 删除左子节点
            rootParent.left = child;
        } else {
            // 删除右子节点
            rootParent.right = child;
        }

        return true;
    }


    /**
     * 存储数据和左右子节点引用
     */
    static class Node {
        private int item;
        private Node left;
        private Node right;

        Node(int item) {
            this.item = item;
        }
    }
}

/**
 * 堆树
 * 建堆
 * 删除堆顶元素
 * 堆排序
 */
class Heap {
    // 堆的底层数据结构, 就是数组, 完全二叉树使用数组存储只浪费一个空间位置
    private int[] items;
    // 堆的容量即底层数组的容量
    private int capacity;
    // 堆中当前元素个数
    private int count;

    Heap(int capacity) {
        // 从下标1开始存储数据, 浪费下标为0的数组空间
        items = new int[capacity + 1];
        this.capacity = capacity;
        count = 0;
    }

    /**
     * 插入一个元素到堆中
     *
     * @param item 待插入到堆中的元素
     * @return 插入结果
     */
    boolean insert(int item) {
        // 堆已满, 无需继续操作
        if (count == capacity) {
            return false;
        }

        // 堆化开始前, 新插入的数据统一存放在数组当前的最后一个位置
        ++count;
        items[count] = item;

        // 然后开始堆化, 使其满足堆的条件
        int i = count;
        while (i / 2 > 0 && items[count] > items[i / 2]) {
            // 交换数组两个下标的值
            swap(i, i / 2);
            // 继续达到堆顶
            i = i / 2;
        }

        return true;
    }

    /**
     * 删除堆顶元素
     *
     * @return 元素存在返回真, 否则返回false
     */
    boolean remove() {
        // 堆中无数据无需操作
        if (count == 0) {
            return false;
        }

        // 最后一个元素移动到堆顶
        items[1] = items[count];
        --count;
        heapify(count, 1);

        return true;
    }

    /**
     * 普通数组从下往上建堆的过程
     * <p>
     * 叶子节点往下堆化只能跟自己比较, 故从第一排非叶子节点开始堆化
     *
     * @param length 数组元素的个数
     */
    void build(int length) {
        // 为什么是 length / 2 看上面的说明
        for (int i = length / 2; i >= 1; --i) {
            heapify(length, i);
        }
    }

    /**
     * 堆排序
     *
     * @param length 数组长度
     */
    void sort(int length) {
        // 建堆
        build(length);
        // 不停堆化
        // k 记录当前数组待交换的最后一个下标
        int k = length;
        while (k > 1) {
            // 将堆顶元素放到数组响应的位置
            swap(1, k);
            --k;
            // 堆顶始终是1, 因为1是用上一个堆顶交换而来的
            heapify(k, 1);
        }
    }

    private void swap(int from, int to) {
        int temp = items[from];
        items[from] = items[to];
        items[to] = temp;
    }

    /**
     * 从上往下堆化的过程
     *
     * @param count 堆中元素个数
     * @param i     当前堆顶下标
     */
    private void heapify(int count, int i) {
        // 自上往下堆化
        while (true) {
            // 当前堆顶位置
            int maxPos = i;
            // 看下一个最大值是左子节点还是右子节点
            if (i * 2 < count && items[i * 2] > items[i / 2]) {
                maxPos = i * 2;
            }
            if (i * 2 + 1 < count && items[maxPos] < items[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            // 节点最大值就是当前节点i, 无需在堆化, 已经堆化完毕
            if (maxPos == i) {
                break;
            }
            // 交换并确认最新的下标
            swap(i, maxPos);
            i = maxPos;
        }
    }
}

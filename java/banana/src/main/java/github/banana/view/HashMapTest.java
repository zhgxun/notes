package github.banana.view;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap源码解读
 * <p>
 * 1. 构造函数
 * {@link HashMap#HashMap()} 默认构造函数, 只初始化负载因子 {@code HashMap#DEFAULT_LOAD_FACTOR} 默认0.75
 * {@link HashMap#HashMap(int)} 指定容量时, 会调用 {@link HashMap#HashMap(int, float)} 重新计算容量, 2的冥次方
 * {@link HashMap#HashMap(int, float)} 会动态调整容量
 * {@link HashMap#HashMap(Map)}
 * <p>
 * 2. put 操作
 * {@link HashMap#put(Object, Object)} 真正调用的是 {@code putVal()}
 * {@code HashMap#putVal(int, Object, Object, boolean, boolean)}
 * int 作为 Map 的 key, 这个 key 是 Map 重新计算过, 并未直接使用 {@link Object#hashCode()} 来直接生成
 * {@code HashMap#hash(Object)} 对高位进行了处理, 是 hash 更加均匀, 避免频繁的 hash 冲突, 而且需要注意的是如果键为 null, 则 hash
 * 恒返回0, 即固定存在数组元素的首位置
 * 第一个 Object 作为存入的 Key
 * 第二个 Object 作为存入 key 的值 Value
 * 第一个 boolean 如果为真则不改变已经存在的值, 默认为 false
 * 第二个 boolean 如果为真则需要创建存储数组, 默认为 true, 一般是调用 put 操作调用
 * <p>
 * 3. 初始化
 * 底层存储表是一个 {@code HashMap#Node} 静态类, Map 则是一个 Node<K, V>[] table 的表数组
 * 首次使用时才进行初始化操作
 * 初始化操作由 {@code HashMap#resize()} 来完成, 返回初始化后的 Node<K, V>[] 数组, 该操作同时还可以扩容底层表
 * 容量默认为 {@code HashMap#DEFAULT_INITIAL_CAPACITY} 16个数组存储单元
 * 并生成默认触发扩容的数量为 默认容量 * 默认负载因子, 负载因子一般都默认 0.75 不推荐修改, 16 * 0.75 = 12 即表中的数组元素实际存储
 * 达到12个时, 会触发扩容
 * 直接生成新表, 使用数组对象实例方式
 * <pre><code>
 * Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
 * <code/><pre/>
 * 该步骤扩容暂不讨论, 首次使用时不会触发, 数组元素不为空时才会触发扩容
 *
 * 4. 检查值
 * 根据 hash 计算出在底层数组表中的位置
 * <pre>
 * if ((p = tab[i = (n - 1) & hash]) == null)
 *     tab[i] = newNode(hash, key, value, null);
 * <pre/>
 * 4.1 值不存在时
 * 并将找到的值赋值给临时 Node 节点保存, 如果当前为 null 则表示当前位置未被占用
 * 那么直接将当前值进行设置即可
 * <pre>
 * tab[i] = newNode(hash, key, value, null);
 * <pre/>
 * {@code HashMap#newNode(int, Object, Object, HashMap.Node)}
 * 并将该位置上的链表下一个值设置为 null 即可
 *
 * 看 Node 得知, 其实是一个具有简单链表功能的对象, 但是只存储序列的下一个元素值的单向链表, 并不是完整的链表, 无法查找上一个元素
 *
 * 4.2 值已经存在时
 * 也分三种情况
 * 4.2.1 当前值的键相同
 * 即 hash 和 key 相同, 则判定为同一个元素, 存储在临时 Node 中, 从这里可以看出被认为是相同的值时, Map 其实永远不使用最新值,
 * 保留旧值, 这样防止额外的删除和新增操作
 * 4.2.2 当前已经存储为一棵红黑树
 * 按红黑树的方式处理
 * 4.2.3 是一个新元素, 大多数情况都属于这个步骤
 * 按顺序遍历链表, <pre>for (int binCount = 0; ; ++binCount) {...}<pre/> 寻找下一个为 null 的位置, 即未被占用的位置来保存当前
 * 值, 此时需要注意, 如果链表的长度超过一定的数量, 默认是 {@code HashMap#TREEIFY_THRESHOLD} 8 时, 需要将链表升级为红黑树
 *
 * 还有一个注意点是如果链表中已经存在相同的元素, 即 hash 和 key 相同一样被认为是同样的元素, 直接退出不做过多的处理
 *
 * 4.2.4 对 map 中已经存在元素的处理
 * 这个看调用的参数设置是否需要覆盖已经存在的元素, 默认是不覆盖的, 并且返回的都是已有的历史值
 *
 * 5. 是否需要扩容
 * 当存储的元素超过 负载因子时需要扩容, 扩容依然调用 {@code HashMap#resize()} 方法
 * 此时会重新计算容量和负载, 每次按2的冥来扩容, 即是 16 -> 32 -> 64 -> 128 -> 256 等等
 * 扩容也是很简单, 用最新的容量来初始化一个新的 Node<K, V>[] 然后逐渐拷贝老的节点元素到新的节点中
 *
 * 扩容的步骤
 * 5.1 遍历老表
 * <pre>for (int j = 0; j < oldCap; ++j) {...}<pre/>
 * null 不处理, 本身获取一个不存在的键时默认返回null
 * 键的算法需要留意, 即保存到新表后, 键可能发生变化
 * 当前元素本身只有一个值, 即链表本身值存储一个值, 则直接保存到新表中即可
 * 5.2 红黑树的处理
 * 红黑树处理
 *
 * 5.3 链表拷贝
 * 链表拷贝即可
 *
 * put 操作总的来说其实主要是下面三个步骤, 难点在第三步, 需要考虑更多的操作, 包括是否覆盖已有值, 是否需要将链表转为红黑树,
 * 红黑树是否需要退化为链表等
 *
 * 红黑树
 * {@code HashMap.TreeNode} 其实包含了各种节点, 便于查找
 * <pre>
 * TreeNode<K,V> parent;  // red-black tree links
 * TreeNode<K,V> left;
 * TreeNode<K,V> right;
 * TreeNode<K,V> prev;    // needed to unlink next upon deletion
 * <pre/>
 *
 * <pre>
 * // put 操作的三个基本步骤
 * // 首次使用初始换底层数组
 * if ((tab = table) == null || (n = tab.length) == 0)
 *     n = (tab = resize()).length;
 * // 当前位置未被占用, 直接保存元素
 * if ((p = tab[i = (n - 1) & hash]) == null)
 *     tab[i] = newNode(hash, key, value, null);
 * // 当前位置被占用, 需要做更复杂的处理
 * else {}
 * // 后续是否需要扩容, 以及是否被并发修改等处理
 * <pre/>
 *
 * 6. get 操作
 * {@link HashMap#get(Object)} 其实调用的是 getNode
 * {@code HashMap#getNode(int, Object)}
 * key 为待查找键的 hash Object 为待查找键的原始值
 * 从这里可以看出, 查找的元素不存在时, 返回的是 null
 *
 * 有个特殊的操作, 经常检查第一个位置, 不是时才进行链表遍历
 * 红黑树就按照红黑树的方式查找
 * 遍历的原则就是在链表上, 找 hash 和 key 相同的元素, 其实一般来说
 *
 * 总结
 * 1. 哈希表也叫散列表, 是一种非常重要的数据结构, 许多缓存技术的核心就是内存中维护一张大的哈希表
 * 2. 哈希冲突也称哈希碰撞, 数组是一块连续固定长度的内存空间, 再好的哈希函数也不能保证得到的存储地址不发生冲突
 * 哈希冲突的解决?
 * 开放地址法: 发生冲突, 继续寻找下一块未被占用的存储地址
 * 再散列函数法
 * 链地址法, HashMap 采用的就是 链地址法, 也就是数组加链表的存储方式
 */
public class HashMapTest {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "张三");
        System.out.println(map.get(1));

        int k = 274;
        int h = hash(k);
        System.out.println(h);
        Integer[] test = new Integer[16];
        System.out.println(test[(16 - 1) & h]);
        System.out.println(map.get(2));

        // e.hash & (newCap - 1)
        // 扩容前后的位置变化: 16时 2, 32 时 18, 64 时 18
        System.out.println("扩容前后的位置变化: 16时 " + (15 & h) + ", 32 时 " + (h & 31) + ", 64 时 " + (h & 63));
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}

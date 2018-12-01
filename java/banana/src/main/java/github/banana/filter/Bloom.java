package github.banana.filter;

/**
 * 学习版的布隆过滤
 * -Xms64m -Xmx64m -XX:+PrintHeapAtGC
 * 如何判断一个元素在亿级数据中是否存在
 *
 * @link https://mp.weixin.qq.com/s/GRQp4nK1vualrC--8SHxEg
 */
public class Bloom {

    private int length = 100;

    private int[] array;

    public Bloom() {
        array = new int[length];
    }

    public Bloom(int length) {
        this.length = length;
        array = new int[length];
    }

    /**
     * 添加一个标识到序列中
     *
     * @param key 待添加的标识
     */
    public void put(String key) {
        array[hashCodeOne(key) % length] = 1;
        array[hashCodeTwo(key) % length] = 1;
        array[hashCodeThree(key) % length] = 1;
    }

    /**
     * 检查一个字符是否在序列中
     *
     * @param key 待检查的字符
     * @return 存在与否
     */
    public boolean check(String key) {
        if (array[hashCodeOne(key) % length] == 0) {
            return false;
        }
        if (array[hashCodeTwo(key) % length] == 0) {
            return false;
        }
        if (array[hashCodeThree(key) % length] == 0) {
            return false;
        }
        return true;
    }

    /**
     * 简单hash编码, 计算简单比拟后的绝对值
     *
     * @param key 待hash的键
     * @return hash编码后的值
     */
    private static int hashCodeOne(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            // 返回指定索引处的字符
            hash = 3 * hash + key.charAt(i);
        }
        return Math.abs(hash);
    }

    /**
     * 稍复杂的hash算法
     *
     * @param key 待hash的键
     * @return hash后的值
     */
    private static int hashCodeTwo(String key) {
        final int p = 1;
        int hash = 2;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash ^ key.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        return Math.abs(hash);
    }

    private static int hashCodeThree(String key) {
        int hash = 0, i;
        for (i = 0; i < key.length(); i++) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);

        return Math.abs(hash);
    }

    public static void main(String[] args) {
        int max = 10000;
        Bloom bloom = new Bloom(max);
        for (int i = 0; i < max; i++) {
            bloom.put(i + "");
        }
        System.out.println(bloom.check(1 + "") ? "数字1存在序列中" : "数字1不存在序列中");
        System.out.println(bloom.check(100000 + "") ? "数字100000存在序列中" : "数字100000不存在序列中");
        System.out.println(bloom.check(99999999 + "") ? "数字99999999存在序列中" : "数字99999999不存在序列中");
        System.out.println(bloom.check(2222 + "") ? "数字2222存在序列中" : "数字2222不存在序列中");
        System.out.println(bloom.check(888822332 + "") ? "数字888822332存在序列中" : "数字888822332不存在序列中");
        System.out.println(bloom.check(-3 + "") ? "数字-3存在序列中" : "数字-3不存在序列中");
    }
}

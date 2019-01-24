package github.banana.letcode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建不可变数据类型
 */
public class Test {

    /**
     * 该处修饰其实无法限定不可改变, 只是表明该数据类型应该是一个不可变而已
     * 被final修改的变量表示引用不可变, 但是里面的内容是可以变
     */
    private static final Map<Long, Long> map = new HashMap<Long, Long>() {
        {
            put(1L, 2L);
        }
    };

    /**
     * 需要借助其他工具来创建不可改变的数据结构
     * final 只是为了将其变为一个常量
     */
    private static final Map<Long, Long> getMap = Collections.unmodifiableMap(new HashMap<Long, Long>() {
        {
            put(100L, 101L);
        }
    });

    public static void main(String[] args) {
        System.out.println(map.toString());
        map.put(10L, 100L);
        System.out.println(map.toString());
        System.out.println(getMap.toString());
        // IDE会给出编译提示, 运行会给出 UnsupportedOperationException 异常错误
        getMap.put(200L, 201L);
        System.out.println(getMap.toString());
    }
}

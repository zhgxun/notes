package github.banana;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 今天遇到一个需求, 就是数据库中查询出数据大约有1800多万个id, 需要按照条件去除其中的一部分, 一开始想到迭代操作, 发觉特别的慢
 * 后来使用了jdk1.8中支持的stream, 特别快, 几乎没性能损耗, 可惜项目中还使用jdk1.7, 无法使用流带来的便利
 */
public class IteratorTest {

    private static Random random = new Random();
    private static final Integer length = 10000000;

    public static void main(String args[]) {
        System.out.println("随机生成1000万个数据: " + new Date());
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            ids.add(random.nextLong());
        }
        System.out.println("生成完毕: " + new Date());

        // 随机指定一个最小值
        Long minId = 1784131475L;

        System.out.println("开始处理流: " + new Date());

        // 使用流来实现
        List<Long> newIds = ids.stream().filter(id -> id < minId).collect(Collectors.toList());

        System.out.println("处理流完毕: " + new Date());
        System.out.println("剩余元素: " + newIds.size());

        // 迭代的方法操作, 性能无法接受, 对于千万的数据来说, 可以说一直阻塞中了
        Iterator iterator = ids.iterator();
        while (iterator.hasNext()) {
            Long id = (Long) iterator.next();
            if (id > minId) {
                iterator.remove();
            }
        }
    }
}

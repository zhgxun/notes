package github.banana.vm;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试堆heap溢出
 * 通过设置堆内存最大和最小值一致防止堆自动扩展直到耗尽为止
 * -Xmx 最大值
 * -Xms 最小值
 * -XX:+ HeapDumpOnOutOfMemoryError 虚拟机出现内存溢出时dump出当前内存堆转储快照便于后续分析
 * <p>
 * 传入虚拟机参数: -Xmx20m -Xms20m -XX:+HeapDumpOnOutOfMemoryError
 * 输出结果为: java.lang.OutOfMemoryError: Java heap space
 * Dumping heap to java_pid53884.hprof ...
 * <p>
 * 周志明. 深入理解Java虚拟机：JVM高级特性与最佳实践（第2版） (原创精品系列) (Kindle Location 960). 机械工业出版社. Kindle Edition.
 */
public class HeapOOM {

    public static void main(String[] args) {
        List<OOM> ooms = new ArrayList<>();
        while (true) {
            ooms.add(new OOM());
        }
    }

    private static class OOM {

    }
}

package github.banana.vm;

/**
 * 测试内存分配策略
 * -verbose:gc -Xmx20m -Xms20m -Xmn10m -XX:+PrintGCDetails
 *
 * [GC (Allocation Failure) [PSYoungGen: 6837K->896K(9216K)] 6837K->5000K(19456K), 0.0048621 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
 * Heap
 *  PSYoungGen      total 9216K, used 5316K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
 *   eden space 8192K, 53% used [0x00000007bf600000,0x00000007bfa51078,0x00000007bfe00000)
 *   from space 1024K, 87% used [0x00000007bfe00000,0x00000007bfee0000,0x00000007bff00000)
 *   to   space 1024K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
 *  ParOldGen       total 10240K, used 4104K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
 *   object space 10240K, 40% used [0x00000007bec00000,0x00000007bf002020,0x00000007bf600000)
 *  Metaspace       used 3322K, capacity 4496K, committed 4864K, reserved 1056768K
 *   class space    used 365K, capacity 388K, committed 512K, reserved 1048576K
 */
public class AllocateTest {

    public static void main(String[] args) {
        int length = 1024 * 1024;
        byte[] bytes1, bytes2, bytes3, bytes4;
        bytes1 = new byte[2 * length];
        bytes2 = new byte[2 * length];
        bytes3 = new byte[2 * length];
        bytes4 = new byte[2 * length];
    }
}

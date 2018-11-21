package github.banana.vm;

public class LocalTest {

    public static void main(String[] args) {
        // 分配60M的空间占用, 而且需要作为局部变量才会被回收, 否则直接分配到老年代内存中
        {
            byte[] bytes = new byte[1024 * 1024 * 60];
        }
        // 强制调用gc回收, 发现无法进行回收, 虽然局部变量永远不会被访问, 但是占用着未被回收
        // System.gc();

        // 强制做一些无意义的操作, 这样就会被回收, 挺有意思
        int i = 0;
        System.gc();
    }
}

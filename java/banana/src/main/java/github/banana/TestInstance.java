package github.banana;

/**
 * 测试单例模式
 */
public class TestInstance {
    public static void main(String args[]) {
        Singleton singleton1 = Singleton.getInstance();
        System.out.println(singleton1.getName());

        // 将返回true, 即是同一个单例模式
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1 == singleton2);

        SingletonV2 singletonV2 = SingletonV2.getInstance();
        System.out.println(singletonV2.getDate());
        System.out.println(singletonV2.getDesc());
    }
}

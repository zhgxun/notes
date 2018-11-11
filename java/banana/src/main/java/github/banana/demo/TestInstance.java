package github.banana.demo;

/**
 * 测试单例模式
 */
public class TestInstance {
    public static void main(String args[]) {
        // 饿汉模式
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        if (s1 == s2) {
            System.out.println("s1和s2是同一个实例");
        } else {
            System.out.println("s1和s2不是同一个实例");
        }

        // 懒汉模式
        SingletonV3 s3 = SingletonV3.getInstance();
        SingletonV3 s4 = SingletonV3.getInstance();
        if (s3 == s4) {
            System.out.println("s3和s4是同一个实例");
        } else {
            System.out.println("s3和s4不是同一个实例");
        }

        // java常量池里默认缓存[-128,127]的数字，100在此范围内常量池中存在100则直接引用
        // 100==100自然为true，1000不在范围内则相当于有两个对象，1000==1000自然为false
        // 这种情况只出现在包装类上，基础数据类型不存在该问题
        Integer a = 1000;
        Integer b = 1000;

        if (a == b) {
            System.out.println("==");
        } else {
            System.out.println("!=");
        }
    }
}

package github.banana;

import java.util.Date;

/**
 * 最常用的单例模式, 嵌套类
 */
public class SingletonV2 {

    /**
     * 外部无法直接实例化
     */
    private SingletonV2() {

    }

    /**
     * 嵌套类可以访问外部类的静态属性和静态方法
     */
    private static class Holder {
        private static SingletonV2 instance = new SingletonV2();
    }

    /**
     * 获取实例对象
     *
     * @return SingletonV2
     */
    public static SingletonV2 getInstance() {
        return Holder.instance;
    }

    /**
     * 描述文本
     *
     * @return String
     */
    public String getDesc() {
        return "This is a test method";
    }

    /**
     * 返回日期信息
     *
     * @return Date
     */
    public Date getDate() {
        return new Date();
    }
}

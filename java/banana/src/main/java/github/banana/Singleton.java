package github.banana;

/**
 * 单例模式
 */
public enum Singleton {

    INSTANCE;

    /**
     * 属性
     */
    private String name;

    /**
     * 构造方法
     */
    Singleton() {
        this.name = "Test";
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static Singleton getInstance() {
        return INSTANCE;
    }

    /**
     * 获取属性描述
     *
     * @return
     */
    public String getName() {
        return this.name;
    }
}

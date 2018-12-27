package github.banana.design.proxy;

/**
 * 静态代理
 * <p>
 * 代理(Proxy)是一种设计模式,提供了对目标对象另外的访问方式;即通过代理对象访问目标对象.
 * 这样做的好处是:可以在目标对象实现的基础上,增强额外的功能操作,即扩展目标对象的功能.
 * <p>
 * 代理对象是对目标对象的扩展,并会调用目标对象
 * <p>
 * 好处是可以统一增加新的特性, 缺点是系统中的代理类千差万别, 各种类的需求又不统一,
 * 会产生比子类稍少的代理类存在, 源代码数量普遍增加
 */
public class Proxy {

    public static void main(String[] args) {
        // 一个实现子类
        UserDao dao = new UserDao();

        // 代理类, 满足该规范的子类都可以通过代理调用, 代理类提供功能增强
        DaoProxy proxy = new DaoProxy(dao);
        proxy.save("实现用户保存操作, 使用功能增强");

        // 一个实现类, 不通过代理类一样可以正常使用, 但无法进行功能增强
        dao.save("直接访问, 无法做到功能增强");
    }
}

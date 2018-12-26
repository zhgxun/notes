package github.banana.design;

/**
 * 代理(Proxy)是一种设计模式,提供了对目标对象另外的访问方式;即通过代理对象访问目标对象.
 * 这样做的好处是:可以在目标对象实现的基础上,增强额外的功能操作,即扩展目标对象的功能.
 * <p>
 * 代理对象是对目标对象的扩展,并会调用目标对象
 */
public class Proxy {

    public static void main(String[] args) {
        UserDao dao = new UserDao();
        DaoProxy proxy = new DaoProxy(dao);
        proxy.save("实现用户保存操作");
    }
}

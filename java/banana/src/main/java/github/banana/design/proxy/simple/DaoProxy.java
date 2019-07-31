package github.banana.design.proxy.simple;

public class DaoProxy implements Dao {

    private Dao target;

    public DaoProxy(Dao dao) {
        this.target = dao;
    }

    @Override
    public void save(String message) {
        System.out.println("静态代理开始...在真正操作目标对象之间进行功能增强");
        target.save(message);
        System.out.println("静态代理结束");
    }
}

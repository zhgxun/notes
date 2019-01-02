package github.banana.design.factory;

/**
 * 4.1 公共汽车产品工厂类
 */
public class BusFactory implements TransFactory {

    @Override
    public Trans getTrans() {
        return new Bus();
    }
}

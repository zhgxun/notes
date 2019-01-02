package github.banana.design.factory;

/**
 * 4.2 自行车产品工厂类
 */
public class BikeFactory implements TransFactory {

    @Override
    public Trans getTrans() {
        return new Bike();
    }
}

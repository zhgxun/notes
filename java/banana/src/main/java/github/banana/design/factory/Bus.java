package github.banana.design.factory;

/**
 * 2.1 定义实际的产品类
 * 乘坐公共汽车上班
 */
public class Bus implements Trans {

    @Override
    public void work() {
        System.out.println("我乘坐公共汽车去上班");
    }
}

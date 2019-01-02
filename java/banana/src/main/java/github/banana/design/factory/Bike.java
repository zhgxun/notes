package github.banana.design.factory;

/**
 * 2.2 定义实际的产品类
 * 我骑自行车去上班
 */
public class Bike implements Trans {

    @Override
    public void work() {
        System.out.println("我骑自行车去上班");
    }
}

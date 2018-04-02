package github.banana.concurrency;

/**
 * 餐食订单
 */
public class Meal {

    private final int orderNum;

    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Meal: " + orderNum;
    }
}

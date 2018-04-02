package github.banana.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 餐厅
 */
public class Restaurant {

    /**
     * 餐食订单
     */
    Meal meal;

    /**
     *
     */
    WaitPerson waitPerson = new WaitPerson(this);

    /**
     * 厨师
     */
    Chef chef = new Chef(this);

    ExecutorService executorService = Executors.newCachedThreadPool();

    public Restaurant() {
        executorService.execute(chef);
        executorService.execute(waitPerson);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}

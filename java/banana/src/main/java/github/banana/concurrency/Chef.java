package github.banana.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 厨师
 */
public class Chef implements Runnable {

    private Restaurant restaurant;
    private int count = 0;

    public Chef(Restaurant r) {
        restaurant = r;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    // 等待做好的菜被带走, 挂起等待
                    while (restaurant.meal != null) {
                        wait();
                    }
                }
                // 订单数超量
                if (++count >= 10) {
                    System.out.println("Out of food, closing...");
                    restaurant.executorService.shutdownNow();
                }
                System.out.println("Order up!");

                synchronized (restaurant.waitPerson) {
                    // 新增菜并通知服务员取走
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }

                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

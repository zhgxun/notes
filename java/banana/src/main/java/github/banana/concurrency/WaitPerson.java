package github.banana.concurrency;

/**
 * 服务员
 */
public class WaitPerson implements Runnable {

    private Restaurant restaurant;

    public WaitPerson(Restaurant r) {
        restaurant = r;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    // 无菜是服务员挂起等待
                    while (restaurant.meal == null) {
                        wait();
                    }
                }
                // 取走菜
                System.out.println("WaitPerson got " + restaurant.meal);

                synchronized (restaurant.chef) {
                    // 取走菜, 通知厨师继续做菜
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

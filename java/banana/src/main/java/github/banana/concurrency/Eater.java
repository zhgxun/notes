package github.banana.concurrency;

/**
 * 消费土司
 */
public class Eater implements Runnable {

    /**
     * 监听涂抹完毕果酱的队列并取出土司来进行消费
     */
    private ToastQueue jammerQueue;

    private int count = 0;

    /**
     * 接收已初始化的涂抹果酱的队列
     *
     * @param toasts 涂抹完毕果酱的队列
     */
    public Eater(ToastQueue toasts) {
        jammerQueue = toasts;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 直接尝试阻塞等待从涂抹完毕果酱队列获取一个可消费的寿司
                Toast t = jammerQueue.take();
                System.out.println("从涂抹完毕果酱的队列中获取一个可消费的土司: " + t);

                // 检查土司的标识ID是否相等并且状态是否已经涂抹完毕果酱
                if (t.getId() != count++ || t.getStatus() != Toast.Status.JAMMED) {
                    System.out.println(">>>Error" + t);
                    System.exit(1);
                } else {
                    System.out.println("土司消费完毕: " + t);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("消费土司过程终止");
    }
}

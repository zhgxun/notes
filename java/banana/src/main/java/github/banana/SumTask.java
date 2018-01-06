package github.banana;

import java.util.concurrent.RecursiveTask;

/**
 * 分治发处理子任务
 * 
 * @author zhgxun
 *
 */
public class SumTask extends RecursiveTask<Long> {

    private static final long serialVersionUID = 1L;
    // 一次计算的数量
    private final int length = 250;
    // 需要计算的数组
    int[] arr;
    // 数组下标开始位置
    int start;
    // 数组下标结束位置
    int end;

    public SumTask(int[] array, int start, int end) {
        this.arr = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        // 如果数组元素小于一次性需要计算的量则无需分治
        if (end - start <= length) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += this.arr[i];
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return sum;
        }

        // 否则使用分治/合并的方式计算
        int middle = (start + end) / 2;
        System.out.printf("%d-%d被分解为: %d->%d; %d->%d\n", start, end, start, middle, middle, end);
        // 执行子任务
        SumTask task1 = new SumTask(this.arr, start, middle);
        SumTask task2 = new SumTask(this.arr, middle, end);
        // 执行两个任务，可以支持多个任务, 已有可变参数实现
        invokeAll(task1, task2);
        // 等待每个任务的执行结果
        long sum1 = task1.join();
        long sum2 = task2.join();
        // 合并子任务计算结果
        return sum1 + sum2;
    }

}

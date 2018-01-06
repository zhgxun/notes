package github.banana;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Fork {

    public static void main(String[] args) {
        // 创建1000个随机数组成的数组
        int[] array = new int[1000];
        long sum = 0;
        Random random = new Random(0);
        for (int i = 0; i < 1000; i++) {
            array[i] = random.nextInt(1000);
            sum += array[i];
        }
        System.out.println("期望的结果是: " + sum);
        
        // 使用分而治之的方法消耗时间计算
        ForkJoinTask<Long> task = new SumTask(array, 0, array.length);
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        // 添加任务
        long result = ForkJoinPool.commonPool().invoke(task);
        System.out.println("Fork/Join sum:" + result + ", 耗时: " + (System.currentTimeMillis() - startTime) + "ms");
    }

}

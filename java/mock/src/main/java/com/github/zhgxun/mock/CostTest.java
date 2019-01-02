package com.github.zhgxun.mock;

/**
 * 简单的 Java Agent 耗时样例
 */
public class CostTest {

    public static void one() {
        long startTime = System.currentTimeMillis();
        try {
            System.out.println("Method one start ...");
            Thread.sleep(1000);
            System.out.println("Method one end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Cost: " + (endTime - startTime) + "ms");
    }

    public static void two() {
        long startTime = System.currentTimeMillis();
        try {
            System.out.println("Method two start ...");
            Thread.sleep(2000);
            System.out.println("Method two end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Cost: " + (endTime - startTime) + "ms");
    }
}

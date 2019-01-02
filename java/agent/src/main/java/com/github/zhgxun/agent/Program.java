package com.github.zhgxun.agent;

import java.lang.instrument.Instrumentation;

/**
 * 一个 Java Agent 启动注入的实例
 */
public class Program {

    /**
     * 该方法在 main 方法之前运行, 与 main 方法运行在同一个JVM中
     * <p>
     * 并被同一个System ClassLoader装载
     * <p>
     * 被统一的安全策略(security policy)和上下文(context)管理
     *
     * @param agentOps
     * @param inst
     */
    public static void premain(String agentOps, Instrumentation inst) {
        System.out.println("Method premain(String agentOps, Instrumentation inst) execute");
        System.out.println("String agentOps: " + agentOps);
//        Class[] classes = inst.getAllLoadedClasses();
//        if (classes != null) {
//            for (Class clazz : classes) {
//                System.out.println("Instrumentation inst Class Name: " + clazz.getName());
//            }
//        }
        // Instrumentation 的最大作用, 就是类定义动态改变和操作, 这种方式相当于在JVM级别做了AOP支持
        inst.addTransformer(new Trans());
    }

    /**
     * 如果不存在 premain(String agentOps, Instrumentation inst), 则会执行 premain(String agentOps)
     *
     * @param agentOps
     */
    public static void premain(String agentOps) {
        System.out.println("Method premain(String agentOps) execute");
        System.out.println("String agentOps: " + agentOps);
    }

    /**
     * 方法不会被执行
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Agent Main Start...");
    }
}

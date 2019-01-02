package com.github.zhgxun.mock;

/**
 * 测试 Java Agent 字节码启动时动态注入
 * <p>
 * 启动方式为:
 * java -javaagent:agent.jar="TEST Agent Load" -jar mock.jar
 * 放在主程序后面的 agent 是无效的, 多次注入中间空格分隔
 * <p>
 * JDK5中只能通过命令行参数在启动JVM时指定javaagent参数来设置代理类, 而JDK6中已经不仅限于在启动JVM时通过配置参数来设置代理类,
 * JDK6中通过 Java Tool API 中的 attach 方式, 我们也可以很方便地在运行过程中动态地设置加载代理类, 以达到 instrumentation 的目的
 * Instrumentation 的最大作用, 就是类定义动态改变和操作, 这种方式相当于在JVM级别做了AOP支持
 * <p>
 * ➜  Desktop java -javaagent:agent.jar=Test -jar mock.jar
 * Method premain(String agentOps, Instrumentation inst) execute
 * String agentOps: Test
 * Mock程序启动...
 * Method one start ...
 * Method one end
 * this method one cost:1004ms.
 * Method two start ...
 * Method two end
 * this method two cost:2003ms.
 * ➜  Desktop
 * <p>
 * 运行时需要将 Boot-Class-Path: javassist-3.24.1-GA.jar 包拷贝到当前目录
 * 运行时间差别不大
 */
public class Mock {

    public static void main(String[] args) {
        System.out.println("Mock程序启动...");
        CostTest.one();
        CostTest.two();
    }
}

package com.github.zhgxun.mock;

/**
 * 测试 Java Agent 字节码启动时动态注入
 *
 * 启动方式为:
 * java -javaagent:agent.jar="TEST Agent Load" -jar mock.jar
 */
public class Mock {

    public static void main(String[] args) {
        System.out.println("Mock程序启动...");
    }
}

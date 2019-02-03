package github.banana.rmi.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboServiceApp {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("provider.xml").start();
    }
}

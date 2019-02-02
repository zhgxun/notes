package github.banana.rmi.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerApp {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("rmiServer.xml");
        System.out.println("服务端启动...");
    }
}

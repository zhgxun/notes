package github.banana.rmi.client;

import github.banana.rmi.api.SumService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("rmiClient.xml");
        SumService sumService = context.getBean("sumClient", SumService.class);
        System.out.println("客户端连接服务端中...");
        System.out.println(sumService.getAdd(10, 20));
    }
}

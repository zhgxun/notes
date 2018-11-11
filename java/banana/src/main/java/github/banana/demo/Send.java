package github.banana.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 */
public class Send {

    // 队列名称
    private final static String NAME = "hello";

    public static void main(String[] args) {
        System.out.println("Start...");
        try {
            // 1. 新建连接工厂
            ConnectionFactory connectionFactory = new ConnectionFactory();
            // 2. 设置RabbitMQ服务主机
            connectionFactory.setHost("localhost");
            // 3. 通过工厂方法建立一个新的连接
            Connection connection = connectionFactory.newConnection();
            // 4. 创建一个接收通道
            Channel channel = connection.createChannel();
            // 5. 指定队列标识
            channel.queueDeclare(NAME, false, false, false, null);
            for (int i = 0; i < 10; i++) {
                // 6. 定义消息
                String message = i + "=>这是一条队列测试消息^v^";
                // 7. 发布消息
                channel.basicPublish("", NAME, null, message.getBytes());
            }
            // 8. 关闭通道
            channel.close();
            // 9. 关闭服务连接
            connection.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("Send done");
    }
}
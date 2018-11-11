package github.banana.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 */
public class Receive {

    // 队列名称
    private final static String NAME = "hello";

    public static void main(String[] args) {
        System.out.println("Receive...");
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
                // 6. 实现一个消费者
                Consumer consumer = new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                        try {
                            String message = new String(body, "UTF-8");
                            System.out.println("Received '" + message + "'");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                };
                // 7. 绑定消费者
                channel.basicConsume(NAME, true, consumer);
            }
            // 8. 关闭通道
            channel.close();
            // 9. 关闭服务连接
            connection.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("Receive done");
    }
}

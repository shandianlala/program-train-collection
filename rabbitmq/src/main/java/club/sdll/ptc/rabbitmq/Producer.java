package club.sdll.ptc.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq 生产者
 *
 * @author chengxiwang
 * @version v0.1
 * @date 2018年07月18日 19:27
 */
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {

        String queueName = "sdll_queue";
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.43.59");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("rabbitmq");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();


        channel.queueDeclare(queueName, false, false, false, null);

        String message = "Hello World!2";
        channel.basicPublish("", queueName, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();

    }

}

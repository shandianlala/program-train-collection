package club.sdll.ptc.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq 消费者
 *
 * @author chengxiwang
 * @version v0.1
 * @date 2018年07月18日 19:28
 */
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {

        String queueName = "sdll_queue";
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.43.59");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("rabbitmq");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();


        channel.queueDeclare(queueName, false, false, false, null);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);


    }

}

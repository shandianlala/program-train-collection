package club.sdll.ptc.rabbitmq.workqueues;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Worker {

    public static void main(String[] args) throws IOException, TimeoutException {
        String queueName = "sdll_queue";
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.26.13");
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
                try {
                    doWork(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(" [x] Done");

                    // 当消息在客户端消费的时候，客户端出现异常，如中断，等
                    // 此时需要把状态返回给rabbitmq,使得rabbitmq再次分发此条消息
                    // channel.basicAck(envelope.getDeliveryTag(), false);

                }
            }
        };
        boolean autoAck = true;
        channel.basicConsume(queueName, autoAck, consumer);

        // 当消息在客户端消费的时候，客户端出现异常，如中断，等
        // 此时需要把状态返回给rabbitmq,使得rabbitmq再次分发此条消息
        // channel.basicConsume(queueName, false, consumer);

    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch: task.toCharArray()) {
            if (ch == '.') Thread.sleep(1000);
        }
    }


}

package club.sdll.ptc.rabbitmq.workqueues;

import club.sdll.ptc.rabbitmq.common.Constant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class NewTask {

    public static void main(String[] args) throws IOException, TimeoutException {
        String queueName = "sdll_queue";
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.26.13");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("rabbitmq");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(queueName, false, false, false, null);
        args = new String[]{"Hello,rabbitmq...b"};
        String message = Constant.getMessage(args);
        channel.basicPublish("", queueName, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }



}

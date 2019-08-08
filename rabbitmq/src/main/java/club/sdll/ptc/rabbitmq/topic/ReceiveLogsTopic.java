package club.sdll.ptc.rabbitmq.topic;

import club.sdll.ptc.rabbitmq.common.Constant;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * decription
 *
 * @author chengxiwang
 * @version v0.1
 * @date 2018年07月30日 19:07
 */
public class ReceiveLogsTopic {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置连接工厂的的配置信息
        connectionFactory.setHost(Constant.HOST);
        connectionFactory.setUsername(Constant.USER_NAME);
        connectionFactory.setPassword(Constant.PASSWORD);
        //获取连接connection
        Connection connection = connectionFactory.newConnection();
        //通过连接创建连接通道channel
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(Constant.TOPIC_EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();

        //使用routingKey ，对队列中的消息进行过滤，只获取自己想要得消息
//        channel.queueBind(queueName, Constant.TOPIC_EXCHANGE_NAME, "kern.*");
        channel.queueBind(queueName, Constant.TOPIC_EXCHANGE_NAME, "*.critical.*");
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);


    }

}

package club.sdll.ptc.rabbitmq.publishandsubscribe;

import club.sdll.ptc.rabbitmq.common.Constant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * decription
 *
 * @author chengxiwang
 * @version v0.1
 * @date 2018年07月30日 16:31
 */
public class EmitLog {

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
        args = new String[]{"Hello,rabbitmq...测试发布和订阅...."};
        String message = Constant.getMessage(args);
        channel.exchangeDeclare(Constant.EXCHANGE_NAME, "fanout");
        channel.basicPublish(Constant.EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        //关闭通道channel ,关闭连接connection
        channel.close();
        connection.close();
    }


}

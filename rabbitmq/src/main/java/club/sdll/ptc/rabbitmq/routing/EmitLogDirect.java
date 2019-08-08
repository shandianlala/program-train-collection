package club.sdll.ptc.rabbitmq.routing;

import club.sdll.ptc.rabbitmq.common.Constant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * decription
 *
 * @author chengxiwang
 * @version v0.1
 * @date 2018年07月30日 17:34
 */
public class EmitLogDirect {
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

        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("error", " 程序出现异常1");
        messageMap.put("info", " 你好世界");
        messageMap.put("warning", " 某处出现警告1");
        String message = Constant.getMessage(args);
        channel.exchangeDeclare(Constant.DIRECT_EXCHANGE_NAME, "direct");
        for (Map.Entry<String, String> entry: messageMap.entrySet()) {
            String routingKey = entry.getKey();
            String msg = entry.getValue();
            channel.basicPublish(Constant.DIRECT_EXCHANGE_NAME, routingKey, null, msg.getBytes());
            System.out.println(" [x] Sent '" +"routingkey="+ routingKey + "  msg=" + msg + "'");
        }

        //关闭通道channel ,关闭连接connection
        channel.close();
        connection.close();
    }


}

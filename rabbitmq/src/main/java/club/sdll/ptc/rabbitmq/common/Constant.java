package club.sdll.ptc.rabbitmq.common;

/**
 * decription
 *
 * @author chengxiwang
 * @version v0.1
 * @date 2018年07月30日 16:33
 */
public class Constant {

    public static final String HOST = "192.168.40.248"; //company
//    public static final String HOST = "192.168.0.82"; //company
//    public static final String HOST = "192.168.26.13"; //home
    public static final String USER_NAME = "admin";
    public static final String PASSWORD = "rabbitmq";


    public static final String EXCHANGE_NAME = "logs";
    public static final String DIRECT_EXCHANGE_NAME = "direct_logs";
    public static final String TOPIC_EXCHANGE_NAME = "topic_logs";


    public static final String ROUTING_KEY = "logs";



    public static String getMessage(String[] strings){
        if (strings.length < 1)
            return "Hello World!";
        return joinStrings(strings, " ");
    }

    public static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }


}

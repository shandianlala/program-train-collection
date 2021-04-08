package club.sdll.ptc.redis;

import org.junit.Test;

/**
 * description
 *
 * @author shandianlala@gmail.com
 * @version 1.0
 * @date 2021-04-08 07:48
 */
public class RedisTest {

    @Test
    public void test() {
        // 向上取整
        double ceil = Math.ceil(0.75);
        System.out.println(ceil);

        ceil = Math.ceil(1.75);
        System.out.println(ceil);

        ceil = Math.ceil(-0.75);
        System.out.println(ceil);

        ceil = Math.ceil(-1.75);
        System.out.println(ceil);
    }


}

package club.sdll.ptc.spring.asynch;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author shandianlala@gmail.com
 * @version 1.0
 * @date 2021-04-26 19:05
 */
@Component
@EnableAsync
public class SpringAsyncComponent {

    @Async
    public void testA() {
        System.out.println("method is testA, thread is " + Thread.currentThread().getName());
    }


    public void testB() {
        System.out.println("method is testB start, thread is " + Thread.currentThread().getName());
        testA();
        System.out.println("method is testB end, thread is " + Thread.currentThread().getName());
    }

}

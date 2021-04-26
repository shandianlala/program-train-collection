package club.sdll.ptc.spring;

import club.sdll.ptc.spring.asynch.SpringAsyncComponent;
import club.sdll.ptc.spring.circlereference.Teacher;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * description
 *
 * @author shandianlala@gmail.com
 * @version 1.0
 * @date 2021-04-13 07:26
 */
public class SpringTest {


    @Test
    public void TestSpringCircleReference() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("club.sdll.ptc.spring.circlereference");
        applicationContext.getBean(Teacher.class).teach();

    }

    @Test
    public void TestSpringAsync() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("club.sdll.ptc.spring.asynch");
        SpringAsyncComponent springAsyncComponent = applicationContext.getBean(SpringAsyncComponent.class);
//        springAsyncComponent.testA();
//        sleep();
        System.out.println("====================" + springAsyncComponent.getClass().getName());
        springAsyncComponent.testB();
    }

    public void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

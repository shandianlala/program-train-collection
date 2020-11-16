package club.sdll.ptc.juc.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * description
 *
 * @author shandianlala@gmail.com
 * @version 1.0
 * @date 2020-11-01 22:50
 */
public class CyclicBarrierTest {

    static CyclicBarrier c = new CyclicBarrier(2, new A());

    static CyclicBarrier c2 = new CyclicBarrier(2);

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            testCommand();
            System.out.println("=========");
        }



    }

    public static void testCommand() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    Thread.sleep(1000L);
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(1 + ", thread is " + Thread.currentThread().getName());
            }
        }).start();

        try {
            Thread.sleep(100L);
            c.await();
//            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(2 + ", thread is " + Thread.currentThread().getName());
    }

    static class A implements Runnable {

        @Override
        public void run() {
            System.out.println(3 + ", thread is " + Thread.currentThread().getName());
        }
    }




}

package club.sdll.ptc.juc.threadpool;

import java.util.concurrent.*;

/**
 * description
 * <p>
 * 1. ScheduledExecutorService
 * 2. DelayedWorkQueue
 *
 * @author shandianlala@gmail.com
 * @version 1.0
 * @date 2020-10-28 00:23
 */
public class ScheduledThreadPoolExecutorTest {

    private static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);


    public static void main(String[] args) {
//        testScheduleCallable();

        testScheduleFixedRunnable();

    }

    private static void testScheduleCallable() {
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("currentTimeMillis is " + currentTimeMillis);
        Callable<Long> callable = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                long timeMills = System.currentTimeMillis() - currentTimeMillis;
                System.out.println("Thread is " + Thread.currentThread().getName() + ", timeMills is " + timeMills);
                return timeMills;
            }
        };
        ScheduledFuture<Long> schedule = scheduledThreadPoolExecutor.schedule(callable, 2, TimeUnit.SECONDS);

        Long result;
        try {
            result = schedule.get(1, TimeUnit.SECONDS);
            System.out.println("result is " + result);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        } catch (ExecutionException e) {
            System.out.println("ExecutionException");
        } catch (TimeoutException e) {
            System.out.println("TimeoutException");
        }

//        scheduledThreadPoolExecutor.shutdown();
    }

    private static void testScheduleRunnable() {
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("currentTimeMillis is " + currentTimeMillis);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long timeMills = System.currentTimeMillis() - currentTimeMillis;
                System.out.println("Thread is " + Thread.currentThread().getName() + ", timeMills is " + timeMills);
            }
        };
        scheduledThreadPoolExecutor.schedule(runnable, 3, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.shutdown();
    }

    private static void testScheduleFixedRunnable() {
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("currentTimeMillis is " + currentTimeMillis);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long timeMills = System.currentTimeMillis() - currentTimeMillis;
                System.out.println("Thread is " + Thread.currentThread().getName() + ", timeMills is " + timeMills);
            }
        };
        scheduledThreadPoolExecutor.scheduleAtFixedRate(runnable, 2,3, TimeUnit.SECONDS);

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduledThreadPoolExecutor.shutdown();
    }


}

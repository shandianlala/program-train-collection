package club.sdll.ptc.juc.test;

import club.sdll.ptc.juc.forkjoin.CountTask;
import club.sdll.ptc.juc.util.BinaryUtil;
import club.sdll.ptc.juc.util.ThreadPoolManager;

import java.util.concurrent.*;

/**
 * description
 *
 * @author chengxiwang
 * @version v0.1
 * @date 2018年11月27日 22:16
 */
public class Main {

    public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void main(String[] args) {

        runExecutor();
//        System.out.println(Integer.toHexString((int) Math.floor(Math.random() * 16.0)));

        count();

    }

    public static void runExecutor() {
        /**
         * 异步任务线程池
         */
        ThreadPoolExecutor EXECUTOR_POOL = ThreadPoolManager.createThreadPoolExecutorFree(1, 4, 10000, 10, "fetchOutsizeApiInfo");

        EXECUTOR_POOL.execute(new TaskRunnable("111"));

        testBinary();
        EXECUTOR_POOL.execute(new TaskRunnable("222"));

        EXECUTOR_POOL.execute(new TaskRunnable("333"));
    }

    static class TaskRunnable implements Runnable {
        private String taskId;

        public TaskRunnable(String taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            System.out.println("开始执行任务，taskId=" + taskId);
        }

    }


    public static void testBinary() {
        int dd = -1 << 29;
        System.out.println("-1 << 29=dd  = " + dd);
        System.out.println("-1 << 29=(dd + 1)  = " + (dd + 1));
        System.out.println(BinaryUtil.decimalismIntToBinary(String.valueOf(dd)) + "=dd");
        System.out.println(BinaryUtil.decimalismIntToBinary(String.valueOf(dd + 1)) + "=dd + 1");
        System.out.println(BinaryUtil.decimalismIntToBinary(String.valueOf(-2)));
        System.out.println("CAPACITY   = 000" + BinaryUtil.decimalismIntToBinary(String.valueOf((1 << 29) - 1)));
        System.out.println("-536870912 = " + BinaryUtil.decimalismIntToBinary(String.valueOf(-536870912)));
        System.out.println("-536870911 = " + BinaryUtil.decimalismIntToBinary(String.valueOf(-536870911)));

        System.out.println("536870912  = 00" + BinaryUtil.decimalismIntToBinary(String.valueOf(536870912)));
        System.out.println("536870911  = 000" + BinaryUtil.decimalismIntToBinary(String.valueOf(536870911)));

        //-1
        System.out.println("11111111111111111111111111111111 = " + BinaryUtil.binaryToDecimalism(String.valueOf("11111111111111111111111111111111")));
    }

    /**
     * forJoin测试demo
     * @return
     */
    public static Integer count() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        //生成一个计算任务，负责计算1+2+3+4
        CountTask task = new CountTask(1, 4);

        Future result = forkJoinPool.submit(task);

        System.out.println("====结果====");
        try {
            Integer value = task.get();
            Integer value2 = (Integer) result.get();
            System.out.println("value="+value + ", value2=" + value2);
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;

    }



     public static void testCountDownLatch() throws InterruptedException {
         CountDownLatch countDownLatch = new CountDownLatch(3);
         countDownLatch.await();

         countDownLatch.countDown();


     }


    public static void threadLocal() {
        //Inheritable



    }
}

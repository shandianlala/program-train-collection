import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * print one by one, use two thread.
 *
 * @author shandianlala@gmail.com
 * @version 1.0
 * @date 2020-04-18 11:18
 */
public class ThreadSchedulingTest {

    public volatile int readIndex = 0;

    public List<String> stringList = Arrays.asList("ABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""));

    public ReentrantLock lock = new ReentrantLock(true);

    @Test
    public void testThreadScheduling() {
        Thread thread1 = generateThread("Thread-0");
        Thread thread2 = generateThread("Thread--1");
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("thread1 in main method, state=" + thread1.getState().name());
        System.out.println("thread2 in main method, state=" + thread2.getState().name());
    }

    public Thread generateThread(String threadName) {
        Thread thread = new Thread(() -> {
            synchronized (stringList) {
                String name = Thread.currentThread().getName();
                while (readIndex < stringList.size()) {
                    System.out.println(name + " | " + stringList.get(readIndex));
                    readIndex++;
                    try {
                        stringList.notify();
                        stringList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setName(threadName);
        return thread;
    }

    @Test
    public void testThreadScheduling1() {
        Thread thread1 = generateThread1("Thread-0");
        Thread thread2 = generateThread1("Thread-1");
        thread2.start();
        thread1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("thread1 in main method, state=" + thread1.getState().name());
        System.out.println("thread2 in main method, state=" + thread2.getState().name());
    }

    public Thread generateThread1(String threadName) {
        Thread thread = new Thread(() -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " in run method, state=" + Thread.currentThread().getState().name());
            while (readIndex < stringList.size()) {
                lock.lock();
                System.out.println(name + " | " + stringList.get(readIndex));
                readIndex++;
                lock.unlock();
            }
        });
        thread.setName(threadName);
        return thread;
    }


}

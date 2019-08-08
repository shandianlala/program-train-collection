package club.sdll.ptc.juc.util;

import java.util.concurrent.*;

/**
 * @description:
 */
public class ThreadPoolManager {

    private ThreadPoolManager() {

    }

    public static ThreadPoolExecutor createThreadPoolExecutorFree(int corePoolSize,
                                                                  int maximumPoolSize, int queueSize, long keepAliveTime, String groupName) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize), new PriorityThreadFactory(
                groupName, Thread.NORM_PRIORITY));
        return poolExecutor;
    }

    public static ThreadPoolExecutor createThreadPoolExecutor(int corePoolSize,
                                                              int maximumPoolSize, long keepAliveTime, String groupName) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(), new PriorityThreadFactory(
                groupName, Thread.NORM_PRIORITY));
        return poolExecutor;
    }

    public static ScheduledThreadPoolExecutor createScheduledThreadPoolExecutor(
            int threadCount, String threadName) {
        ScheduledThreadPoolExecutor scheduledThreadPool = new ScheduledThreadPoolExecutor(
                threadCount, new PriorityThreadFactory(threadName,
                Thread.NORM_PRIORITY));
        return scheduledThreadPool;
    }


    public static ScheduledFuture<?> scheduleGeneral(Runnable r, long delay, ScheduledThreadPoolExecutor scheduledThreadPool) {
        try {
            return scheduledThreadPool.schedule(r, delay, TimeUnit.MILLISECONDS);
        } catch (RejectedExecutionException e) {
            return null;
        }
    }

    public static ScheduledFuture<?> scheduleGeneralAtFixedRate(Runnable r, long initial, long delay, ScheduledThreadPoolExecutor scheduledThreadPool) {
        try {
            return scheduledThreadPool.scheduleAtFixedRate(r, initial, delay, TimeUnit.SECONDS);
        } catch (RejectedExecutionException e) {
            return null;
        }
    }

    public static void purge(ThreadPoolExecutor poolExecutor) {
        if (poolExecutor != null) {
            poolExecutor.purge();
        }
    }

    public static void shutdown(ThreadPoolExecutor poolExecutor) {
        if (poolExecutor != null && !poolExecutor.isShutdown()) {
            poolExecutor.shutdown();
        }
    }

    public static String getPoolExecutorInfo(ThreadPoolExecutor poolExecutor) {
        if (poolExecutor == null || poolExecutor.isShutdown()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(poolExecutor.getThreadFactory().toString());

        sb.append(" |- ActiveThreads:   ")
                .append(poolExecutor.getActiveCount())
                .append(" |- getCorePoolSize: ")
                .append(poolExecutor.getCorePoolSize())
                .append(" |- MaximumPoolSize: ")
                .append(poolExecutor.getMaximumPoolSize())
                .append(" |- LargestPoolSize: ")
                .append(poolExecutor.getLargestPoolSize())
                .append(" |- PoolSize:        " + poolExecutor.getPoolSize())
                .append(" |- CompletedTasks:  ")
                .append(poolExecutor.getCompletedTaskCount())
                .append(" |- QueuedTasks:     ")
                .append(poolExecutor.getQueue().size());
        return sb.toString();
    }

}

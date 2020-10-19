package club.sdll.ptc.juc.threadlocal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * https://github.com/alibaba/transmittable-thread-local
 *
 * @author shandianlala@gmail.com
 * @version 1.0
 * @date 2020-10-19 20:14
 */
public class TestTransmittableThreadLocal {

    public static TransmittableThreadLocal<TraceContext> THREAD_LOCAL = new TransmittableThreadLocal<>();

    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4,
            2, TimeUnit.MINUTES, new LinkedBlockingQueue<>());

    public static void main(String[] args) {
        TraceContext traceContext = new TraceContext();
        traceContext.setTraceId(UUID.randomUUID().toString());
        traceContext.setMethod("main");
        System.out.println("原始数据=\n" + JSON.toJSONString(traceContext, SerializerFeature.PrettyFormat));
        THREAD_LOCAL.set(traceContext);

        wrapTask();
        System.out.println("============================================================================================");
        wrapExecutor();

        threadPoolExecutor.shutdown();
    }

    public static void wrapTask() {

        // runnable
        Runnable runnable = getRunnable();
        TtlRunnable ttlRunnable = TtlRunnable.get(runnable);
        threadPoolExecutor.submit(ttlRunnable);

        // callable
        Callable<Long> callable = getCallable();
        TtlCallable<Long> ttlCallable = TtlCallable.get(callable);
        threadPoolExecutor.submit(ttlCallable);

    }

    public static void wrapExecutor() {
        ExecutorService ttlExecutorService = TtlExecutors.getTtlExecutorService(threadPoolExecutor);
        Runnable runnable = getRunnable();
        Callable<Long> callable = getCallable();

        ttlExecutorService.submit(runnable);
        ttlExecutorService.submit(callable);

    }

    public static Runnable getRunnable() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("开始 runnable。");
                TraceContext traceContext1 = THREAD_LOCAL.get();
                if (Objects.nonNull(traceContext1)) {
                    System.out.println("线程池中数据=\n" + JSON.toJSONString(traceContext1, SerializerFeature.PrettyFormat));
                } else {
                    System.out.println("traceContext1 is null.");
                }
                System.out.println("结束 runnable。");
            }
        };
        return runnable;
    }

    public static Callable<Long> getCallable() {
        Callable<Long> callable = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                System.out.println("开始 callable。");
                TraceContext traceContext1 = THREAD_LOCAL.get();
                if (Objects.nonNull(traceContext1)) {
                    System.out.println("线程池中数据=\n" + JSON.toJSONString(traceContext1, SerializerFeature.PrettyFormat));
                } else {
                    System.out.println("traceContext1 is null.");
                }
                System.out.println("结束 callable。");
                return 22L;
            }
        };
        return callable;
    }


}

package club.sdll.ptc.juc.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * description
 *
 * @author chengxiwang
 * @version v0.1
 * @date 2019年01月23日 22:25
 */
public class CountTask extends RecursiveTask<Integer> {

    /**
     * 阈值
     */
    private static final int THRESHOLD = 2;

    private int start;

    private int end;

    public CountTask() {
    }

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;

        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            //执行任务
            leftTask.fork();
            rightTask.fork();

            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            sum = leftResult + rightResult;

        }
        return sum;
    }

}

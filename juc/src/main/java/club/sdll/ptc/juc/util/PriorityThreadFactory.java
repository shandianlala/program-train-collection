package club.sdll.ptc.juc.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 */
public class PriorityThreadFactory implements ThreadFactory {
    private final int _prio;
    private final String _name;
    private final AtomicInteger _threadNumber = new AtomicInteger(1);
    private final ThreadGroup _group;

    public PriorityThreadFactory(String name, int prio) {
        _prio = prio;
        _name = name;
        _group = new ThreadGroup(_name);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(_group, r);
        t.setName(_name + "-" + _threadNumber.getAndIncrement());
        t.setPriority(_prio);
        return t;
    }

    public ThreadGroup getGroup() {
        return _group;
    }

    @Override
    public String toString() {
        return "PriorityThreadFactory [_prio=" + _prio + ", _name=" + _name
                + ", _threadNumber=" + _threadNumber + "]";
    }


}

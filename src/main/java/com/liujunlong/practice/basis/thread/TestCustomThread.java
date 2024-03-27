package com.liujunlong.practice.basis.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *
 * @author liujunlong
 * @date 2023/4/17 23:37
 */
public class TestCustomThread {
    public static void main(String[] args) {
//        testThread();
    }

    static class MyFactory implements ThreadFactory {

        private final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }


    }

    static class MyThread extends Thread {


        @Override
        public void run() {
            System.out.println(this.getName());
            System.out.println(this.getThreadGroup().getName());
            System.out.println("MyThread is running");
        }
    }


    static void testThread() {
        Executors.defaultThreadFactory();

        ThreadGroup group = new ThreadGroup("后台线程");
        Thread thread = new MyThread();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(50,
                100,
                1000L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new MyFactory());

        executor.execute(thread);
        executor.shutdown();
    }
}

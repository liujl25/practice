package com.liujunlong.practice.comsumerDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p> BlockingQueue的写法最简单。核心思想是，把并发和容量控制封装在缓冲区中。而BlockingQueue的性质天生满足这个要求。
 *
 * @author liujunlong
 * @date 2023/4/19 21:55
 */
public class BlockingQueueModel implements Model {

    private final BlockingQueue<Task> queue;

    private final AtomicInteger increTaskNo = new AtomicInteger(0);


    public BlockingQueueModel(int capacity) {
        this.queue = new LinkedBlockingQueue<>(capacity);
    }


    @Override
    public Runnable newRunnableConsumer() {
        return new AbstractConsumer() {
            @Override
            public void consume() throws InterruptedException {
                Task task = queue.take();
                Thread.sleep((long) (500+ (long) (Math.random() * 500)));
                System.out.println("consumer: "+task.no);
            }
        };
    }

    @Override
    public Runnable newRunnableProducer() {
        return new AbstractProducer() {
            @Override
            public void produce() throws InterruptedException {
                Thread.sleep(500+(long) (Math.random() * 500));
                Task task = new Task(increTaskNo.getAndIncrement());
                System.out.println("producer: "+task.no);
                queue.put(task);
            }
        };
    }

    public static void main(String[] args) throws InterruptedException {
        Model model = new BlockingQueueModel(3);
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            new Thread(model.newRunnableConsumer()).start();
        }
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(model.newRunnableProducer());
            list.add(thread);
            thread.start();
        }

        Thread.sleep(5000);
        list.forEach(Thread::interrupt);
    }
}

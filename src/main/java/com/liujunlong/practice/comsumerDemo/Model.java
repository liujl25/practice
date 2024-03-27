package com.liujunlong.practice.comsumerDemo;

/**
 * <p>
 *
 * @author liujunlong
 * @date 2023/4/19 21:52
 */
public interface Model {
    Runnable newRunnableConsumer();
    Runnable newRunnableProducer();
}

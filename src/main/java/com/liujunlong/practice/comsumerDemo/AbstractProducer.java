package com.liujunlong.practice.comsumerDemo;

/**
 * <p>
 *
 * @author liujunlong
 * @date 2023/4/19 21:51
 */
public abstract class AbstractProducer implements Producer, Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

package com.liujunlong.practice.comsumerDemo;

/**
 * <p>
 *
 * @author liujunlong
 * @date 2023/4/19 21:48
 */
public abstract class AbstractConsumer implements Consumer,Runnable{
    @Override
    public void run() {
        while (true) {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

}

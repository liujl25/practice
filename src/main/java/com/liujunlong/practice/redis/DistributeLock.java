package com.liujunlong.practice.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 使用redis实现分布式锁
 *
 * @author liujunlong
 * @since 2020/10/7 19:14
 */
public class DistributeLock {

    @Autowired
    public RedisTemplate redisTemplate;

    public void test(String key,String value){

        String random = "";

        //random 自己创建的随机值
        boolean islock = redisTemplate.opsForValue().setIfAbsent(key, random, Duration.ofSeconds(100));

        if (islock) {
            //todo
            //如果是自己创建的锁，删除key，释放锁
            //防止因为处理过程太久，key过期了，误删其他请求创建的锁
            if (redisTemplate.opsForValue().get(key).equals(random)) {
                redisTemplate.delete(key);
            }
        }
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            return 1;
        });
        future.join();
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        CompletableFuture.allOf(future).join();
    }


}

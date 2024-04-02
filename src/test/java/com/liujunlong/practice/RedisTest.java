package com.liujunlong.practice;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {

    }

    @Test
    public void testIncr() {
//        redisTemplate.opsForValue().set("incr","0");
//        redisTemplate.opsForValue().increment("incr");

        redisTemplate.opsForValue().set("order:token", "token123456", 10, TimeUnit.SECONDS);
        //分布式锁
        Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent("distributeLock", "1",30,TimeUnit.SECONDS);
        System.out.println(lock ? "lock成功" : "lock失败");
//        stringRedisTemplate.delete("distributeLock");
    }

    @Test
    public void testHash() {
        redisTemplate.opsForHash().put("hashTable", "key", "value");
        redisTemplate.opsForHash().put("hashTable", "key1", "value2");

    }

    @Test
    public void testSet() {
        redisTemplate.opsForSet().add("set", "args1", "args2","args3","args4","args5","args6");
        System.out.print("随机抽奖：");
        System.out.println(redisTemplate.opsForSet().randomMember("set"));
    }

    @Test
    public void testZset() {
        redisTemplate.opsForZSet().add("zset", "value1", 0.1);
        redisTemplate.opsForZSet().add("zset", "value2", 0.2);
        redisTemplate.opsForZSet().add("zset", "value3", 0.6);
        redisTemplate.opsForZSet().add("zset", "value4", 0.2);
        redisTemplate.opsForZSet().add("zset", "value5", 0.15);
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset", 0.05, 0.1));
    }

    @Test
    public void testList() {
            ListOperations<String, String> listOperations = redisTemplate.opsForList();
    //        listOperations.leftPush("list1","v1");
//        listOperations.leftPush("list12","v2");
//        listOperations.leftPush("list12","v3");
//        listOperations.leftPush("list12","v4");
        System.out.println(listOperations.leftPop("list12"));;

    }

    @Test
    public void testBitsMap() {
        stringRedisTemplate.opsForValue().setBit("bitkey", 1, true);
        stringRedisTemplate.opsForValue().setBit("bitkey", 2, true);
        stringRedisTemplate.opsForValue().setBit("bitkey", 3, true);
        stringRedisTemplate.opsForValue().setBit("bitkey", 4, true);
        stringRedisTemplate.opsForValue().setBit("bitkey", 5, true);
        //访问频率
        Long frequency = redisTemplate.execute((RedisCallback<Long>) connection ->
                connection.bitCount("bitkey".getBytes()));
        System.out.println(frequency);
        Object o = AopContext.currentProxy();
    }

}

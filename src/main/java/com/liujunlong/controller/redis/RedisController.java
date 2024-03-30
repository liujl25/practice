package com.liujunlong.controller.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/str/{key}/{value}")
    public Integer testRedis(@PathVariable("key") @Valid @NotNull String key, @PathVariable("value") String value) {
        redisTemplate.opsForValue().set(key, value);
        return 0;
    }

    @DeleteMapping("/str/{key}")
    public Integer delete(@PathVariable("key") @Valid @NotNull String key) {
        redisTemplate.delete(key);
        return 0;
    }

}

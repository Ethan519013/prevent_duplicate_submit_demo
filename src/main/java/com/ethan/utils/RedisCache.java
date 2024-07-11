package com.ethan.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * spring redis 工具类
 *
 **/
@Component
public class RedisCache
{
    @Autowired
    public RedisTemplate redisTemplate;


    public Boolean isCaught(String key, int interval, TimeUnit timeUnit) {
        Object object = redisTemplate.opsForValue().get(key);
        if (object != null) {
            redisTemplate.expire(key, interval, timeUnit);
            return true;
        }
        redisTemplate.opsForValue().set(key, "", interval, timeUnit);
        return false;
    }
}

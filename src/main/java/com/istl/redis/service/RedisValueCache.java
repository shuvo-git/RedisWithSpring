package com.istl.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Service
public class RedisValueCache {
    private final ValueOperations<String, Object> valueOperations;

    @Autowired
    public RedisValueCache(RedisTemplate<String, Object> redisTemplate) {
        this.valueOperations = redisTemplate.opsForValue();
    }

    public void cache(final String key, final Object data) {
        valueOperations.set(key, data);
        // valueOperations.set(key,data,10, TimeUnit.SECONDS);
    }

    public Object getCachedValue(final String key) {
        return valueOperations.get(key);
    }

    public void deleteCachedValue(final String key) {
        valueOperations.getOperations().delete(key);
    }


}

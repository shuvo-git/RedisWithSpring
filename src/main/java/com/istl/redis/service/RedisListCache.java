package com.istl.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RedisListCache {
    private final ValueOperations<String, Object> valueOperations;

    @Autowired
    public RedisListCache(RedisTemplate<String, Object> redisTemplate)
    {
        this.valueOperations = redisTemplate.opsForValue();
    }

    public  void cache(final String key, final Object data){
        valueOperations.set(key,data);
    }

    public Object getCachedValue(final String key){
        return valueOperations.get(key);
    }

    public  void deleteCachedValue(final String key){
        valueOperations.getOperations().delete(key);
    }


}

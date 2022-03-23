package com.istl.redis.service.locker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;

@Service
@Slf4j
public class DistributedLocker {
    private static final long DEFAULT_RETRY_TIME = 100L;

    private final ValueOperations<String, Object> valueOps;

    @Autowired
    public DistributedLocker(final RedisTemplate<String, Object> redisTemplate) {
        this.valueOps = redisTemplate.opsForValue();
    }

    public <T> LockExecutionResult<T> lock(final String key,
                                           final int howLongLockShouldBeAcquiredSeconds,
                                           final int lockTimeoutSeconds,
                                           final Callable<T> task
                                           ){
        try{
            return tryToGetLock(()->{
                //final boolean lockAcquired = ;

            },key,howLongLockShouldBeAcquiredSeconds);

        }
        catch (final Exception e){
            log.error(e.getMessage(),e);
            return LockExecutionResult.buildLockAcquiredWithException(e);
        }

    }
}

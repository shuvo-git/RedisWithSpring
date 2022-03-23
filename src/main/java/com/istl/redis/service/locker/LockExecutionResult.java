package com.istl.redis.service.locker;

import lombok.Getter;

public class LockExecutionResult<T>
{
    @Getter
    private final boolean lockAcquired;

    @Getter
    public final T resultIfLockAcquired;
    public final Exception exception;


    private LockExecutionResult(boolean lockAcquired, T resultIfLockAcquired, Exception exception) {
        this.lockAcquired = lockAcquired;
        this.resultIfLockAcquired = resultIfLockAcquired;
        this.exception = exception;
    }

    public static <T> LockExecutionResult<T> buildLockAcquiredResult(final T result){
        return new LockExecutionResult<>(true, result, null);
    }

    public static <T> LockExecutionResult<T> buildLockAcquiredWithException(final Exception e){
        return new LockExecutionResult<>(true, null, e);
    }

    public static <T> LockExecutionResult<T> lockNotAcquired(){
        return new LockExecutionResult<>(false, null, null);
    }
}

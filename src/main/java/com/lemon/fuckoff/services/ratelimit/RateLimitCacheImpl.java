package com.lemon.fuckoff.services.ratelimit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RateLimitCacheImpl<K, V> implements RateLimitCache<K, V> {

    @Autowired
    private RedisTemplate<K, V> redisTemplate;

    @Override
    public void save(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void saveWithTimeout(K key, V value, Integer timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, timeout, timeUnit);
    }

    @Override
    public V get(K key) {
        return redisTemplate.opsForValue().get(key);
    }
}

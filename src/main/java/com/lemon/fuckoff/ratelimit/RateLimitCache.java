package com.lemon.fuckoff.ratelimit;

import java.util.concurrent.TimeUnit;

public interface RateLimitCache<K, V> {

    void save(K key, V value);

    void saveWithTimeout(K key, V value, Integer timeout, TimeUnit timeUnit);

    V get(K key);

}

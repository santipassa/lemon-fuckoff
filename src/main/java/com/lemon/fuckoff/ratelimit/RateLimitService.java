package com.lemon.fuckoff.ratelimit;

public interface RateLimitService {
    void tryToConsume(String key);

}

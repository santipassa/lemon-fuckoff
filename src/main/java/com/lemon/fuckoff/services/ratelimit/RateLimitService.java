package com.lemon.fuckoff.services.ratelimit;

public interface RateLimitService {
    void tryToConsume(String key);

}

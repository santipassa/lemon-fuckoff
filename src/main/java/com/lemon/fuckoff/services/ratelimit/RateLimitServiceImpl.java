package com.lemon.fuckoff.services.ratelimit;

import com.lemon.fuckoff.exceptions.TooManyRequestsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RateLimitServiceImpl implements RateLimitService {

    private final String REFILL_KEY = "refill-%s";
    private final String AVAILABLE_BUCKET_KEY = "available-bucket-%s";
    @Value("${rate-limit.refill.period}")
    private Integer refillPeriod;
    @Value("${rate-limit.refill.unit}")
    private String refillUnit;
    @Value("${rate-limit.bucket-size}")
    private Integer bucketSize;
    @Autowired
    private RateLimitCache<String, String> rateLimitCache;

    /**
     * Check if the request is available
     *
     * @param key
     */
    @Override
    public void tryToConsume(String key) {
        if (isTimeToRefill(key)) {
            log.info(String.format("Time to refill bucket"));
            refillBucket(key);
        } else {
            int availableLimit = getAvailableLimit(key);
            if (availableLimit == 0) {
                log.warn(String.format("Request without available limit"));
                throw new TooManyRequestsException("Too many requests, try later");
            }
            log.info(String.format("Decrement available limit"));
            saveCurrentAvailableLimit(key, availableLimit - 1);
        }
    }

    private boolean isTimeToRefill(String key) {
        return null == rateLimitCache.get(String.format(REFILL_KEY, key));
    }

    private void refillBucketTimeForKey(String key) {
        rateLimitCache.saveWithTimeout(String.format(REFILL_KEY, key), "dummy-value", refillPeriod, getTimeUnit(refillUnit));
    }

    private void saveCurrentAvailableLimit(String key, Integer availableRequests) {
        rateLimitCache.save(String.format(AVAILABLE_BUCKET_KEY, key), availableRequests.toString());
    }

    private Integer getAvailableLimit(String userId) {
        return Integer.parseInt(rateLimitCache.get(String.format(AVAILABLE_BUCKET_KEY, userId)));
    }

    private void refillBucket(String key) {
        refillBucketTimeForKey(key);
        saveCurrentAvailableLimit(key, this.bucketSize - 1);
    }


    private TimeUnit getTimeUnit(String timeUnit) {
        switch (timeUnit) {
            case "seconds":
                return TimeUnit.SECONDS;
            case "minutes":
                return TimeUnit.MINUTES;
            default:
                throw new IllegalArgumentException("Time unit not supported");
        }
    }

}

package com.lemon.fuckoff.interceptors;

import com.lemon.fuckoff.ratelimit.RateLimitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class RateLimitInterceptor implements HandlerInterceptor {

    private static final String HEADER_USER_ID = "X-User-Id";

    @Autowired
    RateLimitService rateLimitService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userId = request.getHeader(HEADER_USER_ID);
        rateLimitService.tryToConsume(userId);
        return true;
    }
}

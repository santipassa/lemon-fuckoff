package com.lemon.fuckoff.interceptors;

import com.lemon.fuckoff.exceptions.UserNotAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

    private static final String HEADER_USER_ID = "X-User-Id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userId = request.getHeader(HEADER_USER_ID);
        final String AUTHORIZED_USER = "1234";
        if (!AUTHORIZED_USER.equals(userId)) {
            log.info(String.format("User '%s' is not authorized", userId));
            throw new UserNotAuthorizedException("You are not authorized to access to this resource.");
        }
        log.info(String.format("User '%s' is authorized", userId));
        return true;
    }
}

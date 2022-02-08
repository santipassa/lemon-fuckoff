package com.lemon.fuckoff.interceptors;

import com.lemon.fuckoff.exceptions.UserNotAuthorizedException;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
@Slf4j
@ConfigurationProperties(prefix = "authorization")
public class AuthorizationInterceptor implements HandlerInterceptor {

    private static final String HEADER_USER_ID = "X-User-Id";

    @Value("${users-white-list}")
    List<String> usersInWhiteList;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userId = request.getHeader(HEADER_USER_ID);

        if (userId == null || userId.isEmpty() || !usersInWhiteList.contains(userId)) {
            log.info(String.format("User '%s' is not authorized", userId));
            throw new UserNotAuthorizedException("You are not authorized to access to this resource.");
        }
        log.info(String.format("User '%s' is authorized", userId));
        return true;
    }
}

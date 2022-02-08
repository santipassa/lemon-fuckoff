package com.lemon.fuckoff.handlers;

import com.lemon.fuckoff.dto.ApiErrorDTO;
import com.lemon.fuckoff.exceptions.TooManyRequestsException;
import com.lemon.fuckoff.exceptions.UserNotAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotAuthorizedException.class)
    public ResponseEntity<ApiErrorDTO> userNotAuthorizedHandler(UserNotAuthorizedException ex) {
        ApiErrorDTO apiErrorDTO = ApiErrorDTO.builder().error("user_not_authorized").message(ex.getMessage()).status(HttpStatus.UNAUTHORIZED.value()).build();
        return ResponseEntity.status(apiErrorDTO.getStatus()).body(apiErrorDTO);
    }

    @ExceptionHandler(TooManyRequestsException.class)
    public ResponseEntity<ApiErrorDTO> tooManyRequestsExceptionHandler(TooManyRequestsException ex) {
        ApiErrorDTO apiErrorDTO = ApiErrorDTO.builder().error("too_many_requests").message(ex.getMessage()).status(HttpStatus.TOO_MANY_REQUESTS.value()).build();
        return ResponseEntity.status(apiErrorDTO.getStatus()).body(apiErrorDTO);
    }


}

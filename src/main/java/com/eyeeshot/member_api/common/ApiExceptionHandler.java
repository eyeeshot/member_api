package com.eyeeshot.member_api.common;


import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({ApiException.class})
    public ResponseDto exceptionHandler(final ApiException e) {
        return ResponseDto
                .res(e.getServiceErrorType().getHttpStatus().value(),e.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseDto exceptionHandler(final RuntimeException e) {
        return ResponseDto
                .res(ServiceErrorType.RUNTIME_EXCEPTION.getHttpStatus().value(),e.getMessage());
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseDto exceptionHandler(final AccessDeniedException e) {
        return ResponseDto
                .res(ServiceErrorType.UNAUTHORIZED.getHttpStatus().value(),e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResponseDto exceptionHandler(HttpServletRequest request, final Exception e) {
        return ResponseDto
                .res(ServiceErrorType.INTERNAL_SERVER_ERROR.getHttpStatus().value(),e.getMessage());
    }
}

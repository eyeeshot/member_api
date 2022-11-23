package com.eyeeshot.member_api.common;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ApiException extends RuntimeException {
    private final ServiceErrorType serviceErrorType;

    public ApiException(ServiceErrorType serviceErrorType) {
        super(serviceErrorType.getMessage());
        this.serviceErrorType = serviceErrorType;
    }
}

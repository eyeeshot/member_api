package com.eyeeshot.member_api.common;

import org.springframework.http.HttpStatus;

public enum ServiceErrorType {
    OK(HttpStatus.OK, "정상 처리 되었습니다."),
    CREATED(HttpStatus.CREATED, "등록 되었습니다."),
    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "Bad Request"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,  "비 인가 사용자입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN,  "권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND,  "해당 리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,  "시스템에 문제가 발생하였습니다."),
    INVALID_PARAMETER(HttpStatus.CONFLICT,  "유효하지 않은 전달값입니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT,  "이미 가입되어 있는 이메일 입니다."),
    DUPLICATE_PHONENUMBER(HttpStatus.CONFLICT,  "이미 가입되어 있는 휴대번호 입니다."),
    OLDPASSWORD(HttpStatus.CONFLICT, "이전 패스워드 입니다. 변경해주시기 바랍니다."),
    MISMATCHPASSWORD(HttpStatus.CONFLICT, "비밀번호가 일치하지 않습니다."),
    MISMATCH(HttpStatus.CONFLICT, "인증번호가 일치하지 않습니다.");

    HttpStatus httpStatus;
    String message;

    ServiceErrorType(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public String getMessage() {
        return this.message;
    }
}

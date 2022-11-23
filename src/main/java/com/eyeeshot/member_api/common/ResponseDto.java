package com.eyeeshot.member_api.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseDto<T> {
    private final int statusCode;
    private final String responseMessage;
    private final T data;

    public ResponseDto(final int statusCode, final String responseMessage) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
        this.data = null;
    }

    public static<T> ResponseDto<T> res(final int statusCode, final String responseMessage) {
        return res(statusCode, responseMessage, null);
    }

    public static<T> ResponseDto<T> res(final int statusCode, final String responseMessage, final T t) {
        return ResponseDto.<T>builder()
                .data(t)
                .statusCode(statusCode)
                .responseMessage(responseMessage)
                .build();
    }
}

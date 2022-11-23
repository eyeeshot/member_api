package com.eyeeshot.member_api.certification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SmsSendDto {
    @Schema(description = "전화번호")
    private String phoneNumber;
    @Schema(description = "인증타입")
    private SmsType smsType;
}

package com.eyeeshot.member_api.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FindPasswordDto {
    @Schema(description = "전화번호")
    private String phoneNumber;
    @Schema(description = "변경비밀번호")
    private String password;
}

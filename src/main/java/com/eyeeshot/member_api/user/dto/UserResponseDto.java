package com.eyeeshot.member_api.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    @Schema(description = "닉네임")
    private String nickName;
    @Schema(description = "이메일")
    private String email;
    @Schema(description = "이름")
    private String name;
    @Schema(description = "전화번호")
    private String phoneNumber;
}

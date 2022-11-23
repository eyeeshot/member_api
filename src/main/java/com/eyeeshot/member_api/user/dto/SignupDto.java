package com.eyeeshot.member_api.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SignupDto {
    @Schema(description = "닉네임")
    private String nickName;
    @Schema(description = "이메일")
    private String email;
    @Schema(description = "이름")
    private String name;
    @Schema(description = "전화번호")
    private String phoneNumber;
    @Schema(description = "비밀번호")
    private String password;
}

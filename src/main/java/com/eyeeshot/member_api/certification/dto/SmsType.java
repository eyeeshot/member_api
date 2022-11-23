package com.eyeeshot.member_api.certification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SmsType {
    SIGNUP("signUp"),
    PASSWORDCHANGE("passwordChange");

    String value;
}

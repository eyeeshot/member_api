package com.eyeeshot.member_api.controller;

import com.eyeeshot.member_api.certification.dto.SmsCertificationDto;
import com.eyeeshot.member_api.certification.dto.SmsSendDto;
import com.eyeeshot.member_api.certification.service.SmsService;
import com.eyeeshot.member_api.common.ResponseDto;
import com.eyeeshot.member_api.security.service.AuthService;
import com.eyeeshot.member_api.user.dto.LoginDto;
import com.eyeeshot.member_api.user.dto.SignupDto;
import com.eyeeshot.member_api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sms")
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/send")
    public ResponseDto send(@RequestBody SmsSendDto smsSendDto) {
        return smsService.sendSms(smsSendDto);
    }

    @PostMapping("/verify")
    public ResponseDto verify(@RequestBody SmsCertificationDto smsCertificationDto) {
        return smsService.confirmSms(smsCertificationDto);
    }
}

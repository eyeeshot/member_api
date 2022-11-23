package com.eyeeshot.member_api.certification.service;

import com.eyeeshot.member_api.certification.dto.SmsCertificationDto;
import com.eyeeshot.member_api.certification.dto.SmsSendDto;
import com.eyeeshot.member_api.certification.dao.Smscertification;
import com.eyeeshot.member_api.common.ResponseDto;
import com.eyeeshot.member_api.common.ServiceErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsService {

    private final Smscertification smscertification;

    private final int CONFIRM_LIMIT_TIME = 3 * 60;
    private final int SUBMIT_LIMIT_TIME = 10 * 60;

    public ResponseDto sendSms(SmsSendDto smsSendDto) {
        String key = smsSendDto.getSmsType().getValue() + ":" + smsSendDto.getPhoneNumber();
        String value = String.valueOf(generateCertificationNumber());
        smscertification.createSmsCertification(key,value,CONFIRM_LIMIT_TIME);

        return ResponseDto.res(ServiceErrorType.OK.getHttpStatus().value(),ServiceErrorType.OK.getMessage());
    }

    public ResponseDto confirmSms(SmsCertificationDto smsCertificationDto) {
        String key = smsCertificationDto.getSmsType().getValue() + ":" + smsCertificationDto.getPhoneNumber();

        if (smscertification.hasKey(key) &&
                smscertification.getSmsCertification(key).equals(smsCertificationDto.getCertificationNumber())) {
            String submitKey = "submit" + ":" + key;

            smscertification.deleteSmsCertification(key);
            smscertification.createSmsCertification(submitKey,smsCertificationDto.getCertificationNumber(),SUBMIT_LIMIT_TIME);

            return ResponseDto.res(ServiceErrorType.OK.getHttpStatus().value(),ServiceErrorType.OK.getMessage());
        }

        return ResponseDto.res(ServiceErrorType.MISMATCH.getHttpStatus().value(),ServiceErrorType.MISMATCH.getMessage());
    }

    public static int generateCertificationNumber() {
        java.util.Random generator = new java.util.Random();
        generator.setSeed(System.currentTimeMillis());
        return generator.nextInt(1000000) % 1000000;
    }
}

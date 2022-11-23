package com.eyeeshot.member_api.user.service;

import com.eyeeshot.member_api.certification.dao.Smscertification;
import com.eyeeshot.member_api.certification.dto.SmsType;
import com.eyeeshot.member_api.common.ApiException;
import com.eyeeshot.member_api.common.ResponseDto;
import com.eyeeshot.member_api.common.ServiceErrorType;
import com.eyeeshot.member_api.user.dao.AuthorityRepository;
import com.eyeeshot.member_api.user.domain.Authority;
import com.eyeeshot.member_api.user.domain.User;
import com.eyeeshot.member_api.user.dto.FindPasswordDto;
import com.eyeeshot.member_api.user.dto.SignupDto;
import com.eyeeshot.member_api.user.dto.UserResponseDto;
import com.eyeeshot.member_api.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final Smscertification smscertification;

    @Transactional
    public ResponseDto signup(SignupDto signupDto) {
        String key = "submit:" + SmsType.SIGNUP.getValue() + ":" + signupDto.getPhoneNumber();
        if(!smscertification.hasKey(key)) {
            throw new ApiException(ServiceErrorType.INVALID_PARAMETER);
        }

        if (userRepository.findByEmail(signupDto.getEmail()).isPresent()) {
            throw new ApiException(ServiceErrorType.DUPLICATE_EMAIL);
        }

        if (userRepository.findByPhoneNumber(signupDto.getPhoneNumber()).isPresent()) {
            throw new ApiException(ServiceErrorType.DUPLICATE_PHONENUMBER);
        }

        Authority authority = authorityRepository.findById(2L).orElseThrow(NullPointerException::new);

        User user = User.builder()
                .email(signupDto.getEmail())
                .nickName(signupDto.getNickName())
                .name(signupDto.getName())
                .phoneNumber(signupDto.getPhoneNumber())
                .password(signupDto.getPassword())
                .authorities(authority)
                .build();

        userRepository.save(user);

        UserResponseDto signupResponseDto = UserResponseDto.builder()
                .email(signupDto.getEmail())
                .name(signupDto.getName())
                .nickName(signupDto.getNickName())
                .phoneNumber(signupDto.getPhoneNumber())
                .build();

        return ResponseDto.res(ServiceErrorType.CREATED.getHttpStatus().value(),ServiceErrorType.CREATED.getMessage()
                ,signupResponseDto);
    }

    @Transactional
    public ResponseDto profile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(NullPointerException::new);

        UserResponseDto signupResponseDto = UserResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .nickName(user.getNickName())
                .phoneNumber(user.getPhoneNumber())
                .build();

        return ResponseDto.res(ServiceErrorType.OK.getHttpStatus().value(),ServiceErrorType.OK.getMessage(),signupResponseDto);
    }

    @Transactional
    public ResponseDto updateUser(FindPasswordDto findPasswordDto) {
        String key = "submit:" + SmsType.PASSWORDCHANGE.getValue() + ":" + findPasswordDto.getPhoneNumber();
        if(!smscertification.hasKey(key)) {
            throw new ApiException(ServiceErrorType.INVALID_PARAMETER);
        }

        User user = userRepository.findByPhoneNumber(findPasswordDto.getPhoneNumber())
                .orElseThrow(NullPointerException::new);

        if (user.getPassword().equals(findPasswordDto.getPassword())) {
            throw new ApiException(ServiceErrorType.OLDPASSWORD);
        }

        user.setPassword(findPasswordDto.getPassword());

        userRepository.save(user);

        UserResponseDto userResponseDto = UserResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .nickName(user.getNickName())
                .phoneNumber(user.getPhoneNumber())
                .build();

        return ResponseDto.res(ServiceErrorType.OK.getHttpStatus().value(),ServiceErrorType.OK.getMessage(), userResponseDto);
    }
}

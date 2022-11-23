package com.eyeeshot.member_api.security.service;

import com.eyeeshot.member_api.common.ResponseDto;
import com.eyeeshot.member_api.common.ServiceErrorType;
import com.eyeeshot.member_api.security.jwt.TokenProvider;
import com.eyeeshot.member_api.user.dto.LoginDto;
import com.eyeeshot.member_api.user.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final TokenProvider tokenProvider;

    public ResponseDto login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        TokenDto tokenDto = tokenProvider.createToken(authentication);

        return ResponseDto.res(ServiceErrorType.OK.getHttpStatus().value(),ServiceErrorType.OK.getMessage()
                ,tokenDto);
    }
}

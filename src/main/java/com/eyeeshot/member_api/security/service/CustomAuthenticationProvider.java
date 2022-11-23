package com.eyeeshot.member_api.security.service;

import com.eyeeshot.member_api.common.ApiException;
import com.eyeeshot.member_api.common.ServiceErrorType;
import com.eyeeshot.member_api.user.domain.User;
import com.eyeeshot.member_api.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication){
      String name = authentication.getName();
      String password = authentication.getCredentials().toString();

      User user = userRepository.findByEmail(name)
          .orElseThrow(NullPointerException::new);

      if (!user.isMatchPassword(password)) {
          throw new ApiException(ServiceErrorType.MISMATCHPASSWORD);
      }

      SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(
              user.getAuthorities().getRole());

      //임시 인증객체를 진짜 인증객체로 생성
      return new UsernamePasswordAuthenticationToken(name, password,
              new ArrayList<>(Collections.singletonList(simpleGrantedAuthority)));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
            UsernamePasswordAuthenticationToken.class);
    }
}

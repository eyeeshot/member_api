package com.eyeeshot.member_api.config;

import com.eyeeshot.member_api.security.jwt.JWTFilter;
import com.eyeeshot.member_api.security.model.CustomAccessDeniedHandler;
import com.eyeeshot.member_api.security.model.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JWTFilter jwtFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CorsConfig corsConfig;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {

        security.csrf().disable()
            .headers().frameOptions().disable();

        security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        security.authorizeHttpRequests()
            .antMatchers("/api/users","/api/sms/**", "/api/users/login","/h2-console/**","/v2/api-docs",  "/configuration/ui",
                    "/swagger-resources/**", "/swagger-ui/**","/swagger/**").permitAll()
            .antMatchers("/api/users/profile").authenticated()
            .anyRequest().authenticated();

        security
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        security
            .exceptionHandling()
            .accessDeniedHandler(customAccessDeniedHandler)
            .authenticationEntryPoint(customAuthenticationEntryPoint);

        security.formLogin().disable();

        security.httpBasic().disable();

        security.addFilter(corsConfig.corsFilter());

        return security.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() {
        List<AuthenticationProvider> authenticationProviderList = new ArrayList<>();
        authenticationProviderList.add(authenticationProvider);
        ProviderManager authenticationManager = new ProviderManager(authenticationProviderList);
        authenticationManager.setAuthenticationEventPublisher(defaultAuthenticationEventPublisher());
        return authenticationManager;
    }

    @Bean
    DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher() {
    return new DefaultAuthenticationEventPublisher();
    }


}

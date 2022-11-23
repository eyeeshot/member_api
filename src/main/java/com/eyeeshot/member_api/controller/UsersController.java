package com.eyeeshot.member_api.controller;

import com.eyeeshot.member_api.common.ResponseDto;
import com.eyeeshot.member_api.security.service.AuthService;
import com.eyeeshot.member_api.user.dto.FindPasswordDto;
import com.eyeeshot.member_api.user.dto.LoginDto;
import com.eyeeshot.member_api.user.dto.SignupDto;
import com.eyeeshot.member_api.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final AuthService authService;
    private final UserService userService;

    @ApiOperation(value = "회원가입", notes = "회원가입")
    @PostMapping("")
    public ResponseDto signup(@RequestBody SignupDto signupDto) {
        return userService.signup(signupDto);
    }

    @ApiOperation(value = "로그인", notes = "로그인")
    @PostMapping("/login")
    public ResponseDto login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @ApiOperation(value = "내정보", notes = "내정보")
    @GetMapping("/profile")
    public ResponseDto profile() { return userService.profile(); }

    @ApiOperation(value = "패스워드변경", notes = "패스워드변경")
    @PutMapping("")
    public ResponseDto updateUser(@RequestBody FindPasswordDto findPasswordDto) { return userService.updateUser(findPasswordDto); }
}

package com.back.service.config;

import com.back.service.jwt.TokenProvider;
import com.back.service.service.LoginService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtSecurityConfig {

    private final TokenProvider tokenProvider;
    private final LoginService loginService;
}

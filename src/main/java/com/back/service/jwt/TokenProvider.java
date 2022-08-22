package com.back.service.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;

@Slf4j
@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60 * 30;   // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60 * 60;  // 1시간

//    private final Key key;


}

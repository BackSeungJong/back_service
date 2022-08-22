package com.back.service.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {

    private int userSn;
    private String userNm;
    private String email;
    private String birthDay;
    private String regDt;
}

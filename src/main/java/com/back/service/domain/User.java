package com.back.service.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends Page{

    private int userSn;
    private String userNm;
    private String email;
    private String birthDay;
    private String regDt;
}

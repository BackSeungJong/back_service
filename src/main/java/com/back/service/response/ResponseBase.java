package com.back.service.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseBase {

    private String result;
    private String code;
    private Object filter;
    private Object data;
}

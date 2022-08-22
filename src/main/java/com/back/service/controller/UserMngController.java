package com.back.service.controller;

import com.back.service.code.CommonCode;
import com.back.service.domain.User;
import com.back.service.response.ResponseBase;
import com.back.service.service.UserMngService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/userMng")
public class UserMngController {

    private final Logger logger = LoggerFactory.getLogger(UserMngController.class);
    @Autowired
    private UserMngService userMngService;

    @GetMapping("/search")
    public ResponseBase getUserList(User searchInfo) {
        logger.info("api get success");
        List<User> resultList = userMngService.selectUserList(searchInfo);

        return ResponseBase.builder()
                .result(CommonCode.ApiResult.OK.name())
                .build();
    }

}

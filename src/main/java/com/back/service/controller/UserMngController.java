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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/userMng")
public class UserMngController {

    private final Logger logger = LoggerFactory.getLogger(UserMngController.class);
    @Autowired
    private UserMngService userMngService;

    @GetMapping("/search")
    public ResponseBase getUserList(@RequestParam(name = "searchInfo", required = false, defaultValue = "") String searchInfo,
                                    @RequestParam(name = "pageNo", required = false, defaultValue = "1") String pageNo) {
        logger.info("api get success");

        User params = new User();
        params.setSearchInfo(searchInfo);
        params.setPageNo(Integer.parseInt(pageNo));
        List<User> resultList = userMngService.selectUserList(params);

        logger.info("resultList" + resultList);
        return ResponseBase.builder()
                .result(CommonCode.ApiResult.OK.name())
                .build();
    }

}

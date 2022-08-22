package com.back.service.service;

import com.back.service.dao.UserDao;
import com.back.service.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userMngService")
public class UserMngService {

    @Autowired
    private UserDao userDao;

    public List<User> selectUserList(User searchInfo){
        return userDao.selectUserList(searchInfo);
    }

}

package com.back.service.dao;

import com.back.service.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {

    private final SqlSession sqlSession;

    public UserDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<User> selectUserList(User searchInfo) {
        return sqlSession.selectList("selectUserList", searchInfo);
    }
}

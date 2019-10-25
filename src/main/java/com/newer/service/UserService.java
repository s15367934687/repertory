package com.newer.service;

import com.newer.domain.User;
import com.newer.mapper.UserMapper;
import com.newer.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import com.newer.util.MD5;

public class UserService {

    private SqlSession sqlSession;
    private UserMapper userMapper;

    private void init(){
        sqlSession = SqlSessionUtil.getSqlSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    public User findBy(String username,String password){
        init();
        String pwd = MD5.getInstance().getMD5ofStr(password);
        User user = userMapper.findBy(username,pwd);
        SqlSessionUtil.close(sqlSession);
        return user;
    }
}

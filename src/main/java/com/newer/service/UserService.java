package com.newer.service;

import com.newer.domain.User;
import com.newer.mapper.UserMapper;
import com.newer.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import com.newer.util.MD5;

import java.util.List;

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
    public int upDate(String pwd,int userId){
        init();
        String pwd1 = MD5.getInstance().getMD5ofStr(pwd);
        int count = userMapper.upDate(pwd1,userId);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
        return count;
    }
    public List<User> findAll(){
        init();
        List<User> list = userMapper.findAll();
        SqlSessionUtil.close(sqlSession);
        return list;
    }
    public int delete(int id){
        init();
        int count = userMapper.delete(id);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
        return count;
    }
    public int insert(User user){
        init();
        user.setPwd(MD5.getInstance().getMD5ofStr(user.getPwd()));
        int count = userMapper.insert(user);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
        return count;
    }
}

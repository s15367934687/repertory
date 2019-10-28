package com.newer.service;

import com.newer.domain.InStorage;
import com.newer.mapper.InStorageMapper;
import com.newer.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class InStorageService {
    private SqlSession sqlSession;
    private InStorageMapper inStorageMapper;

    private void init(){
        sqlSession = SqlSessionUtil.getSqlSession();
        inStorageMapper = sqlSession.getMapper(InStorageMapper.class);
    }

    public List<InStorage> findAll(){
        init();
        List<InStorage> list = inStorageMapper.findAll();
        SqlSessionUtil.close(sqlSession);
        return list;
    }

    public int insert(InStorage inStorage){
        init();
        int count = inStorageMapper.insert(inStorage);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
        return count;
    }
}

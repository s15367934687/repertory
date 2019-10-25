package com.newer.service;

import com.newer.domain.Stall;
import com.newer.mapper.StallMapper;
import com.newer.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StallService {
    private SqlSession sqlSession;
    private StallMapper stallMapper;

    private void init(){
        sqlSession = SqlSessionUtil.getSqlSession();
        stallMapper = sqlSession.getMapper(StallMapper.class);
    }

    public int upDate(int place,int cwId){
        init();
        int count = stallMapper.upDate(place,cwId);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
        return count;
    }

    public List<Stall> findAll(){
        init();
        List<Stall> list = stallMapper.findAll();
        SqlSessionUtil.close(sqlSession);
        return list;
    }
}

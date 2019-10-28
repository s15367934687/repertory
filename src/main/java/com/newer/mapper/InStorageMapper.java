package com.newer.mapper;

import com.newer.domain.InStorage;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface InStorageMapper {
    @Select("select * from tb_rkjl")
    @Results(value = {@Result(property = "rkId",column = "rkid",id = true),
            @Result(property = "cid",column = "cid"),
            @Result(property = "moderId",column = "moderid"),
            @Result(property = "stall",column = "stall"),
            @Result(property = "address",column = "address"),
            @Result(property = "rkTime",column = "rktime"),
            @Result(property = "czUser",column = "czuser"),
            @Result(property = "mold",javaType = com.newer.domain.Mold.class,
                    column = "moderid",one = @One(select = "com.newer.mapper.MoldMapper.findById"))})
    List<InStorage> findAll();

    @Insert("insert into tb_rkjl values(null,#{cid},#{moderId},#{stall},#{rkTime},#{czUser})")
    int insert(InStorage inStorage);
}

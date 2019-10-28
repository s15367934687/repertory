package com.newer.mapper;

import com.newer.domain.InStorage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface InStorageMapper {
    @Select("select * from tb_rkjl")
    List<InStorage> findAll();

    @Insert("insert into tb_rkjl values(null,#{cid},#{moderId},#{stall},#{rkTime},#{czUser})")
    int insert(InStorage inStorage);
}

package com.newer.mapper;

import com.newer.domain.Stall;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StallMapper {
    @Update("update tb_cw set place=#{id} where cwid=#{cid}")
    int upDate(@Param("id")int place,@Param("cid")int cwId);

    @Select("select * from tb_cw")
    List<Stall> findAll();
}

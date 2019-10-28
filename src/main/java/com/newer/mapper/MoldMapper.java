package com.newer.mapper;

import com.newer.domain.Mold;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MoldMapper {
    @Select("select * from car_mold where moderid=#{id}")
    List<Mold> findById(@Param("id")int moderId);
}

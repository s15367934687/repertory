package com.newer.mapper;

import com.newer.domain.Carxx;
import org.apache.ibatis.annotations.Select;

public interface CarxxMapper {

    @Select("select * from tb_carxx")
    Carxx findAll();

    @Select("select * from tb_carxx where cid={#cid}")
    Carxx findByCid(Integer id);





}

package com.newer.mapper;

import com.newer.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from tb_user where username=#{name} and pwd=#{pwd}")
    User findBy(@Param("name")String userName,@Param("pwd")String pwd);
}

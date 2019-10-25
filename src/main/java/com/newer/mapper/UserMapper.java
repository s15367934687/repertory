package com.newer.mapper;

import com.newer.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("select * from tb_user where username=#{name} and pwd=#{pwd}")
    User findBy(@Param("name")String userName,@Param("pwd")String pwd);

    @Update("update tb_user set pwd=#{pwd} where userid=#{id}")
    int upDate(@Param("pwd")String pwd,@Param("id")int userId);

    @Select("select * from tb_user")
    List<User> findAll();

    @Delete("delete from tb_user where userid=#{id}")
    int delete(@Param("id")int userId);

    @Insert("insert into tb_user values(null,#{userName},#{name},#{pwd},#{type})")
    int insert(User user);
}

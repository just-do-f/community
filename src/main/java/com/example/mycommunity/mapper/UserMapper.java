package com.example.mycommunity.mapper;

import com.example.mycommunity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Create by czl on 2021/6/19 23:01
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,bio) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{bio})")
    void insert(User user);

    @Select("select * from user where token =#{token}")
    User findByToken(@Param("token")String Token);

    @Select("select * from user where id =#{id}")
    User findById(@Param("id")Integer id);
}

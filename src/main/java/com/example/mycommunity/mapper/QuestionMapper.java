package com.example.mycommunity.mapper;

import com.example.mycommunity.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Create by czl on 2021/6/20 15:49
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
    @Select("select count(1) from question")
    Integer count();
}

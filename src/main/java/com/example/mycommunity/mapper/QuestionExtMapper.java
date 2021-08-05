package com.example.mycommunity.mapper;

import com.example.mycommunity.model.Question;

/**
 * Create by czl on 2021/7/9 16:32
 */
public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
}

package com.example.mycommunity.mapper;

import com.example.mycommunity.model.Comment;
import com.example.mycommunity.model.Question;

/**
 * Create by czl on 2021/8/5 17:44
 */
public interface CommentExtMapper {
    int incCommentCount(Comment record);
}

package com.example.mycommunity.controller;

import com.example.mycommunity.dto.CommentDTO;
import com.example.mycommunity.mapper.CommentMapper;
import com.example.mycommunity.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Create by czl on 2021/7/9 16:56
 */
@Controller
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO){
        Comment comment=new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(1);
        comment.setLikeCount(0L);
        commentMapper.insert(comment);
        return null;
    }
}

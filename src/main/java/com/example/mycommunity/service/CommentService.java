package com.example.mycommunity.service;

import com.example.mycommunity.dto.CommentDTO;
import com.example.mycommunity.enums.CommentTypeEnum;
import com.example.mycommunity.exception.CustomizeErrorCode;
import com.example.mycommunity.exception.CustomizeException;
import com.example.mycommunity.mapper.*;
import com.example.mycommunity.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Create by czl on 2021/7/11 10:45
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentExtMapper commentExtMapper;
    @Transactional
    public void insert(Comment comment) {
        if(comment.getParentId()==null||comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType()==null|| !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if(comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbcomment=commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbcomment==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
            //增加评论数
            Comment parentComment=new Comment();
            parentComment.setId(comment.getParentId());
            parentComment.setCommentCount(1);
            commentExtMapper.incCommentCount(parentComment);
        }else {
            //回复问题
            Question question=questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            question.setCommentCount(1);
            commentMapper.insert(comment);
            questionExtMapper.incCommentCount(question);
        }
    }

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
        CommentExample commentExample=new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments=commentMapper.selectByExample(commentExample);
       if(comments.size()==0){
           return new ArrayList<>();
       }
        Set<Long> commentators=comments.stream().map(comment ->comment.getCommentator()).collect(Collectors.toSet());
       List<Long> userIds=new ArrayList<>();
       userIds.addAll(commentators);
       //获取评论人并转换为Map
       UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User>users=userMapper.selectByExample(userExample);
        Map<Long,User> userMap=users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        //转换comment为commentDTO
        List<CommentDTO> commentDTOS=comments.stream().map(comment -> {
            CommentDTO commentDTO=new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }
}

package com.example.mycommunity.dto;

import com.example.mycommunity.model.User;
import lombok.Data;

/**
 * Create by czl on 2021/7/11 20:25
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
}

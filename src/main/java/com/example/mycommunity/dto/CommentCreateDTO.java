package com.example.mycommunity.dto;

import lombok.Data;

/**
 * Create by czl on 2021/7/9 17:00
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}

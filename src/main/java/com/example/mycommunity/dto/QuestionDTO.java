package com.example.mycommunity.dto;

import com.example.mycommunity.model.User;
import lombok.Data;

/**
 * Create by czl on 2021/6/20 16:39
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long mgtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer linkCount;
    private User user;
}

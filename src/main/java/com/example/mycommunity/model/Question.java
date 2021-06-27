package com.example.mycommunity.model;

import lombok.Data;

/**
 * Create by czl on 2021/6/20 15:44
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer linkCount;
    private String avatarUrl;
}

package com.example.mycommunity.model;

import lombok.Data;

/**
 * Create by czl on 2021/06/19
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatarUrl;
}

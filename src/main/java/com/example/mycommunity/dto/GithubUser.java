package com.example.mycommunity.dto;

import lombok.Data;

/**
 * Create by czl on 2021/06/19
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}

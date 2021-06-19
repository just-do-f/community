package com.example.mycommunity.dto;

import lombok.Data;

/**
 * Create by czl on 2021/06/19
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
}

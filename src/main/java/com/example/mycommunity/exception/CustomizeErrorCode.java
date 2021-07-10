package com.example.mycommunity.exception;

/**
 * Create by czl on 2021/7/9 15:10
 */
public enum CustomizeErrorCode implements  ICustomizeErrorCode{
    QUESTION_NOT_FOUND("你找的问题不在了，要不换个试试");

    @Override
    public String getMessage() {
        return message;
    }
    private String message;
    CustomizeErrorCode(String message){
        this.message=message;
    }
}

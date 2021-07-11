package com.example.mycommunity.exception;

/**
 * Create by czl on 2021/7/9 15:00
 */
public class CustomizeException extends RuntimeException{
    private  String message;
    private Integer code;
    public CustomizeException(ICustomizeErrorCode errorCode){
        this.code=errorCode.getCode();
        this.message=errorCode.getMessage();
    }
    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}

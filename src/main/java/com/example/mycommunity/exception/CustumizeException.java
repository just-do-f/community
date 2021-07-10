package com.example.mycommunity.exception;

/**
 * Create by czl on 2021/7/9 15:00
 */
public class CustumizeException extends RuntimeException{
    private  String message;
    public CustumizeException(ICustomizeErrorCode errorCode){
        this.message=errorCode.getMessage();
    }
    public CustumizeException(String message){
        this.message=message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}

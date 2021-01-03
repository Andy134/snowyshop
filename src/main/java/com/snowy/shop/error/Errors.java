package com.snowy.shop.error;

public enum Errors {
    DATA_NOT_FOUND(9001,"Data not found"),
    INTERNAL_ERROR(9999, "Internal errors")
    ;

    public int code;
    public String msg;

    Errors(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

}

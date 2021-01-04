package com.snowy.shop.error;

import org.springframework.http.HttpStatus;

public enum Errors {
    DATA_NOT_FOUND(9001, HttpStatus.BAD_REQUEST, "Data not found"),
    INTERNAL_ERROR(9999, HttpStatus.INTERNAL_SERVER_ERROR, "Internal errors");

    private final int code;

    private final HttpStatus httpStatus;

    private final String message;

    Errors(int code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

}

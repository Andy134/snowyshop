package com.snowy.shop.error;

public class BussinessException extends RuntimeException {
    public BussinessException(Errors e) {
        super(e.msg);
    }
}

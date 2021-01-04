package com.snowy.shop.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    private HttpStatus status;
    private String message;
    private Errors errorCode;

    public BusinessException(HttpStatus status, Errors errorCode) {
        this(errorCode);
        this.status = status;
    }

    public BusinessException(Errors errorCode) {
        this.errorCode = errorCode;
        this.status = errorCode.getHttpStatus();
        this.message = errorCode.getMessage();
    }

    public BusinessException(Errors errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.status = errorCode.getHttpStatus();
        this.message = errorCode.getMessage();
    }

    public BusinessException(HttpStatus status, Errors errorCode, String message) {
        this(status, errorCode);
        this.message = message;
    }

    public BusinessException(Errors errorCode, String message) {
        this(errorCode);
        this.message = message;
    }
}

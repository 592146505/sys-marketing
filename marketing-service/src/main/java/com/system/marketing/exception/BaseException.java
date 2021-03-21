package com.system.marketing.exception;

import lombok.Getter;

/**
 * @author: Liu.B.J
 * @date: 2021/3/21 13:25
 * @description:
 */
public class BaseException extends RuntimeException {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -8088475456529581491L;

    @Getter
    private Integer code;

    @Getter
    private String message;

    public BaseException() {
    }

    /**
     * construct with code & message.
     *
     * @param code    code
     * @param message message
     */
    public BaseException(Integer code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    /**
     * construct with code . message * throwable.
     *
     * @param code      code
     * @param message   message
     * @param throwable throwable
     */
    public BaseException(Integer code, String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
        this.code = code;
    }
}

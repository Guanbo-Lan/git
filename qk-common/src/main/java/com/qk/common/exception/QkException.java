package com.qk.common.exception;

import com.qk.common.enums.Code;

/**
 * @author MrZhao
 * 轻客自定义异常
 */
public class QkException extends RuntimeException {

    /**
     * 业务错误码（可选）
     */
    private final Integer code;

    public QkException(String message) {
        super(message);
        this.code = null;
    }

    public QkException(Code code) {
        super(code.getMsg());
        this.code = code.getCode();
    }

    public Integer getCode() {
        return code;
    }
}

package com.alibaba.core.exception;

/**
 *
 * @author MrZhao
 *
 *  OSS 出现问题
 *
 */
public class OssException extends RuntimeException {
    public OssException(String message) {
        super(message);
    }
}

package com.qk.common.exception;

import com.qk.common.enums.Code;

/**
 * @author MrZhao
 *  异常抛出工具类
 */
public class Throws {

    private Throws() {
    }

    public static void throwE(Code code) {
        throw new QkException(code);
    }

}

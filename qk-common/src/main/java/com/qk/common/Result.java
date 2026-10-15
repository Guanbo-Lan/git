package com.qk.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 后端统一返回前端响应结果
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    private Integer code; //编码：1成功，0为失败
    private String msg; //错误信息
    private Object data; //数据

    private Result(){
    }

    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static Result success(Object object) {
        Result result = new Result();
        result.data = object;
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }

}
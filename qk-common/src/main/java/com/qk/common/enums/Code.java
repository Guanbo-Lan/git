package com.qk.common.enums;

/**
 *
 * @author MrZhao
 * 通用枚举接口
 */
public interface Code {
    /**
     * 获取状态码
     *
     * @return Integer
     */
    Integer getCode();

    /**
     * 状态码描述信息
     *
     * @return String
     */
    String getMsg();

}

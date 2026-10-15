package com.qk.common.enums;

/**
 *
 * @author MrZhao
 *
 */
public enum ExceptionCodeEnum implements Code {

    // 参数非法枚举
    PARAM_INVALID(1001, "参数非法"),
    ROLE_HAS_USER(2001, "角色下关联用户不可删除"),
    DEPT_HAS_USER(2002, "部门下关联用户不可删除"),
    FILE_UPLOAD_ERROR(3001, "文件上传失败"),
    USER_NOT_EXIST(4001, "用户不存在"),
    PASSWORD_USERNAME_INVALID(5001, "用户名或者密码错误"),
    // 状态非法枚举
    STATUS_INVALID(1002, "状态非法");

    final String msg;
    final Integer code;

    ExceptionCodeEnum(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}

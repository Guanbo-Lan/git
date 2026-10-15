package com.qk.entity.dto;

import lombok.Data;

/**
 *
 * @author MrZhao
 * 负责接收客户新增接口请求参数
 */
@Data
public class CustomerSaveDTO {
    private String phone;
    private Integer channel;
    private String name;
    private Integer gender;
    private Integer age;
    private String wechat;
    private String qq;
    private Integer degree;
    private Integer jobStatus;
    private Integer subject;
    private Integer courseId;
}

package com.qk.entity.dto;

import lombok.Data;

@Data
public class CustomerUpdateByIdDTO {
    private Integer id;
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
    private Integer businessId;
}

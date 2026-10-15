package com.qk.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerGetByIdVO {
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}

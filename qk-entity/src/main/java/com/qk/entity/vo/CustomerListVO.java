package com.qk.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *
 * @author MrZhao
 *
 */
@Data
public class CustomerListVO {
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
    /**
     * 课程名称（联表字段）
     */
    private String courseName;
}

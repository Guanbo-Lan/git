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
public class BusinessListVO {
    private Integer id;
    private String name;
    private String phone;
    private Integer gender;
    private Integer age;
    private String wechat;
    private String qq;
    private Integer subject;
    private Integer courseId;
    private Integer degree;
    private Integer jobStatus;
    private Integer channel;
    private String remark;
    private Integer status;
    private Integer userId;
    private Integer clueId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime nextTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    /**
     * 归属人姓名（联表字段）
     */
    private String assignName;
}

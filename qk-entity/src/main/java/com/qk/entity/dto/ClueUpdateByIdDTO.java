package com.qk.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 *
 * @author MrZhao
 * 负责接收线索跟进接口请求参数
 */
@Data
public class ClueUpdateByIdDTO {
    private Integer id;
    private String phone;
    private Integer channel;
    private Integer activityId;
    private String name;
    private Integer gender;
    private Integer age;
    private String wechat;
    private String qq;
    private Integer userId;
    private Integer status;
    private Integer subject;
    private Integer level;
    private LocalDateTime nextTime;
    /**
     * 跟进内容（对应跟进记录表，线索表不存储）
     */
    private String record;
}

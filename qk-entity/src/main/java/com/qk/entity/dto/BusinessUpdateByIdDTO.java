package com.qk.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author MrZhao
 * 负责接收商机跟进接口请求参数
 */
@Data
public class BusinessUpdateByIdDTO {
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
    private LocalDateTime nextTime;
    /**
     * 跟进要点（对应跟进记录表，商机表不存储）
     */
    private List<String> keyItems;
    /**
     * 跟进状态（对应跟进记录表，商机表不存储）
     */
    private Integer trackStatus;
    /**
     * 跟进内容（对应跟进记录表，商机表不存储）
     */
    private String record;
}

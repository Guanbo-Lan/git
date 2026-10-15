package com.qk.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ClueGetByIdVO {
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime nextTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    /**
     * 跟进记录列表（联表字段）
     */
    private List<ClueTrackRecordVO> trackRecords;
}

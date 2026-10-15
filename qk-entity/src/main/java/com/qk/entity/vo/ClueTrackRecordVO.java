package com.qk.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *
 * @author MrZhao
 * 线索跟进记录
 *
 */
@Data
public class ClueTrackRecordVO {
    private Integer id;
    private Integer clueId;
    private Integer userId;
    private Integer subject;
    private Integer level;
    private String record;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime nextTime;
    private Integer type;
    private Integer falseReason;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 跟进人姓名（联表字段）
     */
    private String assignName;
}

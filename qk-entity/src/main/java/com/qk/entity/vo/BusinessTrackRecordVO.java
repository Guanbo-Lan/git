package com.qk.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *
 * @author MrZhao
 * 商机跟进记录
 *
 */
@Data
public class BusinessTrackRecordVO {
    private Integer id;
    private Integer businessId;
    private Integer userId;
    private Integer trackStatus;
    private String keyItems;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime nextTime;
    private String record;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 跟进人姓名（联表字段）
     */
    private String assignName;
}

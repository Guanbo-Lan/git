package com.qk.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 *
 * @author MrZhao
 * 负责接收活动新增接口请求参数
 */
@Data
public class ActivitySaveDTO {
    private Integer channel;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private Integer type;
    private Double discount;
    private Integer voucher;
}

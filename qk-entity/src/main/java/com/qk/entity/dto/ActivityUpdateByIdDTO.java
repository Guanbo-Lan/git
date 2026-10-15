package com.qk.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivityUpdateByIdDTO {
    private Integer id;
    private Integer channel;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private Integer type;
    private Double discount;
    private Integer voucher;
}

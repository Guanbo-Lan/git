package com.qk.entity.dto;

import lombok.Data;

@Data
public class CourseUpdateByIdDTO {
    private Integer id;
    private Integer subject;
    private String name;
    private Integer price;
    private Integer target;
    private String description;
}

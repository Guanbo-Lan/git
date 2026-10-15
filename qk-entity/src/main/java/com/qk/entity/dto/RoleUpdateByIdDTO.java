package com.qk.entity.dto;

import lombok.Data;

@Data
public class RoleUpdateByIdDTO {
    private Integer id;
    private String name;
    private String label;
    private String remark;
}

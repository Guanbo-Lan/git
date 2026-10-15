package com.qk.entity.dto;

import lombok.Data;

/**
 *
 * @author MrZhao
 *
 */
@Data
public class BusinessPoolListDTO extends PageParam {
    private Integer businessId;
    private String name;
    private String phone;
    private Integer subject;
}

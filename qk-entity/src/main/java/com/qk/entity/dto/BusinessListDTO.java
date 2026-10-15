package com.qk.entity.dto;

import lombok.Data;

/**
 *
 * @author MrZhao
 *
 */
@Data
public class BusinessListDTO extends PageParam {
    private Integer businessId;
    private String name;
    private String phone;
    private Integer status;
    private String assignName;
}

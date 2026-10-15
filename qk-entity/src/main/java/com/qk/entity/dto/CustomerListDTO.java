package com.qk.entity.dto;

import lombok.Data;

/**
 *
 * @author MrZhao
 *
 */
@Data
public class CustomerListDTO extends PageParam {
    private String phone;
    private String name;
    private Integer channel;
    private Integer subject;
}

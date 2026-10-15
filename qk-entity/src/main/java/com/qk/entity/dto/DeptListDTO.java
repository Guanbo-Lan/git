package com.qk.entity.dto;

import lombok.Data;

/**
 *
 * @author MrZhao
 *
 */
@Data
public class DeptListDTO extends PageParam {
    private String name;
    private Integer status;
}

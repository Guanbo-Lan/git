package com.qk.entity.dto;

import lombok.Data;

/**
 *
 * @author MrZhao
 *
 */
@Data
public class RoleListDTO extends PageParam {
    private String name;
    private String label;
}

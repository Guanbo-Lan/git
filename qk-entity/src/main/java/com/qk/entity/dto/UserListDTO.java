package com.qk.entity.dto;

import lombok.Data;

/**
 *
 * @author MrZhao
 *
 */
@Data
public class UserListDTO extends PageParam {
    private String name;
    private Integer status;
    private String phone;
    private Integer deptId;
}

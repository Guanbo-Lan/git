package com.qk.entity.dto;

import lombok.Data;

/**
 *
 * @author MrZhao
 * 负责接收用户新增接口请求参数
 */
@Data
public class UserSaveDTO {
    private String username;
    private String name;
    private String phone;
    private String email;
    private Integer gender;
    private Integer status;
    private Integer deptId;
    private Integer roleId;
    private String image;
    private String remark;
}

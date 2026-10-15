package com.qk.entity.dto;

import lombok.Data;

@Data
public class UserUpdateByIdDTO {
    private Integer id;
    private String username;
    private String password;
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

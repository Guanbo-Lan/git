package com.qk.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *
 * @author MrZhao
 *
 */
@Data
public class UserListVO {
    private Integer id;
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
    private String deptName;
    private String roleName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}

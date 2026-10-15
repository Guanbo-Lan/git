package com.qk.entity.vo;

import lombok.Data;

/**
 *
 * @author MrZhao
 * 登录成功后的返回数据
 */
@Data
public class LoginVO {
    private Integer id;
    private String username;
    private String name;
    private String image;
    private String roleLabel;
    private String token;
}

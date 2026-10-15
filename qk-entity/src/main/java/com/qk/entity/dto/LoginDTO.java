package com.qk.entity.dto;

import lombok.Data;

/**
 *
 * @author MrZhao
 * 负责接收登录接口请求参数
 */
@Data
public class LoginDTO {
    private String username;
    private String password;
}

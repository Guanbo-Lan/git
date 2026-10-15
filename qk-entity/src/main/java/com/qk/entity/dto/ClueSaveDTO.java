package com.qk.entity.dto;

import lombok.Data;

/**
 *
 * @author MrZhao
 * 负责接收线索新增接口请求参数
 */
@Data
public class ClueSaveDTO {
    private String phone;
    private Integer channel;
    private Integer activityId;
    private String name;
    private Integer gender;
    private Integer age;
    private String wechat;
    private String qq;
}

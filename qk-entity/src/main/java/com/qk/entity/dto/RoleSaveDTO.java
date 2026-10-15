package com.qk.entity.dto;

import lombok.Data;

/**
 *
 * @author MrZhao
 * 负责接收角色新增接口请求参数
 */
@Data
public class RoleSaveDTO {
    private String name;
    private String label;
    private String remark;
}

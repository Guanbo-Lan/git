package com.qk.entity.dto;

import lombok.Data;

/**
 *
 * @author MrZhao
 * 负责接收部门新增接口请求参数
 */
@Data
public class DeptSaveDTO {
    private String name;
    private Integer status;
}

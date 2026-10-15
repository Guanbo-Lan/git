package com.qk.entity.dto;

import lombok.Data;

/**
 *
 * @author MrZhao
 * 负责接收伪线索处理接口请求参数
 */
@Data
public class ClueFalseDTO {
    private Integer reason;
    private String remark;
}

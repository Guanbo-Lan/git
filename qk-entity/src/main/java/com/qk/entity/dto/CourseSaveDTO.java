package com.qk.entity.dto;

import lombok.Data;

/**
 *
 * @author MrZhao
 * 负责接收课程新增接口请求参数
 */
@Data
public class CourseSaveDTO {
    private Integer subject;
    private String name;
    private Integer price;
    private Integer target;
    private String description;
}

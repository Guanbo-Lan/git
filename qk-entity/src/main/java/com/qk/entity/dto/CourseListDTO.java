package com.qk.entity.dto;

import lombok.Data;

/**
 *
 * @author MrZhao
 *
 */
@Data
public class CourseListDTO extends PageParam {
    private String name;
    private Integer subject;
    private Integer target;
}

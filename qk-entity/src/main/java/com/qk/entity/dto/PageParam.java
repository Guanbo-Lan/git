package com.qk.entity.dto;

import lombok.Data;

/**
 *
 * @author MrZhao
 *  分页参数封装对象
 *  page 当前页码值
 *  pageSize 每页条数
 *
 */
@Data
public class PageParam {
    private Long page = 1L;
    private Long pageSize = 10L;
}

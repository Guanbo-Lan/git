package com.qk.entity.vo;

import lombok.Data;

import java.util.List;

/**
 *
 * @author MrZhao
 * <p>
 * 分页响应数据统一格式
 */
@Data
public class PageVO<E> {
    // 总记录数
    private Long total;
    // 每页数据
    private List<E> rows;

}

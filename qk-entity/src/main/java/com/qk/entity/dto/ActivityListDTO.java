package com.qk.entity.dto;

import lombok.Data;

/**
 *
 * @author MrZhao
 *
 */
@Data
public class ActivityListDTO extends PageParam {
    private Integer channel;
    private Integer type;
}

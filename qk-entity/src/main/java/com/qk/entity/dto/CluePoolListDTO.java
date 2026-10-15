package com.qk.entity.dto;

import lombok.Data;

/**
 *
 * @author MrZhao
 *
 */
@Data
public class CluePoolListDTO extends PageParam {
    private Integer clueId;
    private String phone;
    private Integer channel;
}

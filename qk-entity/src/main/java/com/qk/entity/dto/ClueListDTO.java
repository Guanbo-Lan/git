package com.qk.entity.dto;

import lombok.Data;

/**
 *
 * @author MrZhao
 *
 */
@Data
public class ClueListDTO extends PageParam {
    private Integer clueId;
    private String phone;
    private Integer status;
    private Integer channel;
    private String assignName;
}

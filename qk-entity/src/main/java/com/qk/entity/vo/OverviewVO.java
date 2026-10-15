package com.qk.entity.vo;

import lombok.Data;

/**
 *
 * @author MrZhao
 * 首页线索、商机概览数据
 */
@Data
public class OverviewVO {
    // 线索概览
    private Long clueTotal;
    private Long clueWaitAllot;
    private Long clueWaitFollow;
    private Long clueFollowing;
    private Long clueFalse;
    private Long clueConvertBusiness;
    // 商机概览
    private Long businessTotal;
    private Long businessWaitAllot;
    private Long businessWaitFollow;
    private Long businessFollowing;
    private Long businessFalse;
    private Long businessConvertCustomer;
}

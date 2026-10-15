package com.qk.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 活动信息表
 */
@Data
@TableName(value ="activity")
public class Activity {
    /**
     * 活动id，主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 活动渠道
     */
    private Integer channel;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 活动类型
     */
    private Integer type;

    /**
     * 折扣
     */
    private Double discount;

    /**
     * 代金券
     */
    private Integer voucher;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}

package com.qk.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 线索信息表
 */
@Data
@TableName(value ="clue")
public class Clue {
    /**
     * 线索id，主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 渠道
     */
    private Integer channel;

    /**
     * 活动id
     */
    private Integer activityId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别：0-未知，1-男，2-女
     */
    private Integer gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * QQ号
     */
    private String qq;

    /**
     * 归属用户id
     */
    private Integer userId;

    /**
     * 状态：1-待分配，2-跟进中，3-线索池，4-伪线索，5-已转商机
     */
    private Integer status;

    /**
     * 学科
     */
    private Integer subject;

    /**
     * 意向级别
     */
    private Integer level;

    /**
     * 下次跟进时间
     */
    private LocalDateTime nextTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}

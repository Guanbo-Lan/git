package com.qk.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商机信息表
 */
@Data
@TableName(value ="business")
public class Business {
    /**
     * 商机id，主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

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
     * 学科
     */
    private Integer subject;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 学历
     */
    private Integer degree;

    /**
     * 求职状态
     */
    private Integer jobStatus;

    /**
     * 渠道
     */
    private Integer channel;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态：1-待分配，2-跟进中，4-公海池，5-已转客户
     */
    private Integer status;

    /**
     * 归属用户id
     */
    private Integer userId;

    /**
     * 线索id
     */
    private Integer clueId;

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

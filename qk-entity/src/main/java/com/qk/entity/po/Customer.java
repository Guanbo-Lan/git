package com.qk.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 客户信息表
 */
@Data
@TableName(value ="customer")
public class Customer {
    /**
     * 客户id，主键
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
     * 学历
     */
    private Integer degree;

    /**
     * 求职状态
     */
    private Integer jobStatus;

    /**
     * 学科
     */
    private Integer subject;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 商机id
     */
    private Integer businessId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}

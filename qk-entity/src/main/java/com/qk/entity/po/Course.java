package com.qk.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 课程信息表
 */
@Data
@TableName(value ="course")
public class Course {
    /**
     * 课程id，主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学科
     */
    private Integer subject;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 课程价格
     */
    private Integer price;

    /**
     * 课程目标
     */
    private Integer target;

    /**
     * 课程描述
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}

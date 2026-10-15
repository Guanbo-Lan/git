package com.qk.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 操作日志信息表
 */
@Data
@TableName(value ="operate_log")
public class OperateLog {
    /**
     * 日志id，主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 操作人用户id
     */
    private Integer operateUserId;

    /**
     * 操作时间
     */
    private LocalDateTime operateTime;

    /**
     * 操作的类名
     */
    private String className;

    /**
     * 操作的方法名
     */
    private String methodName;

    /**
     * 方法的入参
     */
    private String methodParams;

    /**
     * 方法的返回值
     */
    private String returnValue;

    /**
     * 耗时(毫秒)
     */
    private Long costTime;

    /**
     * 操作人姓名
     */
    private String operateUserName;
}

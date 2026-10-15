-- =====================================================================
-- 轻客管家 数据库建表脚本
-- 数据库: qk  (见 qk-management/src/main/resources/application.yaml)
-- 说明: MyBatis-Plus 默认开启 map-underscore-to-camel-case,
--       实体字段 createTime 映射列 create_time, deptId 映射列 dept_id, 以此类推。
-- =====================================================================

-- ----------------------------
-- 1. 部门表 (参考, 已有实体 Dept)
-- ----------------------------
CREATE TABLE IF NOT EXISTS `dept` (
    `id`          INT          NOT NULL AUTO_INCREMENT COMMENT '部门id，主键',
    `name`        VARCHAR(50)  NOT NULL COMMENT '部门名称',
    `status`      TINYINT      DEFAULT 1 COMMENT '状态：0-停用，1-正常',
    `create_time` DATETIME     DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '部门信息表';

-- ----------------------------
-- 2. 课程表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `course` (
    `id`          INT          NOT NULL AUTO_INCREMENT COMMENT '课程id，主键',
    `subject`     INT          DEFAULT NULL COMMENT '学科',
    `name`        VARCHAR(100) NOT NULL COMMENT '课程名称',
    `price`       INT          DEFAULT NULL COMMENT '课程价格',
    `target`      INT          DEFAULT NULL COMMENT '课程目标',
    `description` VARCHAR(500) DEFAULT NULL COMMENT '课程描述',
    `create_time` DATETIME     DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '课程信息表';

-- ----------------------------
-- 3. 角色表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `role` (
    `id`          INT          NOT NULL AUTO_INCREMENT COMMENT '角色id，主键',
    `name`        VARCHAR(50)  NOT NULL COMMENT '角色名称',
    `label`       VARCHAR(50)  DEFAULT NULL COMMENT '角色标识',
    `remark`      VARCHAR(255) DEFAULT NULL COMMENT '角色备注',
    `create_time` DATETIME     DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '角色信息表';

-- ----------------------------
-- 4. 用户表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user` (
    `id`          INT          NOT NULL AUTO_INCREMENT COMMENT '用户id，主键',
    `username`    VARCHAR(50)  NOT NULL COMMENT '用户名',
    `password`    VARCHAR(100) DEFAULT NULL COMMENT '密码',
    `name`        VARCHAR(50)  DEFAULT NULL COMMENT '姓名',
    `phone`       VARCHAR(20)  DEFAULT NULL COMMENT '手机号',
    `email`       VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
    `gender`      TINYINT      DEFAULT NULL COMMENT '性别：0-未知，1-男，2-女',
    `status`      TINYINT      DEFAULT 1 COMMENT '状态：0-停用，1-正常',
    `dept_id`     INT          DEFAULT NULL COMMENT '部门id',
    `role_id`     INT          DEFAULT NULL COMMENT '角色id',
    `image`       VARCHAR(500) DEFAULT NULL COMMENT '头像',
    `remark`      VARCHAR(255) DEFAULT NULL COMMENT '备注',
    `create_time` DATETIME     DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '用户信息表';

-- ----------------------------
-- 5. 活动表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `activity` (
    `id`          INT          NOT NULL AUTO_INCREMENT COMMENT '活动id，主键',
    `channel`     INT          DEFAULT NULL COMMENT '活动渠道',
    `name`        VARCHAR(100) NOT NULL COMMENT '活动名称',
    `start_time`  DATETIME     DEFAULT NULL COMMENT '开始时间',
    `end_time`    DATETIME     DEFAULT NULL COMMENT '结束时间',
    `description` VARCHAR(500) DEFAULT NULL COMMENT '活动描述',
    `type`        INT          DEFAULT NULL COMMENT '活动类型',
    `discount`    DOUBLE       DEFAULT NULL COMMENT '折扣',
    `voucher`     INT          DEFAULT NULL COMMENT '代金券',
    `create_time` DATETIME     DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '活动信息表';

-- ----------------------------
-- 6. 线索表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `clue` (
    `id`          INT          NOT NULL AUTO_INCREMENT COMMENT '线索id，主键',
    `phone`       VARCHAR(20)  DEFAULT NULL COMMENT '手机号',
    `channel`     INT          DEFAULT NULL COMMENT '渠道',
    `activity_id` INT          DEFAULT NULL COMMENT '活动id',
    `name`        VARCHAR(50)  DEFAULT NULL COMMENT '姓名',
    `gender`      TINYINT      DEFAULT NULL COMMENT '性别：0-未知，1-男，2-女',
    `age`         INT          DEFAULT NULL COMMENT '年龄',
    `wechat`      VARCHAR(50)  DEFAULT NULL COMMENT '微信号',
    `qq`          VARCHAR(20)  DEFAULT NULL COMMENT 'QQ号',
    `user_id`     INT          DEFAULT NULL COMMENT '归属用户id',
    `status`      TINYINT      DEFAULT 1 COMMENT '状态：1-待分配，2-跟进中，3-线索池，4-伪线索，5-已转商机',
    `subject`     INT          DEFAULT NULL COMMENT '学科',
    `level`       INT          DEFAULT NULL COMMENT '意向级别',
    `next_time`   DATETIME     DEFAULT NULL COMMENT '下次跟进时间',
    `create_time` DATETIME     DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '线索信息表';

-- ----------------------------
-- 7. 线索跟进记录表 (对应 ClueGetByIdVO.trackRecords)
-- ----------------------------
CREATE TABLE IF NOT EXISTS `clue_track_record` (
    `id`           INT          NOT NULL AUTO_INCREMENT COMMENT '跟进记录id，主键',
    `clue_id`      INT          DEFAULT NULL COMMENT '线索id',
    `user_id`      INT          DEFAULT NULL COMMENT '跟进人用户id',
    `subject`      INT          DEFAULT NULL COMMENT '学科',
    `level`        INT          DEFAULT NULL COMMENT '意向级别',
    `record`       VARCHAR(1000) DEFAULT NULL COMMENT '跟进内容',
    `next_time`    DATETIME     DEFAULT NULL COMMENT '下次跟进时间',
    `type`         INT          DEFAULT NULL COMMENT '跟进类型',
    `false_reason` INT          DEFAULT NULL COMMENT '伪线索原因',
    `create_time`  DATETIME     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '线索跟进记录表';

-- ----------------------------
-- 8. 商机表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `business` (
    `id`          INT          NOT NULL AUTO_INCREMENT COMMENT '商机id，主键',
    `name`        VARCHAR(50)  DEFAULT NULL COMMENT '姓名',
    `phone`       VARCHAR(20)  DEFAULT NULL COMMENT '手机号',
    `gender`      TINYINT      DEFAULT NULL COMMENT '性别：0-未知，1-男，2-女',
    `age`         INT          DEFAULT NULL COMMENT '年龄',
    `wechat`      VARCHAR(50)  DEFAULT NULL COMMENT '微信号',
    `qq`          VARCHAR(20)  DEFAULT NULL COMMENT 'QQ号',
    `subject`     INT          DEFAULT NULL COMMENT '学科',
    `course_id`   INT          DEFAULT NULL COMMENT '课程id',
    `degree`      INT          DEFAULT NULL COMMENT '学历',
    `job_status`  INT          DEFAULT NULL COMMENT '求职状态',
    `channel`     INT          DEFAULT NULL COMMENT '渠道',
    `remark`      VARCHAR(500) DEFAULT NULL COMMENT '备注',
    `status`      TINYINT      DEFAULT 1 COMMENT '状态：1-待分配，2-跟进中，4-公海池，5-已转客户',
    `user_id`     INT          DEFAULT NULL COMMENT '归属用户id',
    `clue_id`     INT          DEFAULT NULL COMMENT '线索id',
    `next_time`   DATETIME     DEFAULT NULL COMMENT '下次跟进时间',
    `create_time` DATETIME     DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '商机信息表';

-- ----------------------------
-- 9. 商机跟进记录表 (对应 BusinessGetByIdVO.trackRecords)
-- ----------------------------
CREATE TABLE IF NOT EXISTS `business_track_record` (
    `id`           INT          NOT NULL AUTO_INCREMENT COMMENT '跟进记录id，主键',
    `business_id`  INT          DEFAULT NULL COMMENT '商机id',
    `user_id`      INT          DEFAULT NULL COMMENT '跟进人用户id',
    `track_status` INT          DEFAULT NULL COMMENT '跟进状态',
    `key_items`    VARCHAR(500) DEFAULT NULL COMMENT '跟进要点(JSON数组字符串)',
    `next_time`    DATETIME     DEFAULT NULL COMMENT '下次跟进时间',
    `record`       VARCHAR(1000) DEFAULT NULL COMMENT '跟进内容',
    `create_time`  DATETIME     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '商机跟进记录表';

-- ----------------------------
-- 10. 操作日志表 (对应实体 OperateLog)
-- ----------------------------
CREATE TABLE IF NOT EXISTS `operate_log` (
    `id`                INT           NOT NULL AUTO_INCREMENT COMMENT '日志id，主键',
    `operate_user_id`   INT           DEFAULT NULL COMMENT '操作人用户id',
    `operate_time`      DATETIME      DEFAULT NULL COMMENT '操作时间',
    `class_name`        VARCHAR(255)  DEFAULT NULL COMMENT '操作的类名',
    `method_name`       VARCHAR(100)  DEFAULT NULL COMMENT '操作的方法名',
    `method_params`     VARCHAR(1000) DEFAULT NULL COMMENT '方法的入参',
    `return_value`      VARCHAR(1000) DEFAULT NULL COMMENT '方法的返回值',
    `cost_time`         BIGINT        DEFAULT NULL COMMENT '耗时(毫秒)',
    `operate_user_name` VARCHAR(50)   DEFAULT NULL COMMENT '操作人姓名',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '操作日志信息表';

-- ----------------------------
-- 11. 客户表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `customer` (
    `id`          INT          NOT NULL AUTO_INCREMENT COMMENT '客户id，主键',
    `phone`       VARCHAR(20)  DEFAULT NULL COMMENT '手机号',
    `channel`     INT          DEFAULT NULL COMMENT '渠道',
    `name`        VARCHAR(50)  DEFAULT NULL COMMENT '姓名',
    `gender`      TINYINT      DEFAULT NULL COMMENT '性别：0-未知，1-男，2-女',
    `age`         INT          DEFAULT NULL COMMENT '年龄',
    `wechat`      VARCHAR(50)  DEFAULT NULL COMMENT '微信号',
    `qq`          VARCHAR(20)  DEFAULT NULL COMMENT 'QQ号',
    `degree`      INT          DEFAULT NULL COMMENT '学历',
    `job_status`  INT          DEFAULT NULL COMMENT '求职状态',
    `subject`     INT          DEFAULT NULL COMMENT '学科',
    `course_id`   INT          DEFAULT NULL COMMENT '课程id',
    `business_id` INT          DEFAULT NULL COMMENT '商机id',
    `create_time` DATETIME     DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '客户信息表';

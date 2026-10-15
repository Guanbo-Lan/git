package com.qk.management.mapper;

import com.qk.entity.po.OperateLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author MrZhao
 *  日志管理的Mapper接口
*/
@Mapper
public interface OperateLogMapper extends BaseMapper<OperateLog> {
}

package com.qk.management.mapper;

import com.qk.entity.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author MrZhao
 *  用户管理的Mapper接口
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

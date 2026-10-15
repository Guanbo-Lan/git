package com.qk.management.mapper;

import com.qk.entity.po.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author MrZhao
 *  角色管理的Mapper接口
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}

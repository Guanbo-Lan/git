package com.qk.management.mapper;

import com.qk.entity.po.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author MrZhao
 *  客户管理的Mapper接口
*/
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
}

package com.qk.management.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qk.entity.dto.DeptListDTO;
import com.qk.entity.po.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.entity.vo.DeptListVO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author MrZhao
 *  部门管理的Mapper接口
*/
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {
}





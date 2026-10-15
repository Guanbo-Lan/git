package com.qk.management.mapper;

import com.qk.entity.po.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author MrZhao
 *  课程管理的Mapper接口
*/
@Mapper
public interface CourseMapper extends BaseMapper<Course> {
}

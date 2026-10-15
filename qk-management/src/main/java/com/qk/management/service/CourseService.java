package com.qk.management.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.qk.entity.dto.CourseListDTO;
import com.qk.entity.dto.CourseSaveDTO;
import com.qk.entity.dto.CourseUpdateByIdDTO;
import com.qk.entity.po.Course;
import com.qk.entity.vo.CourseGetByIdVO;
import com.qk.entity.vo.CourseListVO;
import com.qk.entity.vo.PageVO;

import java.util.List;


/**
 * @author MrZhao
 * 课程管理的业务层接口
 */
public interface CourseService extends IService<Course> {
    /**
     *  根据课程id删除课程
     * @param id 课程id
     */
    void delById(Integer id);
    /**
     *  分页条件查询
     * @param dto 分页条件
     * @return PageVO
     */
    PageVO<CourseListVO> getByPage(CourseListDTO dto);
    /**
     * 添加课程
     *
     * @param dto 课程信息
     */
    void add(CourseSaveDTO dto);

    /**
     * 根据课程id查询课程
     *
     * @param id 课程id
     * @return CourseGetByIdVO
     */
    CourseGetByIdVO getCourseById(Integer id);

    /**
     * 根据课程id修改课程
     *
     * @param dto 课程信息
     */
    void updateCourseById(CourseUpdateByIdDTO dto);

    /**
     * 根据学科查询课程
     *
     * @param subject 学科
     * @return List<Course>
     */
    List<Course> getBySubject(Integer subject);
}

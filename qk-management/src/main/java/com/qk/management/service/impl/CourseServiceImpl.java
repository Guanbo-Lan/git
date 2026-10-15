package com.qk.management.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.qk.common.exception.Throws;
import com.qk.entity.dto.CourseListDTO;
import com.qk.entity.dto.CourseSaveDTO;
import com.qk.entity.dto.CourseUpdateByIdDTO;
import com.qk.entity.po.Course;
import com.qk.entity.vo.CourseGetByIdVO;
import com.qk.entity.vo.CourseListVO;
import com.qk.entity.vo.PageVO;
import com.qk.management.service.CourseService;
import com.qk.management.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.qk.common.enums.ExceptionCodeEnum.PARAM_INVALID;

/**
 * @author MrZhao
 * 课程管理业务层实现类
 */
@Service
@SuppressWarnings("all")
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
        implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public void delById(Integer id) {
        // 业务分析能力
        Course course = this.getById(id);
        // 如果课程不存在则提示参数非法
        if (ObjectUtil.isNull(course)){
            Throws.throwE(PARAM_INVALID);
        }
        courseMapper.deleteById(id);
    }

    @Override
    public PageVO<CourseListVO> getByPage(CourseListDTO dto) {
        // 1. 参数校验（省略）
        // 2. 核心业务处理 // 利用我们当前业务层继承 IService 接口中的方法，简化了对DAO操作！
        Page<Course> pageResult = this.page(PageDTO.of(dto.getPage(), dto.getPageSize()),
                Wrappers.lambdaQuery(Course.class)
                        .like(StrUtil.isNotBlank(dto.getName()), Course::getName, dto.getName())
                        .eq(ObjectUtil.isNotNull(dto.getSubject()), Course::getSubject, dto.getSubject())
                        .eq(ObjectUtil.isNotNull(dto.getTarget()), Course::getTarget, dto.getTarget())
                        .orderByDesc(Course::getUpdateTime));

        // 3. 数据处理和拼装
        // List<Course> ---》List<CourseListVO>
        List<CourseListVO> voList = pageResult.getRecords().stream().map((course) -> {
            // bean 拷贝
            CourseListVO vo = new CourseListVO();
            BeanUtil.copyProperties(course, vo);
            return vo;
        }).toList();
        PageVO<CourseListVO> pageVO = new PageVO<>();
        pageVO.setRows(voList);
        pageVO.setTotal(pageResult.getTotal());
        return pageVO;
    }

    @Override
    public void add(CourseSaveDTO dto) {
        // 1. 根据课程名称查询数据库，存在课程则不保存 数据幂等校验
        String courseName = dto.getName();
        Course courseDB = courseMapper.selectOne(Wrappers.lambdaQuery(Course.class).eq(Course::getName, courseName));
        if (Objects.nonNull(courseDB)) {
            throw new RuntimeException("添加课程已经存在请勿重复添加");
        }
        // 赋值dto数据到course
        Course course = new Course();
        course.setSubject(dto.getSubject());
        course.setName(dto.getName());
        course.setPrice(dto.getPrice());
        course.setTarget(dto.getTarget());
        course.setDescription(dto.getDescription());
        // 数据补全
        course.setCreateTime(LocalDateTime.now());
        course.setUpdateTime(LocalDateTime.now());
        // 2. 保存课程
        courseMapper.insert(course);
    }

    @Override
    public CourseGetByIdVO getCourseById(Integer id) {
        Course course = this.getById(id);
        if (ObjectUtil.isNull(course)){
            Throws.throwE(PARAM_INVALID);
        }
        CourseGetByIdVO vo = new CourseGetByIdVO();
        BeanUtil.copyProperties(course,vo);
        return vo;
    }

    @Override
    public void updateCourseById(CourseUpdateByIdDTO dto) {
        // 参数校验
        if (ObjectUtil.isNull(dto.getId()) ||
                ObjectUtil.isNull(dto.getSubject()) ||
                StrUtil.isBlank(dto.getName())) {
            Throws.throwE(PARAM_INVALID);
        }
        // 数据拷贝
        Course course = BeanUtil.copyProperties(dto, Course.class);
        // 补全更新时间
        course.setUpdateTime(LocalDateTime.now());
        // 更新
        this.saveOrUpdate(course);
    }

    @Override
    public List<Course> getBySubject(Integer subject) {
        return this.list(Wrappers.lambdaQuery(Course.class).eq(Course::getSubject, subject));
    }
}

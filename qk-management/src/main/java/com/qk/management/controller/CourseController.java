package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.entity.dto.CourseListDTO;
import com.qk.entity.dto.CourseSaveDTO;
import com.qk.entity.dto.CourseUpdateByIdDTO;
import com.qk.entity.po.Course;
import com.qk.entity.vo.CourseGetByIdVO;
import com.qk.entity.vo.CourseListVO;
import com.qk.entity.vo.PageVO;
import com.qk.management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author MrZhao
 * 课程管理Controller
 *
 */
@RestController
@SuppressWarnings("all")
@RequestMapping(path = "/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    //@PathVariable 将路径中id占位符代表值 赋值  给方法参数名字id中！
    @DeleteMapping(path = "/{id}")
    public Result delById(@PathVariable Integer id){
        courseService.delById(id);
        return Result.success();
    }

    @GetMapping
    public Result list(CourseListDTO dto){
        PageVO<CourseListVO> pageVO = courseService.getByPage(dto);
        return Result.success(pageVO);
    }

    @GetMapping(path = "/{id}")
    public Result getById(@PathVariable Integer id){
        CourseGetByIdVO vo = courseService.getCourseById(id);
        return Result.success(vo);
    }

    @GetMapping(path = "/list")
    public Result list(){
        List<Course> list = courseService.list();
        return Result.success(list);
    }

    @GetMapping(path = "/subject/{subject}")
    public Result getBySubject(@PathVariable Integer subject){
        List<Course> list = courseService.getBySubject(subject);
        return Result.success(list);
    }

    @PostMapping
    public Result save(@RequestBody CourseSaveDTO dto){
        courseService.add(dto);
        return Result.success();
    }

    @PutMapping
    public Result updateById(@RequestBody CourseUpdateByIdDTO dto){
        courseService.updateCourseById(dto);
        return Result.success();
    }
}

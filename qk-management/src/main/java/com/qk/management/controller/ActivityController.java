package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.entity.dto.ActivityListDTO;
import com.qk.entity.dto.ActivitySaveDTO;
import com.qk.entity.dto.ActivityUpdateByIdDTO;
import com.qk.entity.po.Activity;
import com.qk.entity.vo.ActivityGetByIdVO;
import com.qk.entity.vo.ActivityListVO;
import com.qk.entity.vo.PageVO;
import com.qk.management.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author MrZhao
 * 活动管理Controller
 *
 */
@RestController
@SuppressWarnings("all")
@RequestMapping(path = "/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    //@PathVariable 将路径中id占位符代表值 赋值  给方法参数名字id中！
    @DeleteMapping(path = "/{id}")
    public Result delById(@PathVariable Integer id){
        activityService.delById(id);
        return Result.success();
    }


    @GetMapping
    public Result list(ActivityListDTO dto){
       PageVO<ActivityListVO> pageVO = activityService.getByPage(dto);
        return Result.success(pageVO);
    }
    @GetMapping(path = "/{id}")
    public Result getById(@PathVariable Integer id){
        ActivityGetByIdVO vo = activityService.getActivityById(id);
        return Result.success(vo);
    }
    @GetMapping(path = "/type/{type}")
    public Result getByType(@PathVariable Integer type){
        List<Activity> list = activityService.getByType(type);
        return Result.success(list);
    }



    @PostMapping
    public Result save(@RequestBody ActivitySaveDTO dto){
        activityService.add(dto);
        return Result.success();
    }
    @PutMapping
    public Result updateById(@RequestBody ActivityUpdateByIdDTO dto){
        activityService.updateActivityById(dto);
        return Result.success();
    }
}

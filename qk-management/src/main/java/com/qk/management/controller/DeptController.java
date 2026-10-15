package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.common.aop.anno.CostTime;
import com.qk.entity.dto.DeptListDTO;
import com.qk.entity.dto.DeptSaveDTO;
import com.qk.entity.dto.DeptUpdateByIdDTO;
import com.qk.entity.po.Dept;
import com.qk.entity.vo.DeptGetByIdVO;
import com.qk.entity.vo.DeptListVO;
import com.qk.entity.vo.PageVO;
import com.qk.management.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author MrZhao
 * 部门管理Controller
 *
 */
@RestController
@SuppressWarnings("all")
@RequestMapping(path = "/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    //@PathVariable 将路径中id占位符代表值 赋值  给方法参数名字id中！
    @CostTime
    @DeleteMapping(path = "/{id}")
    public Result delById(@PathVariable Integer id){
        deptService.delById(id);
        return Result.success();
    }

    @CostTime
    @GetMapping
    public Result list(DeptListDTO dto){
       PageVO<DeptListVO> pageVO = deptService.getByPage(dto);
        return Result.success(pageVO);
    }
    @CostTime
    @GetMapping(path = "/{id}")
    public Result getById(@PathVariable Integer id){
        DeptGetByIdVO vo =deptService.getDeptById(id);
        return Result.success(vo);
    }
    @CostTime
    @GetMapping(path = "/list")
    public Result list(){
        List<Dept> list = deptService.list();
        return Result.success(list);
    }


    @CostTime
    @PostMapping
    public Result save(@RequestBody DeptSaveDTO dto){
        deptService.add(dto);
        return Result.success();
    }
    @CostTime
    @PutMapping
    public Result updateById(@RequestBody DeptUpdateByIdDTO dto){
        deptService.updateDeptById(dto);
        return Result.success();
    }
}

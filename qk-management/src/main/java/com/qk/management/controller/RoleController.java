package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.entity.dto.RoleListDTO;
import com.qk.entity.dto.RoleSaveDTO;
import com.qk.entity.dto.RoleUpdateByIdDTO;
import com.qk.entity.po.Role;
import com.qk.entity.vo.RoleGetByIdVO;
import com.qk.entity.vo.RoleListVO;
import com.qk.entity.vo.PageVO;
import com.qk.management.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author MrZhao
 * 角色管理Controller
 *
 */
@RestController
@SuppressWarnings("all")
@RequestMapping(path = "/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //@PathVariable 将路径中id占位符代表值 赋值  给方法参数名字id中！
    @DeleteMapping(path = "/{id}")
    public Result delById(@PathVariable Integer id){
        roleService.delById(id);
        return Result.success();
    }


    @GetMapping
    public Result list(RoleListDTO dto){
       PageVO<RoleListVO> pageVO = roleService.getByPage(dto);
        return Result.success(pageVO);
    }
    @GetMapping(path = "/{id}")
    public Result getById(@PathVariable Integer id){
        RoleGetByIdVO vo = roleService.getRoleById(id);
        return Result.success(vo);
    }
    @GetMapping(path = "/list")
    public Result list(){
        List<Role> list = roleService.list();
        return Result.success(list);
    }



    @PostMapping
    public Result save(@RequestBody RoleSaveDTO dto){
        roleService.add(dto);
        return Result.success();
    }
    @PutMapping
    public Result updateById(@RequestBody RoleUpdateByIdDTO dto){
        roleService.updateRoleById(dto);
        return Result.success();
    }
}

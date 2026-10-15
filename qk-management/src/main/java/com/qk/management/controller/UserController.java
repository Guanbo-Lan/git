package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.entity.dto.UserListDTO;
import com.qk.entity.dto.UserSaveDTO;
import com.qk.entity.dto.UserUpdateByIdDTO;
import com.qk.entity.po.User;
import com.qk.entity.vo.UserGetByIdVO;
import com.qk.entity.vo.UserListVO;
import com.qk.entity.vo.PageVO;
import com.qk.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author MrZhao
 * 用户管理Controller
 *
 */
@RestController
@SuppressWarnings("all")
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    //@PathVariable 将路径中ids占位符代表值 赋值  给方法参数名字ids中！支持单个或多个逗号分隔的id
    @DeleteMapping(path = "/{ids}")
    public Result delByIds(@PathVariable String ids){
        userService.delByIds(ids);
        return Result.success();
    }


    @GetMapping
    public Result list(UserListDTO dto){
       PageVO<UserListVO> pageVO = userService.getByPage(dto);
        return Result.success(pageVO);
    }
    @GetMapping(path = "/{id}")
    public Result getById(@PathVariable Integer id){
        UserGetByIdVO vo = userService.getUserById(id);
        return Result.success(vo);
    }
    @GetMapping(path = "/list")
    public Result list(){
        List<User> list = userService.list();
        return Result.success(list);
    }
    @GetMapping(path = "/role/{roleLabel}")
    public Result getByRoleLabel(@PathVariable String roleLabel){
        List<User> list = userService.getByRoleLabel(roleLabel);
        return Result.success(list);
    }
    @GetMapping(path = "/dept/{deptId}")
    public Result getByDeptId(@PathVariable Integer deptId){
        List<User> list = userService.getByDeptId(deptId);
        return Result.success(list);
    }



    @PostMapping
    public Result save(@RequestBody UserSaveDTO dto){
        userService.add(dto);
        return Result.success();
    }
    @PutMapping
    public Result updateById(@RequestBody UserUpdateByIdDTO dto){
        userService.updateUserById(dto);
        return Result.success();
    }
}

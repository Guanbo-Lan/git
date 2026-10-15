package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.entity.dto.LoginDTO;
import com.qk.entity.vo.LoginVO;
import com.qk.management.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author MrZhao
 * 登录Controller
 *
 */
@RestController
@SuppressWarnings("all")
@RequestMapping(path = "/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody LoginDTO dto){
        LoginVO vo = loginService.login(dto);
        return Result.success(vo);
    }
}

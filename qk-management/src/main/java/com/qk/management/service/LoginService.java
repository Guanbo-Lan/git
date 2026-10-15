package com.qk.management.service;

import com.qk.entity.dto.LoginDTO;
import com.qk.entity.vo.LoginVO;

/**
 * @author MrZhao
 * 登录的业务层接口
 */
public interface LoginService {
    /**
     * 用户登录
     *
     * @param dto 登录信息
     * @return LoginVO
     */
    LoginVO login(LoginDTO dto);
}

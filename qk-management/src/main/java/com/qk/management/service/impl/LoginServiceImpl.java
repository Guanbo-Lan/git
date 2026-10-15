package com.qk.management.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qk.common.exception.Throws;
import com.qk.common.util.QKJwts;
import com.qk.entity.dto.LoginDTO;
import com.qk.entity.po.Role;
import com.qk.entity.po.User;
import com.qk.entity.vo.LoginVO;
import com.qk.management.mapper.RoleMapper;
import com.qk.management.mapper.UserMapper;
import com.qk.management.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.qk.common.enums.ExceptionCodeEnum.PASSWORD_USERNAME_INVALID;

/**
 * @author MrZhao
 * 登录业务层实现类
 */
@Service
@SuppressWarnings("all")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public LoginVO login(LoginDTO dto) {
        // 1. 根据用户名查询用户
        User user = userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getUsername, dto.getUsername()));
        // 2. 校验用户是否存在以及密码是否正确
        if (ObjectUtil.isNull(user) || !ObjectUtil.equals(user.getPassword(), dto.getPassword())) {
            Throws.throwE(PASSWORD_USERNAME_INVALID);
        }
        // 3. 查询角色标识
        String roleLabel = null;
        Role role = roleMapper.selectById(user.getRoleId());
        if (ObjectUtil.isNotNull(role)) {
            roleLabel = role.getLabel();
        }
        // 4. 生成JWT令牌
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", user.getId());
        payload.put("username", user.getUsername());
        payload.put("name", user.getName());
        String token = QKJwts.createJWTToken(payload);
        // 5. 组装返回数据
        LoginVO vo = new LoginVO();
        vo.setId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setName(user.getName());
        vo.setImage(user.getImage());
        vo.setRoleLabel(roleLabel);
        vo.setToken(token);
        return vo;
    }
}

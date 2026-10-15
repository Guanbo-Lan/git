package com.qk.management.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.qk.common.exception.Throws;
import com.qk.entity.dto.UserListDTO;
import com.qk.entity.dto.UserSaveDTO;
import com.qk.entity.dto.UserUpdateByIdDTO;
import com.qk.entity.po.Role;
import com.qk.entity.po.User;
import com.qk.entity.vo.UserGetByIdVO;
import com.qk.entity.vo.UserListVO;
import com.qk.entity.vo.PageVO;
import com.qk.management.service.UserService;
import com.qk.management.mapper.UserMapper;
import com.qk.management.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.qk.common.enums.ExceptionCodeEnum.PARAM_INVALID;

/**
 * @author MrZhao
 * 用户管理业务层实现类
 */
@Service
@SuppressWarnings("all")
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void delByIds(String ids) {
        // 参数校验
        if (StrUtil.isBlank(ids)) {
            Throws.throwE(PARAM_INVALID);
        }
        // 拆分逗号分隔的id并批量删除
        List<Integer> idList = Arrays.stream(ids.split(","))
                .map(Integer::valueOf)
                .toList();
        userMapper.deleteBatchIds(idList);
    }

    @Override
    public PageVO<UserListVO> getByPage(UserListDTO dto) {
        // 1. 参数校验（省略）
        // 2. 核心业务处理 // 利用我们当前业务层继承 IService 接口中的方法，简化了对DAO操作！
        Page<User> pageResult = this.page(PageDTO.of(dto.getPage(), dto.getPageSize()),
                Wrappers.lambdaQuery(User.class)
                        .like(StrUtil.isNotBlank(dto.getName()), User::getName, dto.getName())
                        .eq(ObjectUtil.isNotNull(dto.getStatus()), User::getStatus, dto.getStatus())
                        .like(StrUtil.isNotBlank(dto.getPhone()), User::getPhone, dto.getPhone())
                        .eq(ObjectUtil.isNotNull(dto.getDeptId()), User::getDeptId, dto.getDeptId())
                        .orderByDesc(User::getUpdateTime));

        // 3. 数据处理和拼装
        // List<User> ---》List<UserListVO>
        List<UserListVO> voList = pageResult.getRecords().stream().map((user) -> {
            // bean 拷贝
            UserListVO vo = new UserListVO();
            BeanUtil.copyProperties(user, vo);
            return vo;
        }).toList();
        PageVO<UserListVO> pageVO = new PageVO<>();
        pageVO.setRows(voList);
        pageVO.setTotal(pageResult.getTotal());
        return pageVO;
    }

    @Override
    public void add(UserSaveDTO dto) {
        // 1. 根据用户名查询数据库，存在用户则不保存 数据幂等校验
        String username = dto.getUsername();
        User userDB = userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getUsername, username));
        if (Objects.nonNull(userDB)) {
            throw new RuntimeException("添加用户已经存在请勿重复添加");
        }
        // 赋值dto数据到user
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setName(dto.getName());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setGender(dto.getGender());
        user.setStatus(dto.getStatus());
        user.setDeptId(dto.getDeptId());
        user.setRoleId(dto.getRoleId());
        user.setImage(dto.getImage());
        user.setRemark(dto.getRemark());
        // 数据补全
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        // 2. 保存用户
        userMapper.insert(user);
    }

    @Override
    public UserGetByIdVO getUserById(Integer id) {
        User user = this.getById(id);
        if (ObjectUtil.isNull(user)){
            Throws.throwE(PARAM_INVALID);
        }
        UserGetByIdVO vo = new UserGetByIdVO();
        BeanUtil.copyProperties(user,vo);
        return vo;
    }

    @Override
    public void updateUserById(UserUpdateByIdDTO dto) {
        // 参数校验
        if (ObjectUtil.isNull(dto.getId()) ||
                StrUtil.isBlank(dto.getUsername()) ||
                StrUtil.isBlank(dto.getName())) {
            Throws.throwE(PARAM_INVALID);
        }
        // 数据拷贝
        User user = BeanUtil.copyProperties(dto, User.class);
        // 补全更新时间
        user.setUpdateTime(LocalDateTime.now());
        // 更新
        this.saveOrUpdate(user);
    }

    @Override
    public List<User> getByRoleLabel(String roleLabel) {
        // 1. 根据角色标识查询角色，得到角色id
        Role role = roleMapper.selectOne(Wrappers.lambdaQuery(Role.class).eq(Role::getLabel, roleLabel));
        if (ObjectUtil.isNull(role)) {
            return List.of();
        }
        // 2. 根据角色id查询用户列表
        return this.list(Wrappers.lambdaQuery(User.class).eq(User::getRoleId, role.getId()));
    }

    @Override
    public List<User> getByDeptId(Integer deptId) {
        return this.list(Wrappers.lambdaQuery(User.class).eq(User::getDeptId, deptId));
    }
}

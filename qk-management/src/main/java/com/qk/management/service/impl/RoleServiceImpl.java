package com.qk.management.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.qk.common.exception.Throws;
import com.qk.entity.dto.RoleListDTO;
import com.qk.entity.dto.RoleSaveDTO;
import com.qk.entity.dto.RoleUpdateByIdDTO;
import com.qk.entity.po.Role;
import com.qk.entity.vo.RoleGetByIdVO;
import com.qk.entity.vo.RoleListVO;
import com.qk.entity.vo.PageVO;
import com.qk.management.service.RoleService;
import com.qk.management.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.qk.common.enums.ExceptionCodeEnum.PARAM_INVALID;

/**
 * @author MrZhao
 * 角色管理业务层实现类
 */
@Service
@SuppressWarnings("all")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void delById(Integer id) {
        // 业务分析能力
        Role role = this.getById(id);
        // 如果角色不存在则提示参数非法
        if (ObjectUtil.isNull(role)){
            Throws.throwE(PARAM_INVALID);
        }
        roleMapper.deleteById(id);
    }

    @Override
    public PageVO<RoleListVO> getByPage(RoleListDTO dto) {
        // 1. 参数校验（省略）
        // 2. 核心业务处理 // 利用我们当前业务层继承 IService 接口中的方法，简化了对DAO操作！
        Page<Role> pageResult = this.page(PageDTO.of(dto.getPage(), dto.getPageSize()),
                Wrappers.lambdaQuery(Role.class)
                        .like(StrUtil.isNotBlank(dto.getName()), Role::getName, dto.getName())
                        .like(StrUtil.isNotBlank(dto.getLabel()), Role::getLabel, dto.getLabel())
                        .orderByDesc(Role::getUpdateTime));

        // 3. 数据处理和拼装
        // List<Role> ---》List<RoleListVO>
        List<RoleListVO> voList = pageResult.getRecords().stream().map((role) -> {
            // bean 拷贝
            RoleListVO vo = new RoleListVO();
            BeanUtil.copyProperties(role, vo);
            return vo;
        }).toList();
        PageVO<RoleListVO> pageVO = new PageVO<>();
        pageVO.setRows(voList);
        pageVO.setTotal(pageResult.getTotal());
        return pageVO;
    }

    @Override
    public void add(RoleSaveDTO dto) {
        // 1. 根据角色名称查询数据库，存在角色则不保存 数据幂等校验
        String roleName = dto.getName();
        Role roleDB = roleMapper.selectOne(Wrappers.lambdaQuery(Role.class).eq(Role::getName, roleName));
        if (Objects.nonNull(roleDB)) {
            throw new RuntimeException("添加角色已经存在请勿重复添加");
        }
        // 赋值dto数据到role
        Role role = new Role();
        role.setName(dto.getName());
        role.setLabel(dto.getLabel());
        role.setRemark(dto.getRemark());
        // 数据补全
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        // 2. 保存角色
        roleMapper.insert(role);
    }

    @Override
    public RoleGetByIdVO getRoleById(Integer id) {
        Role role = this.getById(id);
        if (ObjectUtil.isNull(role)){
            Throws.throwE(PARAM_INVALID);
        }
        RoleGetByIdVO vo = new RoleGetByIdVO();
        BeanUtil.copyProperties(role,vo);
        return vo;
    }

    @Override
    public void updateRoleById(RoleUpdateByIdDTO dto) {
        // 参数校验
        if (ObjectUtil.isNull(dto.getId()) ||
                StrUtil.isBlank(dto.getName()) ||
                StrUtil.isBlank(dto.getLabel())) {
            Throws.throwE(PARAM_INVALID);
        }
        // 数据拷贝
        Role role = BeanUtil.copyProperties(dto, Role.class);
        // 补全更新时间
        role.setUpdateTime(LocalDateTime.now());
        // 更新
        this.saveOrUpdate(role);
    }
}

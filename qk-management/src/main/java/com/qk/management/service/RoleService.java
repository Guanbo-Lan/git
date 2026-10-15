package com.qk.management.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.qk.entity.dto.RoleListDTO;
import com.qk.entity.dto.RoleSaveDTO;
import com.qk.entity.dto.RoleUpdateByIdDTO;
import com.qk.entity.po.Role;
import com.qk.entity.vo.RoleGetByIdVO;
import com.qk.entity.vo.RoleListVO;
import com.qk.entity.vo.PageVO;


/**
 * @author MrZhao
 * 角色管理的业务层接口
 */
public interface RoleService extends IService<Role> {
    /**
     *  根据角色id删除角色
     * @param id 角色id
     */
    void delById(Integer id);
    /**
     *  分页条件查询
     * @param dto 分页条件
     * @return PageVO
     */
    PageVO<RoleListVO> getByPage(RoleListDTO dto);
    /**
     * 添加角色
     *
     * @param dto 角色信息
     */
    void add(RoleSaveDTO dto);


    RoleGetByIdVO getRoleById(Integer id);

    void updateRoleById(RoleUpdateByIdDTO dto);
}

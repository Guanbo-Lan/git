package com.qk.management.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.qk.entity.dto.UserListDTO;
import com.qk.entity.dto.UserSaveDTO;
import com.qk.entity.dto.UserUpdateByIdDTO;
import com.qk.entity.po.User;
import com.qk.entity.vo.UserGetByIdVO;
import com.qk.entity.vo.UserListVO;
import com.qk.entity.vo.PageVO;

import java.util.List;


/**
 * @author MrZhao
 * 用户管理的业务层接口
 */
public interface UserService extends IService<User> {
    /**
     *  根据用户id批量删除用户
     * @param ids 用户id集合，逗号分隔
     */
    void delByIds(String ids);
    /**
     *  分页条件查询
     * @param dto 分页条件
     * @return PageVO
     */
    PageVO<UserListVO> getByPage(UserListDTO dto);
    /**
     * 添加用户
     *
     * @param dto 用户信息
     */
    void add(UserSaveDTO dto);


    UserGetByIdVO getUserById(Integer id);

    void updateUserById(UserUpdateByIdDTO dto);

    /**
     * 根据角色标识查询用户
     *
     * @param roleLabel 角色标识
     * @return List<User>
     */
    List<User> getByRoleLabel(String roleLabel);

    /**
     * 根据部门查询用户
     *
     * @param deptId 部门id
     * @return List<User>
     */
    List<User> getByDeptId(Integer deptId);
}

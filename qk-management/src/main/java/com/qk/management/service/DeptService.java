package com.qk.management.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.qk.entity.dto.DeptListDTO;
import com.qk.entity.dto.DeptSaveDTO;
import com.qk.entity.dto.DeptUpdateByIdDTO;
import com.qk.entity.po.Dept;
import com.qk.entity.vo.DeptGetByIdVO;
import com.qk.entity.vo.DeptListVO;
import com.qk.entity.vo.PageVO;


/**
 * @author MrZhao
 * 部门管理的业务层接口
 */
public interface DeptService extends IService<Dept> {
    /**
     *  根据部门id删除部门
     * @param id 部门id
     */
    void delById(Integer id);
    /**
     *  分页条件查询
     * @param dto 分页条件
     * @return PageVO
     */
    PageVO<DeptListVO> getByPage(DeptListDTO dto);
    /**
     * 添加部门
     *
     * @param dto 部门信息
     */
    void add(DeptSaveDTO dto);


    DeptGetByIdVO getDeptById(Integer id);

    void updateDeptById(DeptUpdateByIdDTO dto);
}

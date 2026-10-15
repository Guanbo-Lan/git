package com.qk.management.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.qk.common.exception.QkException;
import com.qk.common.exception.Throws;
import com.qk.entity.dto.DeptListDTO;
import com.qk.entity.dto.DeptSaveDTO;
import com.qk.entity.dto.DeptUpdateByIdDTO;
import com.qk.entity.po.Dept;
import com.qk.entity.vo.DeptGetByIdVO;
import com.qk.entity.vo.DeptListVO;
import com.qk.entity.vo.PageVO;
import com.qk.management.service.DeptService;
import com.qk.management.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.qk.common.enums.ExceptionCodeEnum.PARAM_INVALID;
import static com.qk.common.enums.ExceptionCodeEnum.STATUS_INVALID;

/**
 * @author MrZhao
 * 部门管理业务层实现类
 */
@Service
@SuppressWarnings("all")
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept>
        implements DeptService {

    private static final Integer ENABLE = 1; // 启用
    private static final Integer DISABLE = 0; // 停用

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public void delById(Integer id) {
        // 业务分析能力
        Dept dept = this.getById(id);
        // 如果部门不存在则提示参数非法
        if (ObjectUtil.isNull(dept)){
            Throws.throwE(PARAM_INVALID);
        }
        // 部门状态，正常状态不允许删除 状态为 0停止 删除
        if (ENABLE.equals(dept.getStatus())){
            Throws.throwE(STATUS_INVALID);
        }
        deptMapper.deleteById(id);
    }

    @Override
    public PageVO<DeptListVO> getByPage(DeptListDTO dto) {
        // 1. 参数校验（省略）
        // 2. 核心业务处理 // 利用我们当前业务层继承 IService 接口中的方法，简化了对DAO操作！
        Page<Dept> pageResult = this.page(PageDTO.of(dto.getPage(), dto.getPageSize()),
                Wrappers.lambdaQuery(Dept.class)
                        .like(StrUtil.isNotBlank(dto.getName()), Dept::getName, dto.getName())
                        .eq(ObjectUtil.isNotNull(dto.getStatus()), Dept::getStatus, dto.getStatus())
                        .orderByDesc(Dept::getUpdateTime));

        // MP 提供了一个
        // 3. 数据处理和拼装
        // List<Dept> ---》List<DeptListVO>
        List<DeptListVO> voList = pageResult.getRecords().stream().map((dept) -> {
            // bean 拷贝
            DeptListVO vo = new DeptListVO();
            BeanUtil.copyProperties(dept, vo);
            return vo;
        }).toList();
        PageVO<DeptListVO> pageVO = new PageVO<>();
        pageVO.setRows(voList);
        pageVO.setTotal(pageResult.getTotal());
        return pageVO;
    }

    @Override
    public void add(DeptSaveDTO dto) {
        // 1. 根据部门名称查询数据库，存在部门则不保存 数据幂等校验
        String deptName = dto.getName();
        Dept deptDB = deptMapper.selectOne(Wrappers.lambdaQuery(Dept.class).eq(Dept::getName, deptName));
        if (Objects.nonNull(deptDB)) {
            throw new RuntimeException("添加部门已经存在请勿重复添加");
        }
        // 赋值dto数据到dept
        Dept dept = new Dept();
        dept.setName(dto.getName());
        dept.setStatus(dto.getStatus());
        // 数据补全
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        // 2. 保存部门
        deptMapper.insert(dept);
    }

    @Override
    public DeptGetByIdVO getDeptById(Integer id) {
        Dept dept = this.getById(id);
        if (ObjectUtil.isNull(dept)){
            Throws.throwE(PARAM_INVALID);
        }
        DeptGetByIdVO vo = new DeptGetByIdVO();
        BeanUtil.copyProperties(dept,vo);
        return vo;
    }
        @Override
        public void updateDeptById(DeptUpdateByIdDTO dto) {
            // 参数校验
            if (ObjectUtil.isNull(dto.getId()) ||
                    ObjectUtil.isNull(dto.getStatus()) ||
                    StrUtil.isBlank(dto.getName())) {
                Throws.throwE(PARAM_INVALID);
            }
            // 数据拷贝
            Dept dept = BeanUtil.copyProperties(dto, Dept.class);
            // 补全更新时间
            dept.setUpdateTime(LocalDateTime.now());
            // 更新
            this.saveOrUpdate(dept);
        }
    }





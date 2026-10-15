package com.qk.management.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.qk.common.exception.Throws;
import com.qk.entity.dto.BusinessListDTO;
import com.qk.entity.dto.BusinessPoolListDTO;
import com.qk.entity.dto.BusinessSaveDTO;
import com.qk.entity.dto.BusinessUpdateByIdDTO;
import com.qk.entity.po.Business;
import com.qk.entity.vo.BusinessGetByIdVO;
import com.qk.entity.vo.BusinessListVO;
import com.qk.entity.vo.BusinessPoolListVO;
import com.qk.entity.vo.PageVO;
import com.qk.management.service.BusinessService;
import com.qk.management.mapper.BusinessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.qk.common.enums.ExceptionCodeEnum.PARAM_INVALID;

/**
 * @author MrZhao
 * 商机管理业务层实现类
 */
@Service
@SuppressWarnings("all")
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business>
        implements BusinessService {

    private static final Integer STATUS_WAIT_ASSIGN = 1; // 待分配
    private static final Integer STATUS_FOLLOW_UP = 2;   // 跟进中
    private static final Integer STATUS_POOL = 4;        // 公海池
    private static final Integer STATUS_TO_CUSTOMER = 5; // 已转客户

    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public PageVO<BusinessListVO> getByPage(BusinessListDTO dto) {
        // 1. 参数校验（省略）
        // 2. 核心业务处理 // 利用我们当前业务层继承 IService 接口中的方法，简化了对DAO操作！
        // 注：assignName 为联表字段，单表模式下暂不参与过滤
        Page<Business> pageResult = this.page(PageDTO.of(dto.getPage(), dto.getPageSize()),
                Wrappers.lambdaQuery(Business.class)
                        .eq(ObjectUtil.isNotNull(dto.getBusinessId()), Business::getId, dto.getBusinessId())
                        .like(StrUtil.isNotBlank(dto.getName()), Business::getName, dto.getName())
                        .like(StrUtil.isNotBlank(dto.getPhone()), Business::getPhone, dto.getPhone())
                        .eq(ObjectUtil.isNotNull(dto.getStatus()), Business::getStatus, dto.getStatus())
                        .orderByDesc(Business::getUpdateTime));

        // 3. 数据处理和拼装
        // List<Business> ---》List<BusinessListVO>
        List<BusinessListVO> voList = pageResult.getRecords().stream().map((business) -> {
            // bean 拷贝
            BusinessListVO vo = new BusinessListVO();
            BeanUtil.copyProperties(business, vo);
            return vo;
        }).toList();
        PageVO<BusinessListVO> pageVO = new PageVO<>();
        pageVO.setRows(voList);
        pageVO.setTotal(pageResult.getTotal());
        return pageVO;
    }

    @Override
    public void add(BusinessSaveDTO dto) {
        // 1. 根据手机号查询数据库，存在商机则不保存 数据幂等校验
        String phone = dto.getPhone();
        Business businessDB = businessMapper.selectOne(Wrappers.lambdaQuery(Business.class).eq(Business::getPhone, phone));
        if (Objects.nonNull(businessDB)) {
            throw new RuntimeException("添加商机已经存在请勿重复添加");
        }
        // 赋值dto数据到business
        Business business = new Business();
        business.setPhone(dto.getPhone());
        business.setChannel(dto.getChannel());
        business.setName(dto.getName());
        business.setGender(dto.getGender());
        business.setAge(dto.getAge());
        business.setWechat(dto.getWechat());
        business.setQq(dto.getQq());
        business.setSubject(dto.getSubject());
        business.setRemark(dto.getRemark());
        business.setDegree(dto.getDegree());
        business.setJobStatus(dto.getJobStatus());
        business.setCourseId(dto.getCourseId());
        // 数据补全
        business.setStatus(STATUS_WAIT_ASSIGN);
        business.setCreateTime(LocalDateTime.now());
        business.setUpdateTime(LocalDateTime.now());
        // 2. 保存商机
        businessMapper.insert(business);
    }

    @Override
    public void assign(Integer businessId, Integer userId) {
        // 业务分析能力
        Business business = this.getById(businessId);
        // 如果商机不存在则提示参数非法
        if (ObjectUtil.isNull(business)){
            Throws.throwE(PARAM_INVALID);
        }
        // 分配归属人，并置为跟进中状态
        business.setUserId(userId);
        business.setStatus(STATUS_FOLLOW_UP);
        business.setUpdateTime(LocalDateTime.now());
        this.saveOrUpdate(business);
    }

    @Override
    public void back(Integer id) {
        // 业务分析能力
        Business business = this.getById(id);
        // 如果商机不存在则提示参数非法
        if (ObjectUtil.isNull(business)){
            Throws.throwE(PARAM_INVALID);
        }
        // 踢回公海池
        business.setStatus(STATUS_POOL);
        business.setUpdateTime(LocalDateTime.now());
        this.saveOrUpdate(business);
    }

    @Override
    public void toCustomer(Integer id) {
        // 业务分析能力
        Business business = this.getById(id);
        // 如果商机不存在则提示参数非法
        if (ObjectUtil.isNull(business)){
            Throws.throwE(PARAM_INVALID);
        }
        // 转为客户
        business.setStatus(STATUS_TO_CUSTOMER);
        business.setUpdateTime(LocalDateTime.now());
        this.saveOrUpdate(business);
    }

    @Override
    public BusinessGetByIdVO getBusinessById(Integer id) {
        Business business = this.getById(id);
        if (ObjectUtil.isNull(business)){
            Throws.throwE(PARAM_INVALID);
        }
        BusinessGetByIdVO vo = new BusinessGetByIdVO();
        BeanUtil.copyProperties(business,vo);
        // 注：trackRecords 跟进记录为联表数据，单表模式下未填充
        return vo;
    }

    @Override
    public void followUp(BusinessUpdateByIdDTO dto) {
        // 参数校验
        if (ObjectUtil.isNull(dto.getId()) ||
                StrUtil.isBlank(dto.getName())) {
            Throws.throwE(PARAM_INVALID);
        }
        // 数据拷贝（dto 中的 keyItems、trackStatus、record 属于跟进记录，商机表无对应字段，BeanUtil 拷贝时自动忽略）
        Business business = BeanUtil.copyProperties(dto, Business.class);
        // 补全更新时间
        business.setUpdateTime(LocalDateTime.now());
        // 更新
        this.saveOrUpdate(business);
    }

    @Override
    public PageVO<BusinessPoolListVO> getByPool(BusinessPoolListDTO dto) {
        // 1. 参数校验（省略）
        // 2. 核心业务处理 // 公海池即状态为「公海池」的商机
        Page<Business> pageResult = this.page(PageDTO.of(dto.getPage(), dto.getPageSize()),
                Wrappers.lambdaQuery(Business.class)
                        .eq(Business::getStatus, STATUS_POOL)
                        .eq(ObjectUtil.isNotNull(dto.getBusinessId()), Business::getId, dto.getBusinessId())
                        .like(StrUtil.isNotBlank(dto.getName()), Business::getName, dto.getName())
                        .like(StrUtil.isNotBlank(dto.getPhone()), Business::getPhone, dto.getPhone())
                        .eq(ObjectUtil.isNotNull(dto.getSubject()), Business::getSubject, dto.getSubject())
                        .orderByDesc(Business::getUpdateTime));

        // 3. 数据处理和拼装
        // List<Business> ---》List<BusinessPoolListVO>
        List<BusinessPoolListVO> voList = pageResult.getRecords().stream().map((business) -> {
            // bean 拷贝
            BusinessPoolListVO vo = new BusinessPoolListVO();
            BeanUtil.copyProperties(business, vo);
            return vo;
        }).toList();
        PageVO<BusinessPoolListVO> pageVO = new PageVO<>();
        pageVO.setRows(voList);
        pageVO.setTotal(pageResult.getTotal());
        return pageVO;
    }
}

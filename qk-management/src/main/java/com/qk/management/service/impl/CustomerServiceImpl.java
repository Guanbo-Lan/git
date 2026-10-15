package com.qk.management.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.qk.common.exception.Throws;
import com.qk.entity.dto.CustomerListDTO;
import com.qk.entity.dto.CustomerSaveDTO;
import com.qk.entity.dto.CustomerUpdateByIdDTO;
import com.qk.entity.po.Customer;
import com.qk.entity.vo.CustomerGetByIdVO;
import com.qk.entity.vo.CustomerListVO;
import com.qk.entity.vo.PageVO;
import com.qk.management.service.CustomerService;
import com.qk.management.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.qk.common.enums.ExceptionCodeEnum.PARAM_INVALID;

/**
 * @author MrZhao
 * 客户管理业务层实现类
 */
@Service
@SuppressWarnings("all")
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer>
        implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public PageVO<CustomerListVO> getByPage(CustomerListDTO dto) {
        // 1. 参数校验（省略）
        // 2. 核心业务处理 // 利用我们当前业务层继承 IService 接口中的方法，简化了对DAO操作！
        Page<Customer> pageResult = this.page(PageDTO.of(dto.getPage(), dto.getPageSize()),
                Wrappers.lambdaQuery(Customer.class)
                        .like(StrUtil.isNotBlank(dto.getPhone()), Customer::getPhone, dto.getPhone())
                        .like(StrUtil.isNotBlank(dto.getName()), Customer::getName, dto.getName())
                        .eq(ObjectUtil.isNotNull(dto.getChannel()), Customer::getChannel, dto.getChannel())
                        .eq(ObjectUtil.isNotNull(dto.getSubject()), Customer::getSubject, dto.getSubject())
                        .orderByDesc(Customer::getUpdateTime));

        // 3. 数据处理和拼装
        // List<Customer> ---》List<CustomerListVO>
        List<CustomerListVO> voList = pageResult.getRecords().stream().map((customer) -> {
            // bean 拷贝
            CustomerListVO vo = new CustomerListVO();
            BeanUtil.copyProperties(customer, vo);
            return vo;
        }).toList();
        PageVO<CustomerListVO> pageVO = new PageVO<>();
        pageVO.setRows(voList);
        pageVO.setTotal(pageResult.getTotal());
        return pageVO;
    }

    @Override
    public void add(CustomerSaveDTO dto) {
        // 1. 根据手机号查询数据库，存在客户则不保存 数据幂等校验
        String phone = dto.getPhone();
        Customer customerDB = customerMapper.selectOne(Wrappers.lambdaQuery(Customer.class).eq(Customer::getPhone, phone));
        if (Objects.nonNull(customerDB)) {
            throw new RuntimeException("添加客户已经存在请勿重复添加");
        }
        // 赋值dto数据到customer
        Customer customer = new Customer();
        customer.setPhone(dto.getPhone());
        customer.setChannel(dto.getChannel());
        customer.setName(dto.getName());
        customer.setGender(dto.getGender());
        customer.setAge(dto.getAge());
        customer.setWechat(dto.getWechat());
        customer.setQq(dto.getQq());
        customer.setDegree(dto.getDegree());
        customer.setJobStatus(dto.getJobStatus());
        customer.setSubject(dto.getSubject());
        customer.setCourseId(dto.getCourseId());
        // 数据补全
        customer.setCreateTime(LocalDateTime.now());
        customer.setUpdateTime(LocalDateTime.now());
        // 2. 保存客户
        customerMapper.insert(customer);
    }

    @Override
    public CustomerGetByIdVO getCustomerById(Integer id) {
        Customer customer = this.getById(id);
        if (ObjectUtil.isNull(customer)){
            Throws.throwE(PARAM_INVALID);
        }
        CustomerGetByIdVO vo = new CustomerGetByIdVO();
        BeanUtil.copyProperties(customer,vo);
        return vo;
    }

    @Override
    public void updateCustomerById(CustomerUpdateByIdDTO dto) {
        // 参数校验
        if (ObjectUtil.isNull(dto.getId()) ||
                StrUtil.isBlank(dto.getName())) {
            Throws.throwE(PARAM_INVALID);
        }
        // 数据拷贝
        Customer customer = BeanUtil.copyProperties(dto, Customer.class);
        // 补全更新时间
        customer.setUpdateTime(LocalDateTime.now());
        // 更新
        this.saveOrUpdate(customer);
    }
}

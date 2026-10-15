package com.qk.management.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.qk.entity.dto.CustomerListDTO;
import com.qk.entity.dto.CustomerSaveDTO;
import com.qk.entity.dto.CustomerUpdateByIdDTO;
import com.qk.entity.po.Customer;
import com.qk.entity.vo.CustomerGetByIdVO;
import com.qk.entity.vo.CustomerListVO;
import com.qk.entity.vo.PageVO;


/**
 * @author MrZhao
 * 客户管理的业务层接口
 */
public interface CustomerService extends IService<Customer> {
    /**
     *  分页条件查询
     * @param dto 分页条件
     * @return PageVO
     */
    PageVO<CustomerListVO> getByPage(CustomerListDTO dto);
    /**
     * 添加客户
     *
     * @param dto 客户信息
     */
    void add(CustomerSaveDTO dto);


    CustomerGetByIdVO getCustomerById(Integer id);

    void updateCustomerById(CustomerUpdateByIdDTO dto);
}

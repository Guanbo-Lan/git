package com.qk.management.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.qk.entity.dto.BusinessListDTO;
import com.qk.entity.dto.BusinessPoolListDTO;
import com.qk.entity.dto.BusinessSaveDTO;
import com.qk.entity.dto.BusinessUpdateByIdDTO;
import com.qk.entity.po.Business;
import com.qk.entity.vo.BusinessGetByIdVO;
import com.qk.entity.vo.BusinessListVO;
import com.qk.entity.vo.BusinessPoolListVO;
import com.qk.entity.vo.PageVO;


/**
 * @author MrZhao
 * 商机管理的业务层接口
 */
public interface BusinessService extends IService<Business> {
    /**
     *  分页条件查询
     * @param dto 分页条件
     * @return PageVO
     */
    PageVO<BusinessListVO> getByPage(BusinessListDTO dto);
    /**
     * 添加商机
     *
     * @param dto 商机信息
     */
    void add(BusinessSaveDTO dto);

    /**
     * 为指定用户分配商机
     *
     * @param businessId 商机id
     * @param userId 用户id
     */
    void assign(Integer businessId, Integer userId);

    /**
     * 踢回公海
     *
     * @param id 商机id
     */
    void back(Integer id);

    /**
     * 转客户处理
     *
     * @param id 商机id
     */
    void toCustomer(Integer id);


    BusinessGetByIdVO getBusinessById(Integer id);

    /**
     * 跟进商机
     *
     * @param dto 商机信息
     */
    void followUp(BusinessUpdateByIdDTO dto);

    /**
     * 公海池分页条件查询
     *
     * @param dto 分页条件
     * @return PageVO
     */
    PageVO<BusinessPoolListVO> getByPool(BusinessPoolListDTO dto);
}

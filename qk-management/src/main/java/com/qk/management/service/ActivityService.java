package com.qk.management.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.qk.entity.dto.ActivityListDTO;
import com.qk.entity.dto.ActivitySaveDTO;
import com.qk.entity.dto.ActivityUpdateByIdDTO;
import com.qk.entity.po.Activity;
import com.qk.entity.vo.ActivityGetByIdVO;
import com.qk.entity.vo.ActivityListVO;
import com.qk.entity.vo.PageVO;

import java.util.List;


/**
 * @author MrZhao
 * 活动管理的业务层接口
 */
public interface ActivityService extends IService<Activity> {
    /**
     *  根据活动id删除活动
     * @param id 活动id
     */
    void delById(Integer id);
    /**
     *  分页条件查询
     * @param dto 分页条件
     * @return PageVO
     */
    PageVO<ActivityListVO> getByPage(ActivityListDTO dto);
    /**
     * 添加活动
     *
     * @param dto 活动信息
     */
    void add(ActivitySaveDTO dto);


    ActivityGetByIdVO getActivityById(Integer id);

    void updateActivityById(ActivityUpdateByIdDTO dto);

    /**
     * 根据活动类型查询活动
     *
     * @param type 活动类型
     * @return List<Activity>
     */
    List<Activity> getByType(Integer type);
}

package com.qk.management.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.qk.common.exception.Throws;
import com.qk.entity.dto.ActivityListDTO;
import com.qk.entity.dto.ActivitySaveDTO;
import com.qk.entity.dto.ActivityUpdateByIdDTO;
import com.qk.entity.po.Activity;
import com.qk.entity.vo.ActivityGetByIdVO;
import com.qk.entity.vo.ActivityListVO;
import com.qk.entity.vo.PageVO;
import com.qk.management.service.ActivityService;
import com.qk.management.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.qk.common.enums.ExceptionCodeEnum.PARAM_INVALID;

/**
 * @author MrZhao
 * 活动管理业务层实现类
 */
@Service
@SuppressWarnings("all")
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
        implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public void delById(Integer id) {
        // 业务分析能力
        Activity activity = this.getById(id);
        // 如果活动不存在则提示参数非法
        if (ObjectUtil.isNull(activity)){
            Throws.throwE(PARAM_INVALID);
        }
        activityMapper.deleteById(id);
    }

    @Override
    public PageVO<ActivityListVO> getByPage(ActivityListDTO dto) {
        // 1. 参数校验（省略）
        // 2. 核心业务处理 // 利用我们当前业务层继承 IService 接口中的方法，简化了对DAO操作！
        Page<Activity> pageResult = this.page(PageDTO.of(dto.getPage(), dto.getPageSize()),
                Wrappers.lambdaQuery(Activity.class)
                        .eq(ObjectUtil.isNotNull(dto.getChannel()), Activity::getChannel, dto.getChannel())
                        .eq(ObjectUtil.isNotNull(dto.getType()), Activity::getType, dto.getType())
                        .orderByDesc(Activity::getUpdateTime));

        // 3. 数据处理和拼装
        // List<Activity> ---》List<ActivityListVO>
        List<ActivityListVO> voList = pageResult.getRecords().stream().map((activity) -> {
            // bean 拷贝
            ActivityListVO vo = new ActivityListVO();
            BeanUtil.copyProperties(activity, vo);
            return vo;
        }).toList();
        PageVO<ActivityListVO> pageVO = new PageVO<>();
        pageVO.setRows(voList);
        pageVO.setTotal(pageResult.getTotal());
        return pageVO;
    }

    @Override
    public void add(ActivitySaveDTO dto) {
        // 1. 根据活动名称查询数据库，存在活动则不保存 数据幂等校验
        String activityName = dto.getName();
        Activity activityDB = activityMapper.selectOne(Wrappers.lambdaQuery(Activity.class).eq(Activity::getName, activityName));
        if (Objects.nonNull(activityDB)) {
            throw new RuntimeException("添加活动已经存在请勿重复添加");
        }
        // 赋值dto数据到activity
        Activity activity = new Activity();
        activity.setChannel(dto.getChannel());
        activity.setName(dto.getName());
        activity.setStartTime(dto.getStartTime());
        activity.setEndTime(dto.getEndTime());
        activity.setDescription(dto.getDescription());
        activity.setType(dto.getType());
        activity.setDiscount(dto.getDiscount());
        activity.setVoucher(dto.getVoucher());
        // 数据补全
        activity.setCreateTime(LocalDateTime.now());
        activity.setUpdateTime(LocalDateTime.now());
        // 2. 保存活动
        activityMapper.insert(activity);
    }

    @Override
    public ActivityGetByIdVO getActivityById(Integer id) {
        Activity activity = this.getById(id);
        if (ObjectUtil.isNull(activity)){
            Throws.throwE(PARAM_INVALID);
        }
        ActivityGetByIdVO vo = new ActivityGetByIdVO();
        BeanUtil.copyProperties(activity,vo);
        return vo;
    }

    @Override
    public void updateActivityById(ActivityUpdateByIdDTO dto) {
        // 参数校验
        if (ObjectUtil.isNull(dto.getId()) ||
                StrUtil.isBlank(dto.getName())) {
            Throws.throwE(PARAM_INVALID);
        }
        // 数据拷贝
        Activity activity = BeanUtil.copyProperties(dto, Activity.class);
        // 补全更新时间
        activity.setUpdateTime(LocalDateTime.now());
        // 更新
        this.saveOrUpdate(activity);
    }

    @Override
    public List<Activity> getByType(Integer type) {
        return this.list(Wrappers.lambdaQuery(Activity.class).eq(Activity::getType, type));
    }
}

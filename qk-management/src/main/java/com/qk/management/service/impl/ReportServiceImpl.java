package com.qk.management.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qk.entity.po.Business;
import com.qk.entity.po.Clue;
import com.qk.entity.vo.OverviewVO;
import com.qk.management.mapper.BusinessMapper;
import com.qk.management.mapper.ClueMapper;
import com.qk.management.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MrZhao
 * 首页概览业务层实现类
 */
@Service
@SuppressWarnings("all")
public class ReportServiceImpl implements ReportService {

    // 线索状态
    private static final Integer CLUE_WAIT_ALLOT = 1;   // 待分配
    private static final Integer CLUE_FOLLOWING = 2;    // 跟进中
    private static final Integer CLUE_FALSE = 4;        // 伪线索
    private static final Integer CLUE_TO_BUSINESS = 5;  // 已转商机

    // 商机状态
    private static final Integer BUSINESS_WAIT_ALLOT = 1;    // 待分配
    private static final Integer BUSINESS_FOLLOWING = 2;     // 跟进中
    private static final Integer BUSINESS_TO_CUSTOMER = 5;   // 已转客户

    @Autowired
    private ClueMapper clueMapper;

    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public OverviewVO overview() {
        OverviewVO vo = new OverviewVO();

        // 线索概览
        vo.setClueTotal(clueMapper.selectCount(Wrappers.lambdaQuery(Clue.class)));
        vo.setClueWaitAllot(clueMapper.selectCount(Wrappers.lambdaQuery(Clue.class).eq(Clue::getStatus, CLUE_WAIT_ALLOT)));
        vo.setClueFollowing(clueMapper.selectCount(Wrappers.lambdaQuery(Clue.class).eq(Clue::getStatus, CLUE_FOLLOWING)));
        vo.setClueFalse(clueMapper.selectCount(Wrappers.lambdaQuery(Clue.class).eq(Clue::getStatus, CLUE_FALSE)));
        vo.setClueConvertBusiness(clueMapper.selectCount(Wrappers.lambdaQuery(Clue.class).eq(Clue::getStatus, CLUE_TO_BUSINESS)));
        // 待跟进：已分配、尚未转出的线索（与跟进中共用状态，待数据字典确认）
        vo.setClueWaitFollow(vo.getClueFollowing());

        // 商机概览
        vo.setBusinessTotal(businessMapper.selectCount(Wrappers.lambdaQuery(Business.class)));
        vo.setBusinessWaitAllot(businessMapper.selectCount(Wrappers.lambdaQuery(Business.class).eq(Business::getStatus, BUSINESS_WAIT_ALLOT)));
        vo.setBusinessFollowing(businessMapper.selectCount(Wrappers.lambdaQuery(Business.class).eq(Business::getStatus, BUSINESS_FOLLOWING)));
        vo.setBusinessConvertCustomer(businessMapper.selectCount(Wrappers.lambdaQuery(Business.class).eq(Business::getStatus, BUSINESS_TO_CUSTOMER)));
        // 待跟进：与跟进中共用状态，待数据字典确认
        vo.setBusinessWaitFollow(vo.getBusinessFollowing());
        // 伪商机：当前状态模型未单独定义，暂计 0
        vo.setBusinessFalse(0L);

        return vo;
    }
}

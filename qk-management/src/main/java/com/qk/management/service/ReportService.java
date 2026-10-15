package com.qk.management.service;

import com.qk.entity.vo.OverviewVO;

/**
 * @author MrZhao
 * 首页概览的业务层接口
 */
public interface ReportService {
    /**
     * 查询首页线索、商机的概览数据
     *
     * @return OverviewVO
     */
    OverviewVO overview();
}

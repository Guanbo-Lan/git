package com.qk.management.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.qk.entity.dto.LogListDTO;
import com.qk.entity.po.OperateLog;
import com.qk.entity.vo.LogListVO;
import com.qk.entity.vo.PageVO;


/**
 * @author MrZhao
 * 日志管理的业务层接口
 */
public interface OperateLogService extends IService<OperateLog> {
    /**
     *  分页条件查询
     * @param dto 分页条件
     * @return PageVO
     */
    PageVO<LogListVO> getByPage(LogListDTO dto);
}

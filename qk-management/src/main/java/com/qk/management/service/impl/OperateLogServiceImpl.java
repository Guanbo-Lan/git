package com.qk.management.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.qk.entity.dto.LogListDTO;
import com.qk.entity.po.OperateLog;
import com.qk.entity.vo.LogListVO;
import com.qk.entity.vo.PageVO;
import com.qk.management.service.OperateLogService;
import com.qk.management.mapper.OperateLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MrZhao
 * 日志管理业务层实现类
 */
@Service
@SuppressWarnings("all")
public class OperateLogServiceImpl extends ServiceImpl<OperateLogMapper, OperateLog>
        implements OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageVO<LogListVO> getByPage(LogListDTO dto) {
        // 1. 参数校验（省略）
        // 2. 核心业务处理 // 利用我们当前业务层继承 IService 接口中的方法，简化了对DAO操作！
        Page<OperateLog> pageResult = this.page(PageDTO.of(dto.getPage(), dto.getPageSize()),
                Wrappers.lambdaQuery(OperateLog.class)
                        .like(StrUtil.isNotBlank(dto.getOperateUserName()), OperateLog::getOperateUserName, dto.getOperateUserName())
                        .orderByDesc(OperateLog::getOperateTime));

        // 3. 数据处理和拼装
        // List<OperateLog> ---》List<LogListVO>
        List<LogListVO> voList = pageResult.getRecords().stream().map((operateLog) -> {
            // bean 拷贝
            LogListVO vo = new LogListVO();
            BeanUtil.copyProperties(operateLog, vo);
            return vo;
        }).toList();
        PageVO<LogListVO> pageVO = new PageVO<>();
        pageVO.setRows(voList);
        pageVO.setTotal(pageResult.getTotal());
        return pageVO;
    }
}

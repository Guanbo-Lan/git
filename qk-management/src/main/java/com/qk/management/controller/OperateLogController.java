package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.entity.dto.LogListDTO;
import com.qk.entity.vo.LogListVO;
import com.qk.entity.vo.PageVO;
import com.qk.management.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author MrZhao
 * 日志管理Controller
 *
 */
@RestController
@SuppressWarnings("all")
@RequestMapping(path = "/logs")
public class OperateLogController {

    @Autowired
    private OperateLogService operateLogService;

    @GetMapping
    public Result list(LogListDTO dto){
        PageVO<LogListVO> pageVO = operateLogService.getByPage(dto);
        return Result.success(pageVO);
    }
}

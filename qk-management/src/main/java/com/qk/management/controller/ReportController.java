package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.entity.vo.OverviewVO;
import com.qk.management.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author MrZhao
 * 首页概览Controller
 *
 */
@RestController
@SuppressWarnings("all")
@RequestMapping(path = "/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping(path = "/overview")
    public Result overview(){
        OverviewVO vo = reportService.overview();
        return Result.success(vo);
    }
}

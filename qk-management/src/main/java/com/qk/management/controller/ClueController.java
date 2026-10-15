package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.entity.dto.ClueFalseDTO;
import com.qk.entity.dto.ClueListDTO;
import com.qk.entity.dto.CluePoolListDTO;
import com.qk.entity.dto.ClueSaveDTO;
import com.qk.entity.dto.ClueUpdateByIdDTO;
import com.qk.entity.vo.ClueGetByIdVO;
import com.qk.entity.vo.ClueListVO;
import com.qk.entity.vo.CluePoolListVO;
import com.qk.entity.vo.PageVO;
import com.qk.management.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author MrZhao
 * 线索管理Controller
 *
 */
@RestController
@SuppressWarnings("all")
@RequestMapping(path = "/clues")
public class ClueController {

    @Autowired
    private ClueService clueService;


    @GetMapping
    public Result list(ClueListDTO dto){
        PageVO<ClueListVO> pageVO = clueService.getByPage(dto);
        return Result.success(pageVO);
    }
    @GetMapping(path = "/pool")
    public Result pool(CluePoolListDTO dto){
        PageVO<CluePoolListVO> pageVO = clueService.getByPool(dto);
        return Result.success(pageVO);
    }
    @GetMapping(path = "/{id}")
    public Result getById(@PathVariable Integer id){
        ClueGetByIdVO vo = clueService.getClueById(id);
        return Result.success(vo);
    }


    @PostMapping
    public Result save(@RequestBody ClueSaveDTO dto){
        clueService.add(dto);
        return Result.success();
    }
    @PutMapping
    public Result followUp(@RequestBody ClueUpdateByIdDTO dto){
        clueService.followUp(dto);
        return Result.success();
    }
    @PutMapping(path = "/assign/{clueId}/{userId}")
    public Result assign(@PathVariable Integer clueId, @PathVariable Integer userId){
        clueService.assign(clueId, userId);
        return Result.success();
    }
    @PutMapping(path = "/false/{id}")
    public Result handleFalse(@PathVariable Integer id, @RequestBody ClueFalseDTO dto){
        clueService.handleFalse(id, dto);
        return Result.success();
    }
    @PutMapping(path = "/toBusiness/{id}")
    public Result toBusiness(@PathVariable Integer id){
        clueService.toBusiness(id);
        return Result.success();
    }
}

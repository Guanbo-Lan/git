package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.entity.dto.BusinessListDTO;
import com.qk.entity.dto.BusinessPoolListDTO;
import com.qk.entity.dto.BusinessSaveDTO;
import com.qk.entity.dto.BusinessUpdateByIdDTO;
import com.qk.entity.vo.BusinessGetByIdVO;
import com.qk.entity.vo.BusinessListVO;
import com.qk.entity.vo.BusinessPoolListVO;
import com.qk.entity.vo.PageVO;
import com.qk.management.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author MrZhao
 * 商机管理Controller
 *
 */
@RestController
@SuppressWarnings("all")
@RequestMapping(path = "/businesses")
public class BusinessController {

    @Autowired
    private BusinessService businessService;


    @GetMapping
    public Result list(BusinessListDTO dto){
        PageVO<BusinessListVO> pageVO = businessService.getByPage(dto);
        return Result.success(pageVO);
    }
    @GetMapping(path = "/pool")
    public Result pool(BusinessPoolListDTO dto){
        PageVO<BusinessPoolListVO> pageVO = businessService.getByPool(dto);
        return Result.success(pageVO);
    }
    @GetMapping(path = "/{id}")
    public Result getById(@PathVariable Integer id){
        BusinessGetByIdVO vo = businessService.getBusinessById(id);
        return Result.success(vo);
    }


    @PostMapping
    public Result save(@RequestBody BusinessSaveDTO dto){
        businessService.add(dto);
        return Result.success();
    }
    @PostMapping(path = "/toCustomer/{id}")
    public Result toCustomer(@PathVariable Integer id){
        businessService.toCustomer(id);
        return Result.success();
    }
    @PutMapping
    public Result followUp(@RequestBody BusinessUpdateByIdDTO dto){
        businessService.followUp(dto);
        return Result.success();
    }
    @PutMapping(path = "/assign/{businessId}/{userId}")
    public Result assign(@PathVariable Integer businessId, @PathVariable Integer userId){
        businessService.assign(businessId, userId);
        return Result.success();
    }
    @PutMapping(path = "/back/{id}")
    public Result back(@PathVariable Integer id){
        businessService.back(id);
        return Result.success();
    }
}

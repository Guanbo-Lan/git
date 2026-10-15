package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.entity.dto.CustomerListDTO;
import com.qk.entity.dto.CustomerSaveDTO;
import com.qk.entity.dto.CustomerUpdateByIdDTO;
import com.qk.entity.vo.CustomerGetByIdVO;
import com.qk.entity.vo.CustomerListVO;
import com.qk.entity.vo.PageVO;
import com.qk.management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author MrZhao
 * 客户管理Controller
 *
 */
@RestController
@SuppressWarnings("all")
@RequestMapping(path = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping
    public Result list(CustomerListDTO dto){
        PageVO<CustomerListVO> pageVO = customerService.getByPage(dto);
        return Result.success(pageVO);
    }
    @GetMapping(path = "/{id}")
    public Result getById(@PathVariable Integer id){
        CustomerGetByIdVO vo = customerService.getCustomerById(id);
        return Result.success(vo);
    }


    @PostMapping
    public Result save(@RequestBody CustomerSaveDTO dto){
        customerService.add(dto);
        return Result.success();
    }
    @PutMapping
    public Result updateById(@RequestBody CustomerUpdateByIdDTO dto){
        customerService.updateCustomerById(dto);
        return Result.success();
    }
}

package com.qk.management.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.qk.entity.dto.ClueFalseDTO;
import com.qk.entity.dto.ClueListDTO;
import com.qk.entity.dto.CluePoolListDTO;
import com.qk.entity.dto.ClueSaveDTO;
import com.qk.entity.dto.ClueUpdateByIdDTO;
import com.qk.entity.po.Clue;
import com.qk.entity.vo.ClueGetByIdVO;
import com.qk.entity.vo.ClueListVO;
import com.qk.entity.vo.CluePoolListVO;
import com.qk.entity.vo.PageVO;


/**
 * @author MrZhao
 * 线索管理的业务层接口
 */
public interface ClueService extends IService<Clue> {
    /**
     *  分页条件查询
     * @param dto 分页条件
     * @return PageVO
     */
    PageVO<ClueListVO> getByPage(ClueListDTO dto);
    /**
     * 添加线索
     *
     * @param dto 线索信息
     */
    void add(ClueSaveDTO dto);

    /**
     * 为指定用户分配线索
     *
     * @param clueId 线索id
     * @param userId 用户id
     */
    void assign(Integer clueId, Integer userId);


    ClueGetByIdVO getClueById(Integer id);

    /**
     * 跟进线索
     *
     * @param dto 线索信息
     */
    void followUp(ClueUpdateByIdDTO dto);

    /**
     * 伪线索处理
     *
     * @param id 线索id
     * @param dto 伪线索信息
     */
    void handleFalse(Integer id, ClueFalseDTO dto);

    /**
     * 转商机处理
     *
     * @param id 线索id
     */
    void toBusiness(Integer id);

    /**
     * 线索池分页条件查询
     *
     * @param dto 分页条件
     * @return PageVO
     */
    PageVO<CluePoolListVO> getByPool(CluePoolListDTO dto);
}

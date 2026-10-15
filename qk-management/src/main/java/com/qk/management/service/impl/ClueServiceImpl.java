package com.qk.management.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.qk.common.exception.Throws;
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
import com.qk.management.service.ClueService;
import com.qk.management.mapper.ClueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.qk.common.enums.ExceptionCodeEnum.PARAM_INVALID;

/**
 * @author MrZhao
 * 线索管理业务层实现类
 */
@Service
@SuppressWarnings("all")
public class ClueServiceImpl extends ServiceImpl<ClueMapper, Clue>
        implements ClueService {

    private static final Integer STATUS_WAIT_ASSIGN = 1; // 待分配
    private static final Integer STATUS_FOLLOW_UP = 2;   // 跟进中
    private static final Integer STATUS_POOL = 3;        // 线索池
    private static final Integer STATUS_FALSE = 4;       // 伪线索
    private static final Integer STATUS_TO_BUSINESS = 5; // 已转商机

    @Autowired
    private ClueMapper clueMapper;

    @Override
    public PageVO<ClueListVO> getByPage(ClueListDTO dto) {
        // 1. 参数校验（省略）
        // 2. 核心业务处理 // 利用我们当前业务层继承 IService 接口中的方法，简化了对DAO操作！
        // 注：assignName 为联表字段，单表模式下暂不参与过滤
        Page<Clue> pageResult = this.page(PageDTO.of(dto.getPage(), dto.getPageSize()),
                Wrappers.lambdaQuery(Clue.class)
                        .eq(ObjectUtil.isNotNull(dto.getClueId()), Clue::getId, dto.getClueId())
                        .like(StrUtil.isNotBlank(dto.getPhone()), Clue::getPhone, dto.getPhone())
                        .eq(ObjectUtil.isNotNull(dto.getStatus()), Clue::getStatus, dto.getStatus())
                        .eq(ObjectUtil.isNotNull(dto.getChannel()), Clue::getChannel, dto.getChannel())
                        .orderByDesc(Clue::getUpdateTime));

        // 3. 数据处理和拼装
        // List<Clue> ---》List<ClueListVO>
        List<ClueListVO> voList = pageResult.getRecords().stream().map((clue) -> {
            // bean 拷贝
            ClueListVO vo = new ClueListVO();
            BeanUtil.copyProperties(clue, vo);
            return vo;
        }).toList();
        PageVO<ClueListVO> pageVO = new PageVO<>();
        pageVO.setRows(voList);
        pageVO.setTotal(pageResult.getTotal());
        return pageVO;
    }

    @Override
    public void add(ClueSaveDTO dto) {
        // 1. 根据手机号查询数据库，存在线索则不保存 数据幂等校验
        String phone = dto.getPhone();
        Clue clueDB = clueMapper.selectOne(Wrappers.lambdaQuery(Clue.class).eq(Clue::getPhone, phone));
        if (Objects.nonNull(clueDB)) {
            throw new RuntimeException("添加线索已经存在请勿重复添加");
        }
        // 赋值dto数据到clue
        Clue clue = new Clue();
        clue.setPhone(dto.getPhone());
        clue.setChannel(dto.getChannel());
        clue.setActivityId(dto.getActivityId());
        clue.setName(dto.getName());
        clue.setGender(dto.getGender());
        clue.setAge(dto.getAge());
        clue.setWechat(dto.getWechat());
        clue.setQq(dto.getQq());
        // 数据补全
        clue.setStatus(STATUS_WAIT_ASSIGN);
        clue.setCreateTime(LocalDateTime.now());
        clue.setUpdateTime(LocalDateTime.now());
        // 2. 保存线索
        clueMapper.insert(clue);
    }

    @Override
    public void assign(Integer clueId, Integer userId) {
        // 业务分析能力
        Clue clue = this.getById(clueId);
        // 如果线索不存在则提示参数非法
        if (ObjectUtil.isNull(clue)){
            Throws.throwE(PARAM_INVALID);
        }
        // 分配归属人，并置为跟进中状态
        clue.setUserId(userId);
        clue.setStatus(STATUS_FOLLOW_UP);
        clue.setUpdateTime(LocalDateTime.now());
        this.saveOrUpdate(clue);
    }

    @Override
    public ClueGetByIdVO getClueById(Integer id) {
        Clue clue = this.getById(id);
        if (ObjectUtil.isNull(clue)){
            Throws.throwE(PARAM_INVALID);
        }
        ClueGetByIdVO vo = new ClueGetByIdVO();
        BeanUtil.copyProperties(clue,vo);
        // 注：trackRecords 跟进记录为联表数据，单表模式下未填充
        return vo;
    }

    @Override
    public void followUp(ClueUpdateByIdDTO dto) {
        // 参数校验
        if (ObjectUtil.isNull(dto.getId()) ||
                StrUtil.isBlank(dto.getName())) {
            Throws.throwE(PARAM_INVALID);
        }
        // 数据拷贝（dto 中的 record 跟进内容，线索表无对应字段，BeanUtil 拷贝时自动忽略）
        Clue clue = BeanUtil.copyProperties(dto, Clue.class);
        // 补全更新时间
        clue.setUpdateTime(LocalDateTime.now());
        // 更新
        this.saveOrUpdate(clue);
    }

    @Override
    public void handleFalse(Integer id, ClueFalseDTO dto) {
        // 业务分析能力
        Clue clue = this.getById(id);
        // 如果线索不存在则提示参数非法
        if (ObjectUtil.isNull(clue)){
            Throws.throwE(PARAM_INVALID);
        }
        // 标记为伪线索（reason、remark 属于跟进记录，线索表无对应字段）
        clue.setStatus(STATUS_FALSE);
        clue.setUpdateTime(LocalDateTime.now());
        this.saveOrUpdate(clue);
    }

    @Override
    public void toBusiness(Integer id) {
        // 业务分析能力
        Clue clue = this.getById(id);
        // 如果线索不存在则提示参数非法
        if (ObjectUtil.isNull(clue)){
            Throws.throwE(PARAM_INVALID);
        }
        // 转为商机
        clue.setStatus(STATUS_TO_BUSINESS);
        clue.setUpdateTime(LocalDateTime.now());
        this.saveOrUpdate(clue);
    }

    @Override
    public PageVO<CluePoolListVO> getByPool(CluePoolListDTO dto) {
        // 1. 参数校验（省略）
        // 2. 核心业务处理 // 线索池即状态为「线索池」的线索
        Page<Clue> pageResult = this.page(PageDTO.of(dto.getPage(), dto.getPageSize()),
                Wrappers.lambdaQuery(Clue.class)
                        .eq(Clue::getStatus, STATUS_POOL)
                        .eq(ObjectUtil.isNotNull(dto.getClueId()), Clue::getId, dto.getClueId())
                        .like(StrUtil.isNotBlank(dto.getPhone()), Clue::getPhone, dto.getPhone())
                        .eq(ObjectUtil.isNotNull(dto.getChannel()), Clue::getChannel, dto.getChannel())
                        .orderByDesc(Clue::getUpdateTime));

        // 3. 数据处理和拼装
        // List<Clue> ---》List<CluePoolListVO>
        List<CluePoolListVO> voList = pageResult.getRecords().stream().map((clue) -> {
            // bean 拷贝
            CluePoolListVO vo = new CluePoolListVO();
            BeanUtil.copyProperties(clue, vo);
            return vo;
        }).toList();
        PageVO<CluePoolListVO> pageVO = new PageVO<>();
        pageVO.setRows(voList);
        pageVO.setTotal(pageResult.getTotal());
        return pageVO;
    }
}

package com.qk.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
//import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 *
 * @author MrZhao
 *
 */
@Data
public class DeptListVO {
    private Integer id;
    private String name;
    private Integer status;
    // 处理
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss") // 解决所有问题！
   // @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")  //默认转换器只能解决前端传递参数封装到后端的数据格式问题
    private LocalDateTime updateTime;
}

package com.qk.common.handler;

import com.qk.common.Result;
import com.qk.common.exception.QkException;
import com.qk.common.message.AdviceMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author MrZhao
 *  轻客管家全局异常处理器类！
 *  // 标记当前类是一个控制器增强类！
 */
@Slf4j
@RestControllerAdvice
public class QkExceptionHandler {

    @Autowired
    private AdviceMessage adviceMessage;

    // 当前方法专门处理 QkException 自定义异常
    @ExceptionHandler(exception = QkException.class)
    public Result handleQkException(QkException e){
        // 记录详细错误日志
        log.warn("出现业务异常，错误码：{}，异常信息：{}", e.getCode(), e.getMessage());
        return Result.error(e.getMessage());
    }

    // 当前方法 兜底所有异常！
    @ExceptionHandler(exception = Throwable.class)
    public Result handleException(Throwable e){
        // 记录详细错误日志（含堆栈）
        log.error("出现系统异常", e);
        // 发送告警信息给运维人员/开发（异步）
        adviceMessage.msg();
        return Result.error("系统繁忙，请稍后重试");
    }

}

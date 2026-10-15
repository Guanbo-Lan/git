package com.qk.common.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 *
 * @author MrZhao
 *
 *  定义统计接口耗时切面
 *
 */
@Aspect
@Component
public class CostTimeAspect {
    @Pointcut(value = "@annotation(com.qk.common.aop.anno.CostTime)")
    public void pj(){}

    // 定义通知
    @Around(value = "pj()")
    public Object costTime(ProceedingJoinPoint pjp) throws Throwable {

        long startTime = System.currentTimeMillis();

        Object value = pjp.proceed(); // 执行原始方法

        long endTime =System.currentTimeMillis();
        System.out.println("-------接口执行时间是"+(endTime-startTime)/1000+"秒");

        return value;
    }
}

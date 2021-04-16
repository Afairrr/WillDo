package com.afair.auth.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author WangBing
 * @date 2021/4/16 14:41
 */
@Aspect
@Component
@Slf4j
public class LogAopAspect {
    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 结束时间
     */
    private Long endTime;

    public LogAopAspect() {
    }

    @Pointcut("execution(* com.afair.auth.controller.*.*(..))")
    public void logAopAspect(){

    }

    @Before(value = "logAopAspect()")
    public void methodBefore(JoinPoint joinPoint){
        // 获取当前的HttpServletRequest对象
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 打印请求的内容
        startTime = System.currentTimeMillis();
        log.info("请求开始时间：{}", LocalDateTime.now());
        log.info("请求Url : {}", request.getRequestURL().toString());
        log.info("请求方式 : {}", request.getMethod());
        log.info("请求ip : {}", request.getRemoteAddr());
        log.info("请求内容类型 : {}", request.getContentType());
        log.info("请求参数 : {}", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret",pointcut = "logAopAspect()")
    public void doAfterReturning(Object ret){
        endTime=System.currentTimeMillis();
        log.info("请求结束时间 : {}", LocalDateTime.now());
        log.info("请求耗时 : {}ms", (endTime - startTime));
        // 处理完请求，返回内容
        log.info("请求返回 : {}", ret);
    }

    @AfterThrowing(pointcut = "logAopAspect()",throwing = "throwable")
    public void doAfterThrowing(Throwable throwable) {
        // 保存异常日志记录
        log.error("发生异常时间 : {}", LocalDateTime.now());
        log.error("抛出异常 : {}", throwable.getMessage());
    }
}

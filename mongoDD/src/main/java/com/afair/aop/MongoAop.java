package com.afair.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author WangBing
 * @date 2020/12/18 16:31
 */
@Aspect
@Component
@Slf4j
public class MongoAop {
    @Pointcut("execution(public * com.afair.*.*.*(..))")
    public void logAspect() {
    }

    @Around("logAspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        log.info("start exec{}", point.getSignature().toString());
        Object object = point.proceed();
        long end = System.currentTimeMillis();
        log.info("finished exec......{},exec cost{}ms", point.getSignature().toString(), end - start);
        return object;
    }
}


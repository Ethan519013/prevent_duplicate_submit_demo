package com.ethan.aspect;

import com.alibaba.fastjson.JSON;
import com.ethan.annotation.PreventDuplicateSubmit;
import com.ethan.utils.RedisCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author Johnson
 */
@Component
@Aspect
public class PreventDuplicateSubmitAspect {

    @Value("${prevent-duplicate-submit-cache}")
    private String CACHE_NAME;

    @Autowired
    private RedisCache redisCache;

    /**
     * 开启日志
     */
    private static final Logger logger = LoggerFactory.getLogger(PreventDuplicateSubmitAspect.class);

    /**
     * 定义切入点
     */
    @Pointcut("@annotation(com.ethan.annotation.PreventDuplicateSubmit)")
    public void pointcut(){};

    @Around("pointcut()")
    public Object checkRequest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // 获取请求参数
        String args = JSON.toJSONString(proceedingJoinPoint.getArgs());
        // 获取请求 URI
        String uri = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRequestURI();
        // 指定缓存的 key
        String cacheKey = CACHE_NAME + "_" + uri + args;
        // 获取方法
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        // 获取注解
        PreventDuplicateSubmit methodAnnotation = method.getAnnotation(PreventDuplicateSubmit.class);
        // 判断是否存在缓存, 如果存在则为重复提交, 否则添加到缓存
        Boolean isDuplicateSubmit = redisCache.isCaught(cacheKey, methodAnnotation.interval(), TimeUnit.SECONDS);
        // isDuplicateSubmit 为true则证明没有重复提交
        if(isDuplicateSubmit){
            logger.info("阻止了一次重复提交");
            throw  new RuntimeException("请勿重复提交");
        }
        return proceedingJoinPoint.proceed();
    }
}

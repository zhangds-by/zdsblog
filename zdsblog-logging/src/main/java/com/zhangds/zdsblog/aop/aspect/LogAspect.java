package com.zhangds.zdsblog.aop.aspect;

import com.zhangds.zdsblog.common.model.entity.Log;
import com.zhangds.zdsblog.aop.service.LogService;
import com.zhangds.zdsblog.common.model.enums.LogType;
import com.zhangds.zdsblog.common.utils.ServletUtils;
import com.zhangds.zdsblog.common.utils.ip.AddressUtils;
import com.zhangds.zdsblog.common.utils.ip.IpUtils;
import com.zhangds.zdsblog.security.utils.SecurityUtil;
import io.netty.util.internal.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * @Author zhangds
 * @Date 2020/3/12 12:49
 * @Return
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private LogService logService;

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.zhangds.zdsblog.aop.annotation.Log)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /*@AfterReturning(pointcut = "logPointcut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }*/

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        currentTime.set(System.currentTimeMillis());
        result = joinPoint.proceed();
        Log log = new Log(LogType.INFO, System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        HttpServletRequest request = ServletUtils.getRequest();
        //保存操作日志信息
        logService.save(getUsername(), AddressUtils.getBrowser(request), IpUtils.getHostIp(), joinPoint, log);
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e         exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        Log log = new Log(LogType.ERROR, System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        log.setExceptionDetail(ThrowableUtil.stackTraceToString(e).getBytes());
        HttpServletRequest request = ServletUtils.getRequest();
        logService.save(getUsername(), AddressUtils.getBrowser(request), IpUtils.getHostIp(), (ProceedingJoinPoint) joinPoint, log);
    }

    public String getUsername() {
        try {
            return SecurityUtil.getUserName();
        } catch (Exception e) {
            return "";
        }
    }
}

package com.west.log.aspect;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.west.comm.util.HttpUtils;
import com.west.comm.util.SpringUtils;
import com.west.log.annotation.SysLog;
import com.west.log.domain.enums.LogTypeEnum;
import com.west.log.event.SysLogEvent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 系统日志切面
 *
 * @author west
 * @date 2021/2/8 11:22
 */
@Component
@Aspect
@Slf4j
public class SysLogAspect {

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param point join point for advice
     */
    @Around("@annotation(sysLog)")
    public Object logAround(ProceedingJoinPoint point, com.west.log.annotation.SysLog sysLog) throws Throwable {
        Object result = null;
        currentTime.set(System.currentTimeMillis());

        com.west.log.domain.entity.SysLog logVo = new com.west.log.domain.entity.SysLog();
        logVo.setTitle(sysLog.value());
        HttpServletRequest request = HttpUtils.getHttpServletRequest();
        logVo.setLogId(IdUtil.fastSimpleUUID());
//        sysLog.setCreateBy(SecurityUtils.getCurrentUsername());
        logVo.setCreateBy("admin");
        logVo.setCreateTime(LocalDateTime.now());
        logVo.setLogType(LogTypeEnum.NORMAL.getType());
        logVo.setAddress(ServletUtil.getClientIP(request));
        logVo.setRequestIp(URLUtil.getPath(request.getRequestURI()));
        logVo.setMethod(request.getMethod());
        logVo.setBrowser(request.getHeader("user-agent"));
        logVo.setParams(HttpUtils.toParams(request.getParameterMap()));

        // 请求内容
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        SysLog aopLog = method.getAnnotation(SysLog.class);

        // 方法路径
        String methodName = point.getTarget().getClass().getName() + "." + signature.getName() + "()";

        // 请求参数
        StringBuilder params = new StringBuilder("{");
        //参数值
        Object[] argValues = point.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature) point.getSignature()).getParameterNames();
        if (argValues != null) {
            for (int i = 0; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        }
        params.append("}");

        try {
            System.out.println("2.当前线程ID：" + Thread.currentThread().getId());
            result = point.proceed();
        } catch (Exception e) {
            logVo.setLogType(LogTypeEnum.ERROR.getType());
            logVo.setException(e.getMessage());
            throw e;
        } finally {
            System.out.println("3.当前线程ID：" + Thread.currentThread().getId());
            logVo.setTime(System.currentTimeMillis() - currentTime.get());
            currentTime.remove();
            SpringUtils.publishEvent(new SysLogEvent(logVo));
        }

        return result;
    }
}

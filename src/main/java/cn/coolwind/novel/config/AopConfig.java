package cn.coolwind.novel.config;

import cn.coolwind.novel.entity.SysAccessLog;
import cn.coolwind.novel.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Aspect
@Component
public class AopConfig {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysLogService sysLogService;

    @Pointcut("execution(public * cn.coolwind.novel.controller.*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void beforeAccess(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        StringBuffer url = request.getRequestURL();
        String ip = request.getRemoteAddr();
        SysAccessLog sysAccessLog = new SysAccessLog();
        sysAccessLog.setIp(ip);
        sysAccessLog.setUrl(url.toString());
        sysAccessLog.setLastTime(new Date());
        sysLogService.saveAccessLog(sysAccessLog);
        log.info("-----------------"+ip+"访问："+url);
    }
}

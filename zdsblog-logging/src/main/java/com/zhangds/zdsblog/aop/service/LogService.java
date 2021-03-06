package com.zhangds.zdsblog.aop.service;

import com.zhangds.zdsblog.aop.dto.LogQueryCriteria;
import com.zhangds.zdsblog.common.model.entity.Log;
import com.zhangds.zdsblog.common.service.base.BaseService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Zheng Jie
 * @date 2018-11-24
 */
public interface LogService extends BaseService<Log, Long> {

    /**
     * 查询全部数据
     * @param criteria 查询条件
     * @return /
     */
    List<Log> queryAll(LogQueryCriteria criteria);

    /**
     * 保存日志数据
     * @param username 用户
     * @param browser 浏览器
     * @param ip 请求IP
     * @param joinPoint /
     * @param log 日志实体
     */
    @Async
    void save(String username, String browser, String ip, ProceedingJoinPoint joinPoint, Log log);

    /**
     * 查询异常详情
     * @param id 日志ID
     * @return Object
     */
    Object findByErrDetail(Long id);

    /**
     * 导出日志
     * @param logs 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<Log> logs, HttpServletResponse response) throws IOException;


    /**
     * 根据日志类型清空日志
     * @Author zhangds
     * @Date 2020/3/12 14:59
     * @Return
     */
    void clearLog(String logType);
}

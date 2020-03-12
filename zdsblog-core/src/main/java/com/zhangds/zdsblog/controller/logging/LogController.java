package com.zhangds.zdsblog.controller.logging;

import cn.hutool.core.util.EnumUtil;
import com.zhangds.zdsblog.aop.annotation.Log;
import com.zhangds.zdsblog.aop.dto.LogQueryCriteria;
import com.zhangds.zdsblog.aop.service.LogService;
import com.zhangds.zdsblog.common.exception.GlobalRequestException;
import com.zhangds.zdsblog.common.model.enums.LogType;
import com.zhangds.zdsblog.common.model.support.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.enums.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author zhangds
 * @Date 2020/3/12 12:50
 * @Return
 */
@RestController
@RequestMapping("logs")
@Api(tags = "日志管理")
public class LogController {

    @Autowired
    private LogService logService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping("/download")
    public BaseResponse download(HttpServletResponse response, LogQueryCriteria criteria){
        criteria.setLogType(LogType.INFO);
        try {
            logService.download(logService.queryAll(criteria), response);
        } catch (IOException e) {
            throw new GlobalRequestException("导出日志数据失败");
        }
        return BaseResponse.ok();
    }


    @Log("导出错误数据")
    @ApiOperation("导出错误数据")
    @GetMapping("/downloadError")
    public BaseResponse downloadError(HttpServletResponse response, LogQueryCriteria criteria){
        criteria.setLogType(LogType.ERROR);
        try {
            logService.download(logService.queryAll(criteria), response);
        } catch (IOException e) {
            throw new GlobalRequestException("导出错误日志数据失败");
        }
        return BaseResponse.ok();
    }

    @Log("分页查询操作日志")
    @ApiOperation("分页查询操作日志")
    @GetMapping("/getPage")
    public BaseResponse getPage(LogQueryCriteria criteria, Pageable pageable){
        return BaseResponse.ok(logService.getPage(criteria, pageable));
    }

    @Log("日志异常详情查询")
    @ApiOperation("日志异常详情查询")
    @GetMapping("/getErrorLog/{id:\\d+}")
    public BaseResponse getErrorLog(@PathVariable Long id){
        return BaseResponse.ok(logService.findByErrDetail(id));
    }

    @Log("根据日志类型清空日志")
    @ApiOperation("根据日志类型清空日志")
    @GetMapping("/clearLog/{logType}")
    public BaseResponse clearLog(@PathVariable String logType){
        logService.clearLog(logType);
        return BaseResponse.ok();
    }
}

package com.zhangds.zdsblog.aop.dto;

import com.zhangds.zdsblog.common.annotation.Query;
import com.zhangds.zdsblog.common.model.dto.BaseQueryCriteria;
import com.zhangds.zdsblog.common.model.enums.LogType;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * 日志查询类
 * @author Zheng Jie
 * @date 2019-6-4 09:23:07
 */
@Data
public class LogQueryCriteria extends BaseQueryCriteria {

    @Query(blurry = "username,description,address,requestIp,method,params")
    private String blurry;

    @Query
    private LogType logType;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}

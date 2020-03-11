package com.zhangds.zdsblog.common.model.dto;

import com.zhangds.zdsblog.common.annotation.Query;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 通用查询条件
 * Create by zhangds
 * 2020-03-09 10:10
 **/
@Data
public class BaseQueryCriteria implements Serializable {

    @Query
    private Long id;

    @Query(blurry = "name")
    private String blurry;

    @Query(type = Query.Type.LEFT_LIKE)
    private String type;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}

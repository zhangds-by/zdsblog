package com.zhangds.zdsblog.common.model.dto;

import com.zhangds.zdsblog.common.annotation.Query;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * 用户查询条件
 * Create by zhangds
 * 2020-03-09 10:10
 **/
@Data
public class AttachmentQueryCriteria implements Serializable {

    @Query
    private Long id;

    @Query(blurry = "name")
    private String blurry;

    @Query(type = Query.Type.LEFT_LIKE)
    private String type;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}

package com.zhangds.zdsblog.common.model.dto;

import com.zhangds.zdsblog.common.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * 菜单查询条件
 * Create by zhangds
 * 2020-03-09 10:10
 **/
@Data
public class MenuQueryCriteria extends BaseQueryCriteria {

    @Query
    private Long id;

    @Query
    private Long parentId;

    /*@Query(propName = "id", type = Query.Type.IN, joinName = "roleId")
    private Set<Long> roleIds;*/

    @Query(blurry = "email,userName,nickName")
    private String blurry;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}

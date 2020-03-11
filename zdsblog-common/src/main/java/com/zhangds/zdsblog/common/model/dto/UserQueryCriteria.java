package com.zhangds.zdsblog.common.model.dto;

import com.zhangds.zdsblog.common.annotation.Query;
import lombok.Data;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;

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
public class UserQueryCriteria extends BaseQueryCriteria {

    @Query
    private Long id;

    /*@Query(propName = "id", type = Query.Type.IN, joinName = "roleId")
    private Set<Long> roleIds;*/

    @Query(blurry = "email,userName,nickName")
    private String blurry;

    @Query
    private Boolean enabled;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}

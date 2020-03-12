package com.zhangds.generator.repository;

import com.zhangds.generator.domain.GenConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author zhangds
 * @Date 2020/3/12 15:17
 * @Return 
 */
@Repository
public interface GenConfigRepository extends JpaRepository<GenConfig, Long> {

    /**
     * 查询表配置
     * @param tableName 表名
     * @return /
     */
    GenConfig findByTableName(String tableName);
}

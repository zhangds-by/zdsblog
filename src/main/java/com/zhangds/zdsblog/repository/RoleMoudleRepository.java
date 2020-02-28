package com.zhangds.zdsblog.repository;

import com.zhangds.zdsblog.model.entity.RoleMoudle;
import com.zhangds.zdsblog.model.entity.RoleMoudleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by zhangds
 * 2020-02-26 11:44
 **/
@Repository
public interface RoleMoudleRepository extends JpaRepository<RoleMoudle, RoleMoudleId> {
}

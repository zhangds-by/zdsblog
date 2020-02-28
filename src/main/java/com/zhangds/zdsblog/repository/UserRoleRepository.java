package com.zhangds.zdsblog.repository;

import com.zhangds.zdsblog.model.entity.UserRole;
import com.zhangds.zdsblog.model.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by zhangds
 * 2020-02-26 11:44
 **/
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}

package com.zhangds.zdsblog.common.repository;

import com.zhangds.zdsblog.common.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Create by zhangds
 * 2020-02-26 10:45
 **/
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor {

    @Query(nativeQuery = true, value = "SELECT r.* from sys_role r "
            + "left join sys_user_role ur on r.role_id = ur.role_id "
            + "left join sys_user u on u.user_id = ur.user_id where u.user_id = ?")
    List<Role> getRoleByUserId(@Param("userId") Long userId);
}

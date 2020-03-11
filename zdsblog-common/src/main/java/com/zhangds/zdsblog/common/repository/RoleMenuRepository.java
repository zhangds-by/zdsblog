package com.zhangds.zdsblog.common.repository;

import com.zhangds.zdsblog.common.model.entity.RoleMenu;
import com.zhangds.zdsblog.common.model.entity.RoleMenuId;
import com.zhangds.zdsblog.common.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Create by zhangds
 * 2020-03-11 17:07
 **/
public interface RoleMenuRepository extends BaseRepository<RoleMenu, RoleMenuId>, JpaSpecificationExecutor {

    boolean existsByMenuId(Long menuId);
}

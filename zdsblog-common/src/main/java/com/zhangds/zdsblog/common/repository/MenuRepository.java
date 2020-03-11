package com.zhangds.zdsblog.common.repository;

import com.zhangds.zdsblog.common.model.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by zhangds
 * 2020-03-10 17:32
 **/
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>, JpaSpecificationExecutor {

    @Query(nativeQuery = true, value = "SELECT distinct m.* from sys_menu m "
            + "left join sys_role_menu rm on m.menu_id = rm.menu_id "
            + "left join sys_user_role ur on rm.role_id = ur.role_id "
            + "left join sys_user u on u.user_id = ur.user_id where u.user_id = ?1 and  if(?2 is not null && ?2 !='', m.parent_id=?2, 1=1)")
    List<Menu> findListByUserId(Long userId, Long parentId);

    boolean existsByParentId(Long parentId);

    List<Menu> findAllByMenuId(Long menuId);

    List<Menu> findAllByParentId(Long parentId);

}

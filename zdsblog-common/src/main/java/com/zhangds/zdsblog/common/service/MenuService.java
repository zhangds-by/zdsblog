package com.zhangds.zdsblog.common.service;

import com.zhangds.zdsblog.common.model.dto.MenuDTO;
import com.zhangds.zdsblog.common.model.entity.Menu;
import com.zhangds.zdsblog.common.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * Create by zhangds
 * 2020-03-10 17:11
 **/
public interface MenuService extends BaseService<Menu, Long> {

    List<Menu> getListByUserId(Long userId, Long parentId);

    boolean checkMenuExistRole(Long menuId);

    boolean hasChild(Long menuId);

    List<Menu> getListByMenuId(Long menuId);

    List<Menu> getListByParentId(Long parentId);

    List<Map<String, Object>> getMenuTree(List<Menu> list);

    Object buildTree(List<MenuDTO> menuDTOList);
}

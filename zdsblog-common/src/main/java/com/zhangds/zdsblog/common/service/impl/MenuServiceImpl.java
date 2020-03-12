package com.zhangds.zdsblog.common.service.impl;

import com.zhangds.zdsblog.common.model.dto.MenuDTO;
import com.zhangds.zdsblog.common.model.entity.Menu;
import com.zhangds.zdsblog.common.model.interfaces.mapper.MenuMapper;
import com.zhangds.zdsblog.common.repository.MenuRepository;
import com.zhangds.zdsblog.common.repository.RoleMenuRepository;
import com.zhangds.zdsblog.common.service.MenuService;
import com.zhangds.zdsblog.common.service.base.AbstractBaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Create by zhangds
 * 2020-03-10 17:13
 **/
@Service
public class MenuServiceImpl extends AbstractBaseService<Menu, Long> implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RoleMenuRepository roleMenuRepository;

    @Override
    public List<Menu> getListByUserId(Long userId, Long parentId) {
        return menuRepository.findListByUserId(userId, parentId);
    }

    @Override
    public boolean checkMenuExistRole(Long menuId) {
        return roleMenuRepository.existsByMenuId(menuId);
    }

    @Override
    public boolean hasChild(Long menuId) {
        return menuRepository.existsByParentId(menuId);
    }

    @Override
    public List<Menu> getListByMenuId(Long menuId) {
        return menuRepository.findAllByMenuId(menuId);
    }

    @Override
    public List<Menu> getListByParentId(Long parentId) {
        return menuRepository.findAllByMenuId(parentId);
    }

    public List<Map<String, Object>> getMenuTree(List<Menu> menus) {
        List<Map<String,Object>> list = new LinkedList<>();
        menus.forEach(menu -> {
                    if (menu!=null){
                        List<Menu> menuList = menuRepository.findAllByParentId(menu.getMenuId());
                        Map<String,Object> map = new HashMap<>(16);
                        map.put("id",menu.getMenuId());
                        map.put("label",menu.getMenuName());
                        if(menuList!=null && menuList.size()!=0){
                            map.put("children",getMenuTree(menuList));
                        }
                        list.add(map);
                    }
                }
        );
        return list;
    }

    @Override
    public Object buildTree(List<MenuDTO> dtoList) {
        List<MenuDTO> trees = new ArrayList<>();
        Set<Long> ids = new HashSet<>();
        for (MenuDTO dto : dtoList) {
            if (dto.getParentId() == 0) {
                trees.add(dto);
            }
            for (MenuDTO it : dtoList) {
                if (it.getParentId().equals(dto.getMenuId())) {
                    if (dto.getChildren() == null) {
                        dto.setChildren(new ArrayList<>());
                    }
                    dto.getChildren().add(it);
                    ids.add(it.getMenuId());
                }
            }
        }
        Map<String,Object> map = new HashMap<>(2);
        if(trees.size() == 0){
            trees = dtoList.stream().filter(s -> !ids.contains(s.getMenuId())).collect(Collectors.toList());
        }
        map.put("content",trees);
        map.put("totalElements", dtoList.size());
        return map;
    }
}

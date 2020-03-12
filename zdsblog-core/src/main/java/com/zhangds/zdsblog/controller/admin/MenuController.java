package com.zhangds.zdsblog.controller.admin;

import com.zhangds.zdsblog.common.model.dto.MenuDTO;
import com.zhangds.zdsblog.common.model.dto.MenuQueryCriteria;
import com.zhangds.zdsblog.common.model.entity.Menu;
import com.zhangds.zdsblog.common.model.entity.User;
import com.zhangds.zdsblog.common.model.interfaces.mapper.MenuMapper;
import com.zhangds.zdsblog.common.model.support.BaseResponse;
import com.zhangds.zdsblog.common.service.MenuService;
import com.zhangds.zdsblog.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Create by zhangds
 * 2020-03-10 17:02
 **/
@Api(tags = "菜单管理")
@RestController
@RequestMapping("menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuMapper menuMapper;

    @ApiOperation("获取用户拥有的菜单权限")
    @GetMapping("/getMenuByUserId/{userId:\\d+}")
    public BaseResponse getMenuByUserId(@PathVariable Long userId){
        List<Menu> menuList = menuService.getListByUserId(userId, null);
        return BaseResponse.ok(menuList);
    }

    @ApiOperation("分页获取菜单")
    @GetMapping("/getPage")
    public BaseResponse getPage(MenuQueryCriteria queryCriteria, Pageable pageable){
        Page<Menu> page = menuService.getPage(queryCriteria, pageable);
        return BaseResponse.ok(page);
    }

    @ApiOperation("新增菜单")
    @PostMapping("/save")
    public BaseResponse save(@RequestBody Menu menu){
        Menu res = menuService.save(menu);
        return BaseResponse.ok(res);
    }

    @ApiOperation("修改菜单")
    @PostMapping("/update")
    public BaseResponse update(@RequestBody Menu menu){
        Menu res = menuService.update(menu);
        return BaseResponse.ok(res);
    }

    @ApiOperation("删除菜单")
    @PostMapping("/delete/{menuId:\\d+}")
    public BaseResponse delete(@PathVariable Long menuId){
        if (menuService.checkMenuExistRole(menuId)){
            return BaseResponse.fail("菜单已绑定了角色，不允许删除");
        }
        if (menuService.hasChild(menuId)){
            return BaseResponse.fail("此菜单拥有子菜单，不允许删除");
        }
        menuService.deleteById(menuId);
        return BaseResponse.ok();
    }

    @ApiOperation("获取非组件菜单树")
    @GetMapping("/getMenuTree")
    public BaseResponse getMenuTree(){
        List<Menu> menuList = menuService.getListByParentId(0L);
        List<Map<String, Object>> menuTree = menuService.getMenuTree(menuList);
        return BaseResponse.ok(menuTree);
    }

    @ApiOperation("获取所有菜单树(分配菜单权限使用)")
    @GetMapping("/buildTree")
    public BaseResponse buildTree(){
        List<Menu> menuList = menuService.getListByParentId(0L);
        List<MenuDTO> menuDTOList = menuList.stream().map(menuMapper::toDto).collect(Collectors.toList());
        return BaseResponse.ok(menuService.buildTree(menuDTOList));
    }

    @ApiOperation("获取用户拥有菜单树(修改分配菜单权限使用)")
    @GetMapping("/getMenus")
    public BaseResponse getMenus(){
        User user = super.getCurrentUser();
        List<Menu> menuList = menuService.getListByUserId(user.getUserId(), null);
        List<MenuDTO> menuDTOList = menuList.stream().map(menuMapper::toDto).collect(Collectors.toList());
        return BaseResponse.ok(menuService.buildTree(menuDTOList));
    }

}

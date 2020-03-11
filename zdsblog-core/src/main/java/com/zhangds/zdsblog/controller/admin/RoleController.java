package com.zhangds.zdsblog.controller.admin;

import com.zhangds.zdsblog.common.model.entity.Role;
import com.zhangds.zdsblog.common.model.support.BaseResponse;
import com.zhangds.zdsblog.common.service.RoleService;
import com.zhangds.zdsblog.common.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create by zhangds
 * 2020-02-27 18:46
 **/
@RestController
@RequestMapping("role")
@Api(tags = "RoleController", value = "角色管理")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @ApiOperation("新增角色")
    @PostMapping("/save")
    public BaseResponse<Role> save(@RequestBody Role role){
        Role res = roleService.save(role);
        return BaseResponse.ok(res);
    }

    @ApiOperation("修改角色")
    @PostMapping("/update")
    public BaseResponse<Role> update(@RequestBody Role role){
        Role res = roleService.update(role);
        return BaseResponse.ok(res);
    }

    @ApiOperation("获取用户的角色信息")
    @GetMapping("/getRoleList/{userId}")
    public BaseResponse<List> getRoleList(@PathVariable Long userId){
        List<Role> list = userService.getRoleByUserId(userId);
        return BaseResponse.ok(list);
    }

    @ApiOperation("根据id获取角色")
    @GetMapping("/getRoleById/{roleId:\\d+}")
    public BaseResponse<Role> getRoleById(@PathVariable Long roleId){
        Role role = roleService.getRoleById(roleId);
        return BaseResponse.ok(role);
    }
}

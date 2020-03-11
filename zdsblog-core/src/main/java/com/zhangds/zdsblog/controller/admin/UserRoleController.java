package com.zhangds.zdsblog.controller.admin;

import com.zhangds.zdsblog.common.model.entity.UserRole;
import com.zhangds.zdsblog.common.model.entity.UserRoleId;
import com.zhangds.zdsblog.common.model.support.BaseResponse;
import com.zhangds.zdsblog.common.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Create by zhangds
 * 2020-02-27 18:46
 **/
@RestController
@RequestMapping("userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/save")
    public BaseResponse<UserRole> save(@RequestBody UserRole userRole){
        UserRole res = userRoleService.save(userRole);
        return BaseResponse.ok(res);
    }

    @PostMapping("/getUserRoleById")
    public BaseResponse<UserRole> getUserRoleById(@RequestBody UserRoleId id){
        UserRole userRole = userRoleService.getByIds(id);
        return BaseResponse.ok(userRole);
    }
}

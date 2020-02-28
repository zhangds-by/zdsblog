package com.zhangds.zdsblog.controller;

import com.google.common.collect.Maps;
import com.zhangds.zdsblog.model.entity.Role;
import com.zhangds.zdsblog.model.entity.User;
import com.zhangds.zdsblog.model.param.LoginParam;
import com.zhangds.zdsblog.model.param.UserParam;
import com.zhangds.zdsblog.model.support.BaseResponse;
import com.zhangds.zdsblog.service.AdminService;
import com.zhangds.zdsblog.service.IndexService;
import com.zhangds.zdsblog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Create by zhangds
 * 2020-02-17 14:02
 **/
@Api(tags = "IndexController", value = "首页索引")
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private IndexService indexService;

    @ApiOperation(value = "登录", tags = "/index/login")
    @PostMapping("/login")
    public BaseResponse<Map> login(@RequestBody LoginParam param){

        //SelfUserEntity userDetails = (SelfUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = indexService.login(param.getUsername(), param.getPassword());
        Map<String, Object> resMap = Maps.newHashMap();
        resMap.put("token", token);
        return BaseResponse.ok(resMap);
    }

    @ApiOperation(tags = "/index/register", value = "注册")
    @PostMapping("/register")
    public BaseResponse register(@RequestBody User user){
        indexService.register(user);
        return BaseResponse.ok(null);
    }

    @GetMapping("/getAllMoudle/{username}")
    public BaseResponse<List> getAllMoudle(@PathVariable String username){
        return BaseResponse.ok(null);
    }





    @GetMapping("/getList")
    public BaseResponse<List> getList(){
        List<User> list = adminService.getList();
        return BaseResponse.ok(list);
    }

    @GetMapping("/getRoleList/{userId}")
    public BaseResponse<List> getRoleList(@PathVariable Long userId){
        List<Role> list = userService.getRoleByUserId(userId);
        return BaseResponse.ok(list);
    }

    @PostMapping("/getPage")
    public BaseResponse getPage(@RequestBody UserParam param){
        Page<User> userPage = userService.getPage(param.getUsername(), param.getPageIndex(), param.getPageSize());
        List<User> list = userPage.getContent();
        return BaseResponse.ok(list);
    }
}

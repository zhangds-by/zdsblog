package com.zhangds.zdsblog.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.zhangds.zdsblog.common.model.constants.SysContants;
import com.zhangds.zdsblog.common.model.dto.UserLoginDTO;
import com.zhangds.zdsblog.common.model.entity.Role;
import com.zhangds.zdsblog.common.model.entity.User;
import com.zhangds.zdsblog.common.model.param.LoginParam;
import com.zhangds.zdsblog.common.model.param.UserParam;
import com.zhangds.zdsblog.common.model.support.BaseResponse;
import com.zhangds.zdsblog.common.service.AdminService;
import com.zhangds.zdsblog.common.service.IndexService;
import com.zhangds.zdsblog.common.service.UserService;
import com.zhangds.zdsblog.security.entity.SelfUserEntity;
import com.zhangds.zdsblog.security.service.SelfUserDetailsService;
import com.zhangds.zdsblog.security.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create by zhangds
 * 2020-02-17 14:02
 **/
@Api(tags = "导航管理")
@RestController
public class IndexController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private IndexService indexService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    /*@ApiOperation(value = "登录", tags = SysContants.LOGIN_URL)
    @PostMapping("index/login")
    public BaseResponse<UserLoginDTO> login(@RequestBody LoginParam param){
        //一、通过接口登录，二、通过配置文件引入登录逻辑处理类
        String token = null;
        SelfUserEntity userDetails = selfUserDetailsService.loadUserByUsername(param.getUsername());
        if(!passwordEncoder.matches(param.getPassword(), userDetails.getPassword())){
            throw new BadCredentialsException("密码不正确");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        token = JwtUtils.createAccessToken(userDetails);
        // 插入登录日志
        // 是否已经登录
        // SelfUserEntity userDetails = (SelfUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByName(param.getUsername());
        UserLoginDTO dto = new UserLoginDTO();
        BeanUtils.copyProperties(user, dto);
        dto.setToken(token);
        return BaseResponse.ok(dto);
    }*/

    @ApiOperation("注册")
    @PostMapping(SysContants.REGIST_URL)
    public BaseResponse register(@RequestBody User user){
        //查询是否有相同用户
        User sameUser = null;
        if (StrUtil.isNotEmpty(user.getUsername())){
            sameUser = userService.getUserByName(user.getUsername());
        }
        if (ObjectUtil.isNotEmpty(sameUser)){
            return BaseResponse.warn("用户账号已被注册");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        indexService.register(user);
        return BaseResponse.ok(null);
    }



}

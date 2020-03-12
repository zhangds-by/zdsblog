package com.zhangds.zdsblog.controller.admin;

import com.zhangds.zdsblog.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by zhangds
 * 2020-03-12 10:30
 **/
@RestController
@Api(tags = "登录日志管理")
@RequestMapping("com.zhangds.zdsblog.aop.log")
public class LoginLogController extends BaseController {

    /*
    @ApiOperation("分页查询登录日志")
    @ApiOperation("清空所有登录日志")
    @ApiOperation("获取最近的登录日志列表")
    */

}

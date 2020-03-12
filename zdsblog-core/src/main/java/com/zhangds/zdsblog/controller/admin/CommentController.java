package com.zhangds.zdsblog.controller.admin;

import com.zhangds.zdsblog.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by zhangds
 * 2020-03-12 10:30
 **/
@RestController
@Api(tags = "评论管理")
@RequestMapping("comment")
public class CommentController extends BaseController {

    /*
    @ApiOperation("新增评论")
    @ApiOperation("更新评论") //修改点赞数
    @ApiOperation("删除评论") //删除评论同时删除子评论
    @ApiOperation("获取评论树")
    @ApiOperation("获取最新的评论")
    */

}

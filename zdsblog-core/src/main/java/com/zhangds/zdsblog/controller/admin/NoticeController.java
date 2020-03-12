package com.zhangds.zdsblog.controller.admin;

import com.zhangds.zdsblog.aop.annotation.Log;
import com.zhangds.zdsblog.common.model.dto.BaseQueryCriteria;
import com.zhangds.zdsblog.common.model.entity.Notice;
import com.zhangds.zdsblog.common.model.enums.BusinessType;
import com.zhangds.zdsblog.common.model.support.BaseResponse;
import com.zhangds.zdsblog.common.service.NoticeService;
import com.zhangds.zdsblog.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create by zhangds
 * 2020-03-12 10:30
 **/
@RestController
@Api(tags = "通告管理")
@RequestMapping("notice")
public class NoticeController extends BaseController {

    @Autowired
    private NoticeService noticeService;

    @Log(title = "新增通告", businessType = BusinessType.INSERT)
    @ApiOperation("新增通告")
    @PostMapping("/save")
    public BaseResponse save(@RequestBody Notice notice){
        Notice entity = noticeService.save(notice);
        return BaseResponse.ok(entity);
    }

    @ApiOperation("更新通告")
    @Log(title = "更新通告", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public BaseResponse update(@RequestBody Notice notice){
        Notice entity = noticeService.update(notice);
        return BaseResponse.ok(entity);
    }

    @ApiOperation("删除通告")
    @Log(title = "删除通告", businessType = BusinessType.UPDATE)
    @PostMapping("/delete/{noticeId:\\d+}")
    public BaseResponse update(@PathVariable Long noticeId){
        noticeService.deleteById(noticeId);
        return BaseResponse.ok();
    }

    @ApiOperation("获取通告列表")
    @Log(title = "获取通告列表")
    @GetMapping("/getList")
    public BaseResponse getList(BaseQueryCriteria criteria){
        List<Notice> list = noticeService.queryAll(criteria);
        return BaseResponse.ok(list);
    }

    @ApiOperation("通过id获取通告详情")
    @Log(title = "通过id获取通告详情")
    @GetMapping("/getNoticeById/{noticeId:\\d+}")
    public BaseResponse getNoticeById(@PathVariable Long noticeId){
        Notice notice = noticeService.findById(noticeId);
        return BaseResponse.ok(notice);
    }

    @ApiOperation("获取最新的通告")
    @Log(title = "获取最新的通告")
    @GetMapping("/getNewNotice")
    public BaseResponse getNewNotice(){
        //Notice notice = noticeService.findNewNotice();
        return BaseResponse.ok();
    }


}

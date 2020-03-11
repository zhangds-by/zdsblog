package com.zhangds.zdsblog.controller.admin;

import cn.hutool.core.util.ObjectUtil;
import com.zhangds.zdsblog.common.service.TagService;
import com.zhangds.zdsblog.common.model.entity.Tag;
import com.zhangds.zdsblog.common.model.support.BaseResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Create by zhangds
 * 2020-03-03 14:57
 **/
@RestController
@RequestMapping("tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation("获取所有标签")
    @GetMapping("/getAll")
    public BaseResponse<List> getAll(){
        List<Tag> list = tagService.findAll();
        return BaseResponse.ok(list);
    }

    @ApiOperation("保存标签")
    @PostMapping("/save")
    public BaseResponse<Tag> save(@RequestBody @Valid Tag tag){
        Tag entity = tagService.save(tag);
        return BaseResponse.ok(entity);
    }

    @ApiOperation("更新标签")
    @PostMapping("/update")
    public BaseResponse<Tag> update(@RequestBody @Valid Tag tag){
        Tag entity = tagService.update(tag);
        return BaseResponse.ok(entity);
    }

    @ApiOperation("根据id获取标签")
    @GetMapping("/getById/{tagId:\\d+}")
    public BaseResponse<Tag> getById(@PathVariable Long tagId){
        Tag tag = tagService.findById(tagId);
        return BaseResponse.ok(tag);
    }

    @ApiOperation("删除标签")
    @GetMapping("/deleteById/{tagId:\\d+}")
    public BaseResponse deleteById(@PathVariable Long tagId){
        tagService.deleteById(tagId);
        return BaseResponse.ok();
    }
}

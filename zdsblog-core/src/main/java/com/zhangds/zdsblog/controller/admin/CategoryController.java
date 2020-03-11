package com.zhangds.zdsblog.controller.admin;

import com.zhangds.zdsblog.common.model.dto.BaseQueryCriteria;
import com.zhangds.zdsblog.common.model.entity.Category;
import com.zhangds.zdsblog.common.model.param.CategoryParam;
import com.zhangds.zdsblog.common.model.support.BaseResponse;
import com.zhangds.zdsblog.common.service.CategoryService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Create by zhangds
 * 2020-03-02 12:01
 **/
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("类别分页查询")
    @GetMapping("/getPage")
    public BaseResponse<Page> getPage(BaseQueryCriteria queryCriteria, Pageable pageable){
        Page<Category> page = categoryService.getPage(queryCriteria, pageable);
        return BaseResponse.ok(page);
    }

    @ApiOperation("获取所有分类")
    @GetMapping("/getAll")
    public BaseResponse<List> getAll(){
        List<Category> list = categoryService.findAll();
        return BaseResponse.ok(list);
    }

    @ApiOperation("根据父类别id查询")
    @GetMapping("/findByParentId/{parentId}")
    public BaseResponse<List> findByParentId(@PathVariable Long parentId){
        List<Category> list = categoryService.findByParentId(parentId);
        return BaseResponse.ok(list);
    }

    @ApiOperation("根据别名查询")
    @GetMapping("/findBySlugName/{slugName}")
    public BaseResponse<Category> findBySlugName(@PathVariable String slugName){
        Category category = categoryService.findBySlugName(slugName);
        return BaseResponse.ok(category);
    }

    @ApiOperation("保存分类")
    @PostMapping("/save")
    public BaseResponse<Category> save(@RequestBody Category category){
        Category entity = categoryService.save(category);
        return BaseResponse.ok(entity);
    }

    @ApiOperation("修改分类")
    @PostMapping("/update")
    public BaseResponse<Category> update(@RequestBody Category category){
        Category entity = categoryService.update(category);
        return BaseResponse.ok(entity);
    }

    @ApiModelProperty("批量保存分类")
    @PostMapping("saveInBatch")
    public BaseResponse<List> saveInBatch(@RequestBody List<Category> list){
        List<Category> categories = categoryService.saveInBatch(list);
        return BaseResponse.ok(categories);
    }

}

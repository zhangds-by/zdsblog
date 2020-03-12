package com.zhangds.zdsblog.controller.admin;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Lists;
import com.zhangds.zdsblog.common.model.dto.ArticleDTO;
import com.zhangds.zdsblog.common.model.entity.*;
import com.zhangds.zdsblog.common.model.support.BaseResponse;
import com.zhangds.zdsblog.common.service.*;
import com.zhangds.zdsblog.common.utils.CommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create by zhangds
 * 2020-03-02 10:32
 **/
@RestController
@RequestMapping("/article")
@Api(tags = "文章管理")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleMetaService articleMetaService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MetaService metaService;

    @ApiOperation("获取所有文章")
    @GetMapping("/getAll")
    public BaseResponse<List> getAll(){
        List<Article> list = articleService.findAll();
        return BaseResponse.ok(list);
    }

    @ApiOperation("id获取，预览，修改")
    @GetMapping("/getById/{articleId:\\d+}")
    public BaseResponse<ArticleDTO> getById(@PathVariable Long articleId){
        ArticleDTO dto = new ArticleDTO();
        Article article = articleService.findById(articleId);
        BeanUtils.copyProperties(article, dto);
        //获取该篇文章的分类
        List<Category> categoryList = Lists.newArrayList();
        dto.setCategoryList(categoryList);
        //获取该篇文章的标签
        List<Tag> tagList = Lists.newArrayList();
        dto.setTagList(tagList);
        //获取该篇文章的发布方式
        return BaseResponse.ok(dto);
    }

    @ApiOperation("发布文章，保存草稿")
    @PostMapping("/saveOrUpdate")
    public BaseResponse saveOrUpdate(@RequestBody ArticleDTO dto){
        List<ArticleMeta> articleMetaList = Lists.newArrayList();
        List<ArticleCategory> articleCategoryList = Lists.newArrayList();
        List<ArticleTag> articleTagList = Lists.newArrayList();

        if (ObjectUtil.isNotEmpty(dto.getId())){
            Article article = new Article();
            BeanUtils.copyProperties(dto, article);
            //更新文章基本信息
            articleService.update(article);
            //更新文章标签
            for (Tag tag : CommonUtils.nullToEmpty(dto.getTagList())) {
                ArticleTag articleTag = new ArticleTag();
            }
            articleTagService.updateInBatch(articleTagList);
            //更新文章分类
            articleCategoryService.saveInBatch(articleCategoryList);
            //更新文章发布方式
            articleMetaService.saveInBatch(articleMetaList);
        } else {
            //保存文章基本信息
            Article article = new Article();
            BeanUtils.copyProperties(dto, article);
            articleService.save(article);
            //保存文章标签
            articleTagService.saveInBatch(articleTagList);
            //保存文章分类
            articleCategoryService.saveInBatch(articleCategoryList);
            //保存文章发布方式
            articleMetaService.saveInBatch(articleMetaList);
        }
        return BaseResponse.ok();
    }

    @GetMapping("/reCycle/")
    public BaseResponse reCycle(){
        return BaseResponse.ok();
    }


}

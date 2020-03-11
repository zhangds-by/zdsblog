package com.zhangds.zdsblog.common.model.dto;

import com.zhangds.zdsblog.common.model.entity.Article;
import com.zhangds.zdsblog.common.model.entity.Category;
import com.zhangds.zdsblog.common.model.entity.Meta;
import com.zhangds.zdsblog.common.model.entity.Tag;
import lombok.Data;

import java.util.List;

/**
 * Create by zhangds
 * 2020-03-03 15:43
 **/
@Data
public class ArticleDTO extends Article {

    private List<Tag> tagList;

    private List<Meta> metaList;

    private List<Category> categoryList;

}

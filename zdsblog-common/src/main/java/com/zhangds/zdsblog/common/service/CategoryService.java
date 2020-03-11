package com.zhangds.zdsblog.common.service;

import com.zhangds.zdsblog.common.model.dto.BaseQueryCriteria;
import com.zhangds.zdsblog.common.model.entity.Category;
import com.zhangds.zdsblog.common.model.param.CategoryParam;
import com.zhangds.zdsblog.common.service.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Create by zhangds
 * 2020-03-02 11:58
 **/
public interface CategoryService extends BaseService<Category, Long> {

    Page<Category> getPage(BaseQueryCriteria queryCriteria, Pageable pageable);

    List<Category> findByParentId(Long parentId);

    Category findBySlugName(String slugName);

}

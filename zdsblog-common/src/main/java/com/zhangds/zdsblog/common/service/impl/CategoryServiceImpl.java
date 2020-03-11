package com.zhangds.zdsblog.common.service.impl;

import com.zhangds.zdsblog.common.model.dto.BaseQueryCriteria;
import com.zhangds.zdsblog.common.model.entity.Attachment;
import com.zhangds.zdsblog.common.model.entity.Category;
import com.zhangds.zdsblog.common.model.param.CategoryParam;
import com.zhangds.zdsblog.common.repository.CategoryRepository;
import com.zhangds.zdsblog.common.service.CategoryService;
import com.zhangds.zdsblog.common.service.base.AbstractBaseService;
import com.zhangds.zdsblog.common.utils.QueryHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by zhangds
 * 2020-03-02 11:59
 **/
@Service
public class CategoryServiceImpl extends AbstractBaseService<Category, Long> implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Category> getPage(BaseQueryCriteria queryCriteria, Pageable pageable) {
        Page<Category> page = categoryRepository.findAll((root, criteriaQuery, criteriaBuilder)
                -> QueryHelp.getPredicate(root, queryCriteria, criteriaBuilder), pageable);
        return page;
    }

    @Override
    public List<Category> findByParentId(Long parentId) {
        return categoryRepository.findByParentId(parentId);
    }

    @Override
    public Category findBySlugName(String slugName) {
        return categoryRepository.findBySlugName(slugName);
    }

}

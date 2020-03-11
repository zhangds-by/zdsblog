package com.zhangds.zdsblog.common.repository;

import com.zhangds.zdsblog.common.repository.base.BaseRepository;
import com.zhangds.zdsblog.common.model.entity.Category;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by zhangds
 * 2020-03-02 12:09
 **/
@Repository
public interface CategoryRepository extends BaseRepository<Category, Long>, JpaSpecificationExecutor {

    List<Category> findByParentId(Long parentId);

    Category findBySlugName(String slugName);


}

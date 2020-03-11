package com.zhangds.zdsblog.common.model.entity;

import com.zhangds.zdsblog.common.model.entity.ArticleCategoryId;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 文章类别关联
 * Create by zhangds
 * 2020-02-25 18:51
 **/
@Data
@Entity
@NoArgsConstructor
@IdClass(ArticleCategoryId.class)
@Table(name = "sys_article_category")
public class ArticleCategory {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "bigint")
    private Long id;

    @Id
    @Column(name = "article_id", columnDefinition = "bigint")
    private Long articleId;

    @Id
    @Column(name = "category_id", columnDefinition = "bigint")
    private Long categoryId;

}

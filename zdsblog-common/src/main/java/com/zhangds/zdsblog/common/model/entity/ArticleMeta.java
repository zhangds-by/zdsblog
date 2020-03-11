package com.zhangds.zdsblog.common.model.entity;

import com.zhangds.zdsblog.common.model.entity.ArticleMetaId;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 文章
 * Create by zhangds
 * 2020-02-25 18:51
 **/
@Data
@Entity
@NoArgsConstructor
@IdClass(ArticleMetaId.class)
@Table(name = "sys_article_meta")
public class ArticleMeta {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "bigint")
    private Long id;

    @Id
    @Column(name = "article_id", columnDefinition = "bigint")
    private Long articleId;

    @Id
    @Column(name = "meta_id", columnDefinition = "bigint")
    private Long metaId;

}

package com.zhangds.zdsblog.common.model.entity;

import com.zhangds.zdsblog.common.model.entity.ArticleTagId;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 角色-菜单
 * Create by zhangds
 * 2020-02-25 18:51
 **/
@Data
@Entity
@NoArgsConstructor
@IdClass(ArticleTagId.class)
@Table(name = "sys_article_tag")
public class ArticleTag {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "bigint")
    private Long id;

    @Id
    @Column(name = "article_id", columnDefinition = "bigint")
    private Long articleId;

    @Id
    @Column(name = "tag_id", columnDefinition = "bigint")
    private Long tagId;

}

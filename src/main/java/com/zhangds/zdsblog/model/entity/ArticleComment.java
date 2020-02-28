package com.zhangds.zdsblog.model.entity;

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
@IdClass(ArticleCommentId.class)
@Table(name = "sys_article_comment")
public class ArticleComment {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Id
    @Column(name = "article_id")
    private Long articleId;

    @Id
    @Column(name = "comment_id")
    private Long commentId;

}

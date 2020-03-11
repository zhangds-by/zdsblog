package com.zhangds.zdsblog.common.model.entity;

import com.zhangds.zdsblog.common.model.entity.ArticleCommentId;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 文章评论关联
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
    @Column(name = "id", columnDefinition = "bigint")
    private Long id;

    @Id
    @Column(name = "article_id", columnDefinition = "bigint")
    private Long articleId;

    @Id
    @Column(name = "comment_id", columnDefinition = "bigint")
    private Long commentId;

}

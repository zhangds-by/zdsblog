package com.zhangds.zdsblog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Objects;

/**
 * Create by zhangds
 * 2020-02-26 18:44
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCategoryId implements Serializable {

    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "category_id")
    private Long categoryId;

    public ArticleCategoryId(Long articleId, Long categoryId){
        this.articleId = articleId;
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ArticleCategoryId that = (ArticleCategoryId) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(articleId, that.articleId) &&
                Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, articleId, categoryId);
    }
}

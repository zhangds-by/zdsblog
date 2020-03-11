package com.zhangds.zdsblog.common.model.entity;

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
public class ArticleMetaId implements Serializable {

    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "meta_id")
    private Long metaId;

    public ArticleMetaId(Long articleId, Long metaId){
        this.articleId = articleId;
        this.metaId = metaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ArticleMetaId that = (ArticleMetaId) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(articleId, that.articleId) &&
                Objects.equals(metaId, that.metaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, articleId, metaId);
    }
}

package com.zhangds.zdsblog.common.model.entity;

import com.zhangds.zdsblog.common.model.enums.CreateFrom;
import com.zhangds.zdsblog.common.model.enums.PostStatus;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 文章
 * Create by zhangds
 * 2020-02-28 15:38
 **/
@Data
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    private Long id;

    @ApiModelProperty("作者")
    @Column(name = "author_id", columnDefinition ="bigint not null")
    private Long authorId;

    @Column(name = "title", columnDefinition = "varchar(100) not null")
    private String title;

    @Column(name = "status", columnDefinition = "varchar(11) default 1")
    private PostStatus status;

    @Column(name = "url", columnDefinition = "varchar(255) not null", unique = true)
    private String url;

    @Column(name = "original_content", columnDefinition = "longtext not null")
    private String originalContent;

    @Column(name = "format_content", columnDefinition = "longtext not null")
    private String formatContent;

    @Column(name = "summary", columnDefinition = "varchar(500) default ''")
    private String summary;

    @ApiModelProperty("阅读量")
    @Column(name = "visits", columnDefinition = "bigint default 0")
    private Long visits;

    @ApiModelProperty("允许评论")
    @Column(name = "disallow_comment", columnDefinition = "tinyint default 0")
    private Boolean disallowComment;

    @ApiModelProperty("模板")
    @Column(name = "template", columnDefinition = "varchar(255) default ''")
    private String template;

    @ApiModelProperty("排序")
    @Column(name = "top_priority", columnDefinition = "int default 0")
    private Integer topPriority;

    @ApiModelProperty("发布来源")
    @Column(name = "create_from", columnDefinition = "varchar(10) default 0")
    private CreateFrom createFrom;

    @ApiModelProperty("点赞量")
    @Column(name = "likes", columnDefinition = "bigint default 0")
    private Long likes;

    @Column(name = "create_time", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ApiModelProperty("修改时间")
    @Column(name = "edit_time", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editTime;
}

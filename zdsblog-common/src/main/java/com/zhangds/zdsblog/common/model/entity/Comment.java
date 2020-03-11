package com.zhangds.zdsblog.common.model.entity;

import com.zhangds.zdsblog.common.model.enums.CommentStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * 评论
 * Create by zhangds
 * 2020-02-28 15:09
 **/
@Data
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_name", columnDefinition = "varchar(50) not null")
    private String authorName;

    @Column(name = "email", columnDefinition = "varchar(127) not null")
    private String email;

    @Column(name = "ip", columnDefinition = "varchar(127) default ''")
    private String ip;

    @Column(name = "author_url", columnDefinition = "varchar(127) default ''")
    private String authorUrl;

    @Column(name = "content", columnDefinition = "varchar(1023) not null")
    private String content;

    @Column(name = "status", columnDefinition = "int default 1")
    private CommentStatus status;

    @Column(name = "user_agent", columnDefinition = "varchar(127) default ''")
    private String userAgent;

    @ApiModelProperty("是否是up主评论")
    @Column(name = "is_admin", columnDefinition = "tinyint default 0")
    private Boolean isAdmin;

    @Column(name = "allow_notification", columnDefinition = "tinyint default 1")
    private Boolean allowNotification;

    @Column(name = "post_id", columnDefinition = "int not null")
    private Integer postId;

    @ApiModelProperty("点赞数，优先级")
    @Column(name = "likes", columnDefinition = "int default 0")
    private Integer likes;

    @Column(name = "parent_id", columnDefinition = "bigint default 0")
    private Long parentId;

}

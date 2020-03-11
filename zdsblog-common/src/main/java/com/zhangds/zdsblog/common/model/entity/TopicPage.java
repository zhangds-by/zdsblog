package com.zhangds.zdsblog.common.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Create by zhangds
 * 2020-02-29 12:16
 **/
@Data
@Entity
public class TopicPage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long pageId;

    private String pageKey;

    private String pageTitle;

    private String pageContent;

    private Date pageCreateTime;

    private Date pageUpdateTime;

    private Integer pageViewCount;

    private Integer pageCommentCount;

    private Integer pageStatus;
}

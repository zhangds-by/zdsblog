package com.zhangds.zdsblog.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 类型
 * Create by zhangds
 * 2020-02-28 15:34
 **/
@Entity
@Data
@Table(name = "meta")
public class Meta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article_id", columnDefinition = "int not null")
    private Integer articleId;

    @Column(name = "meta_key", columnDefinition = "varchar(100) not null")
    private String key;

    @Column(name = "meta_value", columnDefinition = "varchar(1023) not null")
    private String value;
}

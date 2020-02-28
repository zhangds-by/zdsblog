package com.zhangds.zdsblog.model.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Create by zhangds
 * 2020-02-28 15:36
 **/
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(255) not null")
    private String name;

    @Column(name = "url", columnDefinition = "varchar(1023) not null")
    private String url;

    @Column(name = "logo", columnDefinition = "varchar(1023) default ''")
    private String logo;

    @Column(name = "description", columnDefinition = "varchar(255) default ''")
    private String description;

    @Column(name = "team", columnDefinition = "varchar(255) default ''")
    private String team;

    @Column(name = "priority", columnDefinition = "int default 0")
    private Integer priority;
}

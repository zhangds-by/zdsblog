package com.zhangds.zdsblog.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 标签
 * Create by zhangds
 * 2020-02-28 15:23
 **/
@Data
@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(255) not null")
    private String name;

    @Column(name = "slug_name", columnDefinition = "varchar(255) not null", unique = true)
    private String slugName;
}

package com.zhangds.zdsblog.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 分类
 * Create by zhangds
 * 2020-02-28 15:18
 **/
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(50) not null")
    private String name;

    @ApiModelProperty("简称")
    @Column(name = "slug_name", columnDefinition = "varchar(50) not null", unique = true)
    private String slugName;

    @Column(name = "description", columnDefinition = "varchar(100) default ''")
    private String description;

    @Column(name = "parent_id", columnDefinition = "int default 0")
    private Integer parentId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}

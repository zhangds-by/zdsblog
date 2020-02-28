package com.zhangds.zdsblog.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Create by zhangds
 * 2020-02-25 18:44
 **/
@Data
@Entity
@Table(name = "sys_moudle")
public class Moudle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moudle_id")
    private Long moudleId;

    @Column(name = "moudle_name")
    private String moudleName;

    @ApiModelProperty("父级id")
    @Column(name = "parent_id")
    private Long parentId;

    @ApiModelProperty(value = "菜单级数")
    private Integer level;

    @ApiModelProperty(value = "菜单排序")
    private Integer sort;

    @ApiModelProperty(value = "前端名称")
    private String name;

    @ApiModelProperty(value = "前端图标")
    private String icon;

    @ApiModelProperty(value = "前端隐藏")
    private Integer hidden;

}

package com.zhangds.zdsblog.common.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 关联项配置
 * Create by zhangds
 * 2020-02-29 15:06
 **/
@Data
@Entity
public class RelationshipOption {

    @Id
    @GeneratedValue
    private Long id;

    @ApiModelProperty("关联配置编码")
    @Column(name = "option_code", columnDefinition = "varchar(50)")
    private String optionCode;

    @ApiModelProperty("关联项主键")
    private Long relationId;

    @ApiModelProperty("项目主键")
    private Long itemId;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("扩展字段")
    @Column(name = "expand", columnDefinition = "varchar(50)")
    private String expand;

}

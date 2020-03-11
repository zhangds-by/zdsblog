package com.zhangds.zdsblog.common.model.entity;

import com.zhangds.zdsblog.common.model.enums.OptionType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * 系统配置项目
 * Create by zhangds
 * 2020-02-28 15:35
 **/
@Entity
@Data
@Table(name = "sys_option")
public class Option {

    @Id
    @GeneratedValue
    private Integer id;

    @ApiModelProperty("配置类型")
    @Column(name = "option_type", columnDefinition = "varchar(20)")
    private OptionType optionType;

    @ApiModelProperty("配置名称")
    @Column(name = "option_key", columnDefinition = "varchar(50) not null")
    private String optionKey;

    @ApiModelProperty("配置值")
    @Column(name = "value", columnDefinition = "varchar(50) not null")
    private String value;
}

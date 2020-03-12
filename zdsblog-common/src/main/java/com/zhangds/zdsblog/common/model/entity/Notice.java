package com.zhangds.zdsblog.common.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Create by zhangds
 * 2020-03-12 10:57
 **/
@Data
@Entity
@ApiModel("系统通告")
@Table(name = "sys_notice")
public class Notice {

    @Id
    @GeneratedValue
    @ApiModelProperty("公告ID")
    private Long noticeId;

    @ApiModelProperty("公告标题")
    private String noticeTitle;

    @ApiModelProperty("公告类型（1通知 2公告）")
    private String noticeType;

    @ApiModelProperty("公告内容")
    private String noticeContent;

    @ApiModelProperty("公告状态（0正常 1关闭）")
    private String status;
}

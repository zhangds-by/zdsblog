package com.zhangds.zdsblog.common.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 附件
 * Create by zhangds
 * 2020-02-28 15:27
 **/
@Data
@Entity
@Table(name = "sys_attachment")
public class Attachment {

    @Id
    @GeneratedValue
    private Long id;

    @ApiModelProperty("附件名")
    @Column(name = "name", columnDefinition = "varchar(20) not null")
    private String name;

    @ApiModelProperty("附件类型")
    @Column(name = "file_type", columnDefinition = "varchar(20) not null")
    private String fileType;

    @ApiModelProperty("存储位置")
    @Column(name = "thumb_path", columnDefinition = "varchar(20)")
    private String thumbPath;

    @ApiModelProperty("附件大小")
    private Long size;

    @Column(name = "file_key", columnDefinition = "varchar(20)")
    private String fileKey;

    @Column(name = "user_name", columnDefinition = "varchar(50) not null")
    private String userName;

    @Column(name = "create_time", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ApiModelProperty("附件链接")
    @Column(name = "url", columnDefinition = "varchar(100) not null")
    private String url;

}

package com.zhangds.zdsblog.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 附件
 * Create by zhangds
 * 2020-02-28 15:27
 **/
@Data
@Entity
@Table(name = "attachment")
public class Attachment {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String type;

    private String key;

    private Long authorId;

    private Date created;

}

package com.zhangds.zdsblog.model.entity;

import com.zhangds.zdsblog.model.enums.OptionType;
import lombok.Data;

import javax.persistence.*;

/**
 * 发布形式
 * Create by zhangds
 * 2020-02-28 15:35
 **/
@Entity
@Data
@Table(name = "option")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type", columnDefinition = "int default 0")
    private OptionType type;

    @Column(name = "option_key", columnDefinition = "varchar(100) not null")
    private String key;

    @Column(name = "option_value", columnDefinition = "varchar(1023) not null")
    private String value;
}

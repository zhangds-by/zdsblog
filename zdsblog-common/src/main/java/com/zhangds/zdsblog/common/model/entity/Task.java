package com.zhangds.zdsblog.common.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 定时任务
 * Create by zhangds
 * 2020-03-03 18:56
 **/
@Data
@Entity
@Table(name = "sys_task")
public class Task {

    @Id
    @GeneratedValue
    private Long id;
    /**
     * 定时任务名称
     */
    private String jobName;
    /**
     * 定时任务组
     */
    private String jobGroupName;
    /**
     * 定时任务全类名
     */
    private String jobClassName;
    /**
     * 触发器名称
     */
    private String triggerName;
    /**
     * 触发器组
     */
    private String triggerGroup;
    /**
     * 重复间隔
     */
    private Integer repeatInterval;
    /**
     * 触发次数
     */
    private Integer timesTriggered;
    /**
     * cron 表达式
     */
    private String cronExpression;
    /**
     * 时区
     */
    private String timeZoneId;
    /**
     * 定时任务状态
     */
    private String triggerState;
}

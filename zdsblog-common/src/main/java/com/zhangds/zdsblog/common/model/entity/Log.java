package com.zhangds.zdsblog.common.model.entity;

import com.zhangds.zdsblog.common.model.enums.BusinessType;
import com.zhangds.zdsblog.common.model.enums.LogType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Zheng Jie
 * @date 2018-11-24
 */
@Entity
@Data
@ApiModel("操作日志")
@Table(name = "sys_log")
@NoArgsConstructor
public class Log implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("操作用户")
    private String username;

    @ApiModelProperty("操作模块")
    private String title;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("操作方法名")
    private String method;

    @ApiModelProperty("参数")
    @Column(columnDefinition = "text")
    private String params;

    @ApiModelProperty("日志状态")
    @Column(name = "log_type")
    private LogType logType;

    @ApiModelProperty("日志类型")
    @Column(name = "business_type")
    private BusinessType businessType;

    @ApiModelProperty("请求ip")
    @Column(name = "request_ip")
    private String requestIp;

    @ApiModelProperty("地址")
    @Column(name = "location")
    private String location;

    @ApiModelProperty("地址")
    @Column(name = "url")
    private String url;

    @ApiModelProperty("浏览器")
    private String browser;

    @ApiModelProperty("请求耗时")
    private Long time;

    @ApiModelProperty("异常详细")
    @Column(name = "exception_detail", columnDefinition = "text")
    private byte[] exceptionDetail;

    @ApiModelProperty("创建日期")
    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    public Log(LogType logType, Long time) {
        this.logType = logType;
        this.time = time;
    }
}

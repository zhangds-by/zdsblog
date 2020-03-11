package com.zhangds.zdsblog.common.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Create by zhangds
 * 2020-03-03 18:45
 **/
public interface BaseJob extends Job {

    @Override
    void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException;
}

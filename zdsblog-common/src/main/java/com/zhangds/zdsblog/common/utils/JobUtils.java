package com.zhangds.zdsblog.common.utils;

import com.zhangds.zdsblog.common.quartz.job.BaseJob;
import com.zhangds.zdsblog.common.model.entity.Task;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Create by zhangds
 * 2020-03-04 11:34
 **/
@Slf4j
@Component
public class JobUtils {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    //private TaskService taskService;

    /**
     *
     * @param scheduler quartz调度器
     * @param startAtTime 任务执行时刻
     * @param name 任务名称
     * @param group 任务组名称
     * @param jobBean 具体任务
     */
    public static void createJobByStartAt(Scheduler scheduler, long startAtTime, String name, String group, Class jobBean){
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group).startAt(new Date(startAtTime)).build();
        createJob(scheduler, name, group, trigger,jobBean);

    }

    /**
     *
     * @param scheduler quartz调度器
     * @param name 任务名称
     * @param group 任务组名称
     * @param cron cron表达式
     * @param jobBean 具体任务
     */
    public static void createJobByCron(Scheduler scheduler, String name, String group,String cron,Class jobBean){
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group).withSchedule(scheduleBuilder).build();
        createJob(scheduler,name,group,trigger,jobBean);
    }

    /**
     * 查询定时任务列表
     *
     * @return 定时任务列表
     */
    /*public Page<Task> list(Integer pageIndex, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<Task> page = taskService.pageBy(pageable);
        return page;
    }*/


    private static void createJob(Scheduler scheduler, String name, String group, Trigger trigger,Class jobBean) {
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(jobBean).withIdentity(name,group).build();
        try {
            //将触发器与任务绑定到调度器内
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加并启动定时任务
     *
     */
    public void addJob(Task task) throws Exception {
        // 启动调度器
        scheduler.start();

        // 构建Job信息
        JobDetail jobDetail = JobBuilder.newJob(getClass(task.getJobClassName()).getClass()).withIdentity(task.getJobClassName(), task.getJobGroupName()).build();

        // Cron表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule(task.getCronExpression());

        //根据Cron表达式构建一个Trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getJobClassName(), task.getJobGroupName()).withSchedule(cron).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error("【定时任务】创建失败！", e);
            throw new Exception("【定时任务】创建失败！");
        }

    }

    /**
     * 删除定时任务
     *
     */
    public void deleteJob(Task task) throws SchedulerException {
        scheduler.pauseTrigger(TriggerKey.triggerKey(task.getJobClassName(), task.getJobGroupName()));
        scheduler.unscheduleJob(TriggerKey.triggerKey(task.getJobClassName(), task.getJobGroupName()));
        scheduler.deleteJob(JobKey.jobKey(task.getJobClassName(), task.getJobGroupName()));
    }

    /**
     * 暂停定时任务
     *
     */
    public void pauseJob(Task task) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(task.getJobClassName(), task.getJobGroupName()));
    }

    /**
     * 恢复定时任务
     *
     */
    public void resumeJob(Task task) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(task.getJobClassName(), task.getJobGroupName()));
    }


    /**
     * 根据全类名获取Job实例
     *
     */
    public static BaseJob getClass(String classname) throws Exception {
        Class<?> clazz = Class.forName(classname);
        return (BaseJob) clazz.newInstance();
    }
}

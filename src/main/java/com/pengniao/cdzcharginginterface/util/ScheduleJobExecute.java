package com.pengniao.cdzcharginginterface.util;


import com.pengniao.cdzcharginginterface.entity.ScheduleJob;
import com.pengniao.cdzcharginginterface.entity.ScheduleJobLog;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @ClassNameScheduleJobExecute
 * @Description 执行定时任务，并保存定时任务
 * @Author
 * @Date2020/8/25 15:20
 * @Version V1.0
 **/
public class ScheduleJobExecute extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap()
                .get(ScheduleJob.JOB_PARAM_KEY);

        //获取spring bean
        //ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtils.getBean("scheduleJobLogService");

        //数据库保存执行记录
        ScheduleJobLog log = new ScheduleJobLog();
        log.setJobId(scheduleJob.getJobId()); // 任务ID
        log.setBeanName(scheduleJob.getBeanName()); // spring bean名称
        log.setParams(scheduleJob.getParams()); // 参数
        log.setCreateTime(new Date()); // 创建时间

        //任务开始时间
        long startTime = System.currentTimeMillis();

        try {
            //执行任务
            logger.info("任务准备执行，任务ID：" + scheduleJob.getJobId());

            Object target = SpringContextUtils.getBean(scheduleJob.getBeanName());
            Method method = target.getClass().getDeclaredMethod("run", String.class);
            method.invoke(target, scheduleJob.getParams());

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setTimes((int)times); // 耗时时间
            //任务状态    0：成功    1：失败
            log.setStatus(0);

            logger.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");

            //scheduleJobLogService.saveJobLog(log);


        } catch (Exception e) {
            logger.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setTimes((int)times); // 耗时时间

            //任务状态    0：成功    1：失败
            log.setStatus(1);
            log.setError(StringUtils.substring(e.toString(), 0, 2000));

            try {
                // 将错误日志保存到日志表中
                //scheduleJobLogService.saveJobLog(log);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
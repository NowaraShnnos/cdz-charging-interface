package com.pengniao.cdzcharginginterface.service.impl;

import com.pengniao.cdzcharginginterface.entity.ScheduleJob;
import com.pengniao.cdzcharginginterface.mapper.ScheduleJobMapper;
import com.pengniao.cdzcharginginterface.service.ScheduleJobService;
import com.pengniao.cdzcharginginterface.util.Constant;
import com.pengniao.cdzcharginginterface.util.IdWorker;
import com.pengniao.cdzcharginginterface.util.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;


/**
 * @ClassNameScheduleJobServiceImpl
 * @Description 定时任务
 * @Author
 * @Date2020/8/25 10:26
 * @Version V1.0
 **/

@Transactional
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleJobMapper scheduleJobMapper;

    /* 功能描述: 项目启动时，初始化定时器
     * @param
    * @return: void
    * @Author: home
    * @Date: 2020/8/27 9:56
    */
    @PostConstruct
    public void init(){
        List<ScheduleJob> scheduleJobList = scheduleJobMapper.queryScheduleJobList();
        for (ScheduleJob scheduleJob : scheduleJobList){
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            // 如果不存在，则创建
            if(cronTrigger == null){
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else{
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    public List<ScheduleJob> queryScheduleJobList() {
        return scheduleJobMapper.queryScheduleJobList();
    }

    /* 功能描述: 保存定时任务
     * @param scheduleJob
    * @return: void
    * @Author: home
    * @Date: 2020/8/25 10:26
    */
    @Override
    public void saveJob(ScheduleJob scheduleJob) throws Exception {
        scheduleJob.setJobId(IdWorker.nextId()); //  任务ID
        scheduleJob.setStatus(Constant.ScheduleStatus.NORMAL.getValue()); // 任务状态 正常
        scheduleJob.setCreateTime(new Date()); // 创建时间
        scheduleJob.setJobType(3); // 任务类型
        // 保存定时作业
        scheduleJobMapper.saveJob(scheduleJob);

        // 创建定时任务
        ScheduleUtils.createScheduleJob(scheduler,scheduleJob);

    }

    /* 功能描述: 通过ID查询定时任务
     * @param jobId
    * @return: com.cdznet.commonutils.entity.ScheduleJob
    * @Author: home
    * @Date: 2020/8/25 11:42
    */
    @Override
    public ScheduleJob queryScheduleJobById(Long jobId) {
        return scheduleJobMapper.queryScheduleJobById(jobId);
    }

    /* 功能描述: 更新定时任务
     * @param scheduleJob
    * @return: void
    * @Author: home
    * @Date: 2020/8/25 11:54
    */
    @Override
    public void updateJob(ScheduleJob scheduleJob) throws Exception {
        ScheduleUtils.updateScheduleJob(scheduler,scheduleJob);
        scheduleJobMapper.updateJob(scheduleJob);
    }

    /* 功能描述: 批量删除定时任务
     * @param JobIds
    * @return: void
    * @Author: home
    * @Date: 2020/8/25 12:01
    */
    @Override
    public void batchDeleteJob(Long[] jobIds) throws Exception {
        for (Long jobId : jobIds){
            ScheduleJob scheduleJob =  scheduleJobMapper.queryScheduleJobById(jobId);
            ScheduleUtils.deleteScheduleJob(scheduler,jobId);
        }
        scheduleJobMapper.batchDeleteJob(jobIds);
    }

    /* 功能描述: 立即执行
     * @param jobIds
    * @return: void
    * @Author: home
    * @Date: 2020/8/27 11:26
    */
    @Override
    public void run(Long[] jobIds) throws Exception {

        for (Long jobId : jobIds){
            ScheduleJob scheduleJob =  scheduleJobMapper.queryScheduleJobById(jobId);
            ScheduleUtils.run(scheduler,scheduleJob);
        }

        // 批量更新任务状态
        scheduleJobMapper.updateBathchStatus(jobIds, Constant.ScheduleStatus.NORMAL.getValue());

    }

    /* 功能描述: 暂停执行
     * @param jobIds
    * @return: void
    * @Author: home
    * @Date: 2020/8/27 11:58
    */
    @Override
    public void pause(Long[] jobIds) throws Exception {

        for (Long jobId : jobIds){
            ScheduleUtils.pauseJob(scheduler,jobId);
        }

        // 批量更新任务状态
        scheduleJobMapper.updateBathchStatus(jobIds, Constant.ScheduleStatus.PAUSE.getValue());
    }

    /* 功能描述: 恢复运行
     * @param null
    * @return:
    * @Author: home
    * @Date: 2020/8/27 14:09
    */
    @Override
    public void resume(Long[] jobIds) throws Exception {
        for (Long jobId : jobIds){
            ScheduleUtils.resumeJob(scheduler,jobId);
        }

        // 批量更新任务状态
        scheduleJobMapper.updateBathchStatus(jobIds, Constant.ScheduleStatus.NORMAL.getValue());
    }


}
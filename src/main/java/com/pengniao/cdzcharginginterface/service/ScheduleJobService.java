package com.pengniao.cdzcharginginterface.service;

/* 功能描述: 定时任务
 * @param null
* @return:
* @Author: home
* @Date: 2020/8/25 10:23
*/


import com.pengniao.cdzcharginginterface.entity.ScheduleJob;

import java.util.List;

public interface ScheduleJobService {

    /* 功能描述: 查询定时任务
    * @return: java.util.List<com.cdznet.commonutils.entity.ScheduleJob>
    * @Author: home
    * @Date: 2020/8/25 11:13
    */
    public List<ScheduleJob> queryScheduleJobList();

    /* 功能描述: 保存定时任务
     * @param null
    * @return:
    * @Author: home
    * @Date: 2020/8/25 10:25
    */
    public void saveJob(ScheduleJob scheduleJob) throws Exception;

    /* 功能描述: 通过ID获取定时任务
     * @param jobId
     * @return: com.cdznet.commonutils.entity.ScheduleJob
     * @Author: home
     * @Date: 2020/8/25 11:40
     */
    public ScheduleJob queryScheduleJobById(Long jobId);

    /* 功能描述: 更新任务
     * @param scheduleJob
     * @return: void
     * @Author: home
     * @Date: 2020/8/25 11:50
     */
    public void updateJob(ScheduleJob scheduleJob) throws Exception;

    /* 功能描述: 批量删除定时任务
     * @param null
    * @return:
    * @Author: home
    * @Date: 2020/8/25 12:00
    */
    public void batchDeleteJob(Long[] JobIds)throws Exception;
    
    /* 功能描述: 立即执行
     * @param jobIds
    * @return: void
    * @Author: home
    * @Date: 2020/8/27 11:26
    */
    public void run(Long[] jobIds) throws Exception;

    /* 功能描述: 暂停运行
     * @param jobIds
    * @return: void
    * @Author: home
    * @Date: 2020/8/27 11:57
    */
    public void pause(Long[] jobIds) throws Exception;

    /* 功能描述: 恢复运行
     * @param jobIds
    * @return: void
    * @Author: home
    * @Date: 2020/8/27 14:08
    */
    public void resume(Long[] jobIds) throws Exception;
}

package com.pengniao.cdzcharginginterface.mapper;

import com.pengniao.cdzcharginginterface.entity.ScheduleJob;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/* 功能描述: 定时任务
 * @param null
* @return:
* @Author: home
* @Date: 2020/8/25 10:35
*/
public interface ScheduleJobMapper {


    /* 功能描述: 获取所有的定时器
     * @param
    * @return: java.util.List<com.cdznet.commonutils.entity.ScheduleJob>
    * @Author: home
    * @Date: 2020/8/27 9:58
    */
    public List<ScheduleJob> queryScheduleJobList();

    /* 功能描述: 保存任务
     * @param scheduleJob
    * @return: void
    * @Author: home
    * @Date: 2020/8/25 10:36
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
     * @param JobIds
     * @return: void
     * @Author: home
     * @Date: 2020/8/25 12:02
     */
    public void batchDeleteJob(Long[] JobIds)throws Exception;

    /* 功能描述: 批量更新任务状态
     * @param jobIds
     * @param status
    * @return: void
    * @Author: home
    * @Date: 2020/8/27 12:03
    */
    public void updateBathchStatus(@Param("jobIds") Long[] jobIds, @Param("status") int status)throws Exception;
}

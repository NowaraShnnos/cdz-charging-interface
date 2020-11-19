package com.pengniao.cdzcharginginterface.controller;

import com.pengniao.cdzcharginginterface.entity.ScheduleJob;
import com.pengniao.cdzcharginginterface.response.Result;
import com.pengniao.cdzcharginginterface.service.ScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassNameScheduleJobController
 * @Description 定时任务
 * @Author
 * @Date2020/8/25 9:33
 * @Version V1.0
 **/

@RestController
@RequestMapping("schedulejob")
public class ScheduleJobController {

    @Autowired
    private ScheduleJobService scheduleJobService;

    /* 功能描述: 获取定时任务
     * @param pagination
    * @return:
    * @Author: home
    * @Date: 2020/8/28 14:37
    */
    @GetMapping("schedule/scheduleJobList")
    public List<ScheduleJob> scheduleJobList(){

        List<ScheduleJob> list = scheduleJobService.queryScheduleJobList();
        return list;

    }


    /* 功能描述: 新增定时任务
     * @param scheduleJob
    * @return: com.cdznet.commonutils.response.Result
    * @Author: home
    * @Date: 2020/8/25 10:22
    */
    @PostMapping("schedule/save")
    public Result save(@RequestBody ScheduleJob scheduleJob) throws Exception {
        scheduleJobService.saveJob(scheduleJob);
        return Result.SUCCESS();
    }

    /* 功能描述: 通过ID获取定时任务
     * @param jobId
    * @return: com.cdznet.commonutils.response.Result
    * @Author: home
    * @Date: 2020/8/25 11:44
    */
    @GetMapping("schedule/getScheduleJobById/{jobId}")
    public Result getScheduleJobById(@PathVariable("jobId") Long jobId){
        // 通过ID获取部门定时任务
        ScheduleJob scheduleJob = scheduleJobService.queryScheduleJobById(jobId);
        return Result.SUCCESS().put("scheduleJob",scheduleJob);
    }

    /* 功能描述: 更新定时任务
     * @param scheduleJob
    * @return: com.cdznet.commonutils.response.Result
    * @Author: home
    * @Date: 2020/8/25 11:55
    */
    @PostMapping("schedule/update")
    public Result update(@RequestBody ScheduleJob scheduleJob) throws Exception {
        scheduleJobService.updateJob(scheduleJob);
        return Result.SUCCESS();
    }

    /* 功能描述: 删除定时任务
     * @param jobIds
    * @return: com.cdznet.commonutils.response.Result
    * @Author: home
    * @Date: 2020/8/25 11:58
    */
    @PostMapping("schedule/delete")
    public Result deleteSchedule(@RequestBody Long[] jobIds) throws Exception {
        scheduleJobService.batchDeleteJob(jobIds);
        return Result.SUCCESS();
    }

    /* 功能描述: 立即执行
     * @param jobIds
    * @return: com.cdznet.commonutils.response.Result
    * @Author: home
    * @Date: 2020/8/27 11:29
    */
    @PostMapping("schedule/run")
    public Result run(@RequestBody Long[] jobIds) throws Exception {
        scheduleJobService.run(jobIds);
        return Result.SUCCESS();
    }

    /* 功能描述: 暂停定时任务
     * @param jobIds
    * @return: com.cdznet.commonutils.response.Result
    * @Author: home
    * @Date: 2020/8/27 12:08
    */
    @PostMapping("schedule/pause")
    public Result pause(@RequestBody Long[] jobIds) throws Exception {
        scheduleJobService.pause(jobIds);
        return Result.SUCCESS();
    }

    /* 功能描述: 恢复定时任务
     * @param jobIds
    * @return: com.cdznet.commonutils.response.Result
    * @Author: home
    * @Date: 2020/8/27 14:07
    */
    @PostMapping("schedule/resume")
    public Result resume(@RequestBody Long[] jobIds) throws Exception {
        scheduleJobService.resume(jobIds);
        return Result.SUCCESS();
    }
}
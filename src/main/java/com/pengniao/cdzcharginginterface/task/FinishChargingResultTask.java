package com.pengniao.cdzcharginginterface.task;

import com.pengniao.cdzcharginginterface.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: cdz-charging-interface
 * @description: 推送结束充电
 * @author: lj
 * @create: 2020-11-25 09:58
 **/

@Component("FinishChargingResultTask")
public class FinishChargingResultTask implements ITask{

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskService taskService;

    @Override
    public void run(String params) throws Exception {

        log.info("扫描停止充电结果  定时任务正在执行，参数为：{}"+params);
        taskService.resultFinishCharging();

    }
}

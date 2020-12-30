package com.pengniao.cdzcharginginterface.task;

import com.pengniao.cdzcharginginterface.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: cdz-charging-interface
 * @description: 推送实时数据
 * @author: lj
 * @create: 2020-11-23 17:07
 **/
@Component("ChargringDataTask")
public class ChargringDataTask implements ITask{
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskService taskService;

    @Override
    public void run(String params) {

        log.info("推送实时数据  定时任务正在执行，参数为：{}"+params);
        taskService.chargringData();

    }
}

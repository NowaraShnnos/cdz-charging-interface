package com.pengniao.cdzcharginginterface.task;

import com.pengniao.cdzcharginginterface.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassNameStartUpChargringResult
 * @Description 扫描启动充电结果
 * @Author
 * @Date2020/11/12 11:43
 * @Version V1.0
 **/

@Component("StartUpChargringResultTask")
public class StartUpChargringResultTask implements ITask{

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskService taskService;

    @Override
    public void run(String params) {

        log.info("扫描启动充电结果  定时任务正在执行，参数为：{}"+params);
        taskService.resultStartUpChargring();

    }

}
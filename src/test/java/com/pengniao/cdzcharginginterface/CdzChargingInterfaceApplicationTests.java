package com.pengniao.cdzcharginginterface;

import com.alibaba.fastjson.JSON;
import com.pengniao.cdzcharginginterface.service.ChargingService;
import com.pengniao.cdzcharginginterface.service.TaskService;
import com.pengniao.cdzcharginginterface.util.DateTimeUtil;
import entity.FinishCharging;
import entity.StartReply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class CdzChargingInterfaceApplicationTests {

    @Autowired
    private ChargingService chargingService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

//    @Test
//    public void saveStartUpChargring(){
//        String deviceNo = "03710001";
//        String message = "{98989880}";
//        //chargingService.saveStartUpChargring(deviceNo,message);
//    }


//    @Test
//    public void resultStartUpChargring(){
//        taskService.resultStartUpChargring();
//    }

    /* 功能描述: 向redis中插入启动充电结果
     * @param
    * @return: void
    * @Author: lj
    * @Date: 2020/11/23 10:05
    */
    @Test
    public void saveStartReply(){
        String key = "03710065:1";

        StartReply startReply = new StartReply();
        startReply.setStartChargeSeq("dfgdfgdfgdfgdfgfdg");
        startReply.setStartChargeSeqStat(4); //1：启动中；2：充电中；3：停止中；4：已结束；5：未知
        startReply.setConnectorID("03710065");
        startReply.setStartTime(DateTimeUtil.getSystemTime());
        startReply.setIdentCode("");
        startReply.setRet(1);
        startReply.setRetMessage("启动成功");

        redisTemplate.opsForHash().put("startReply", key, JSON.toJSONString(startReply));

    }

    /* 功能描述: 保存结束充电
     * @param null
    * @return:
    * @Author: lj
    * @Date: 2020/11/23 10:47
    */
    @Test
    public void finishCharging(){
        String key = "03710038:1";

        FinishCharging finishCharging = new FinishCharging();
        finishCharging.setStartChargeSeq("22222222222222222");
        finishCharging.setConnectorID("03710038");
        finishCharging.setFinishTime(DateTimeUtil.getSystemTime());
        finishCharging.setPlatform(1);

        redisTemplate.opsForHash().put("finishCharging", key, JSON.toJSONString(finishCharging));
    }
}

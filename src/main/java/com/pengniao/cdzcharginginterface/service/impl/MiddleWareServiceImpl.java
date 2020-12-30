package com.pengniao.cdzcharginginterface.service.impl;

import com.alibaba.fastjson.JSON;
import com.pengniao.cdzcharginginterface.service.MiddleWareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;

/**
 * @program: cdz-charging-interface
 * @description: 主站中间件
 * @author: lj
 * @create: 2020-12-28 14:39
 **/

@RequestMapping("redis")
@Service
public class MiddleWareServiceImpl implements MiddleWareService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    
    /* 功能描述: 保存启动充电
     * @param key
     * @param value
     * @return: java.lang.String
     * @Author: lj
     * @Date: 2020/12/28 14:40
     */
    @Override
    public void saveStartUpChargring(String key, String value) {
        redisTemplate.opsForHash().put("startCharging", key, value);
    }

    /* 功能描述: 保存启动充电结果
     * @param key
     * @param value
    * @return: void
    * @Author: lj
    * @Date: 2020/12/28 15:21
    */
    @Override
    public void saveStartUpChargringRet(String key, String value) {
        redisTemplate.opsForHash().put("startReply", key, value);
    }

    /* 功能描述: 保存结束充电
     * @param key
     * @param value
    * @return: void
    * @Author: lj
    * @Date: 2020/12/28 15:26
    */
    @Override
    public void saveFinishChargring(String key, String value) {
        redisTemplate.opsForHash().put("finishCharging", key, value);
    }

    /* 功能描述: 保存结束充电结果
     * @param key
     * @param value
    * @return: void
    * @Author: lj
    * @Date: 2020/12/28 15:29
    */
    @Override
    public void saveFinishChargringRet(String key, String value) {
        redisTemplate.opsForHash().put("finishReply", key, value);
    }

    /* 功能描述: 保存订单
     * @param key
     * @param value
    * @return: void
    * @Author: lj
    * @Date: 2020/12/28 15:32
    */
    @Override
    public void saveChargeBill(String key, String value) {
        redisTemplate.opsForHash().put("ChargeBill", key, value);
    }


}

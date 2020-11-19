package com.pengniao.cdzcharginginterface.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pengniao.cdzcharginginterface.entity.ChargingObject;
import com.pengniao.cdzcharginginterface.service.ChargingService;
import com.pengniao.cdzcharginginterface.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassNameChargingServiceImpl
 * @Description
 * @Author
 * @Date2020/11/11 15:56
 * @Version V1.0
 **/

@RequestMapping("redis")
@Service
public class ChargingServiceImpl implements ChargingService {


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /* 功能描述: 保存启动充电
     * @param deviceNo
     * @param platform
     * @param jsonObject
    * @return: void
    * @Author: home
    * @Date: 2020/11/11 15:57
    */
    @Override
    public void saveStartUpChargring(String  message) {

        JSONObject jsonObject = JSONObject.parseObject(message);

        String deviceNo = jsonObject.getString("deviceNo"); // 设备编号
        Integer platform = jsonObject.getInteger("platform"); //  1 - 景郑快充  2 - 河南省平台  1 - 小桔

        JSONObject chargingObject = jsonObject.getJSONObject("startChargingJson"); //  启动充电json数据
        ((JSONObject) chargingObject).put("starttime", DateTimeUtil.getSystemTime()); // json数据中增加启动充电时间

        redisTemplate.opsForHash().put("startCharging", deviceNo+":"+platform, JSON.toJSONString(chargingObject));

    }
}
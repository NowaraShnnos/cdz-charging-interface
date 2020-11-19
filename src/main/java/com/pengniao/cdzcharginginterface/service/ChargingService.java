package com.pengniao.cdzcharginginterface.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassNameChargingService
 * @Description 操作redis里充电数据
 * @Author
 * @Date2020/11/11 15:53
 * @Version V1.0
 **/


public interface ChargingService {

    /* 功能描述: 保存启动充电
     * @param deviceNo
     * @param platform // 平台 1：景郑快充  2：河南省平台  1：小桔
     * @param message
    * @return: void
    * @Author: home
    * @Date: 2020/11/11 15:56
    */
    public void saveStartUpChargring(String message);



}
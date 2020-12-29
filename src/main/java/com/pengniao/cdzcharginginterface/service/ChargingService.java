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
    public String saveStartUpChargring(String message);

    /* 功能描述: 删除启动充电结果
     * @param key
    * @return: void
    * @Author: lj
    * @Date: 2020/11/20 14:50
    */
    public void deleteStartUpChargringResult(String key);

    /* 功能描述: 获取启动充电结果（景郑平台使用）
     * @param connectorId 充电设备编号
     * @param platform 平台
    * @return: void
    * @Author: lj
    * @Date: 2020/11/23 11:42
    */
    public Object getStartUpChargringResult(String message);

    /* 功能描述: 获取充电实时数据（景郑平台使用）
     * @param message
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2020/11/24 9:00
    */
    public Object getChargeData(String message);
    

    /* 功能描述: 保存结束充电
     * @param message
    * @return: void
    * @Author: lj
    * @Date: 2020/11/20 15:24
    */
    public void saveFinishCharging(String message);


    /* 功能描述: 请求停止充电
     * @param message
    * @return: void
    * @Author: lj
    * @Date: 2020/11/24 9:57
    */
    public void stopCharge(String message);

    /* 功能描述: 获取停止充电的结果（景郑平台使用）
     * @param message
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2020/11/24 12:31
    */
    public Object getStopChargringResult(String message);

    /* 功能描述: 删除结束充电结果
     * @param key
    * @return: void
    * @Author: lj
    * @Date: 2020/11/25 9:53
    */
    public void deleteFinishChargringResult(String key);
    

}
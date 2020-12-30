package com.pengniao.cdzcharginginterface.service;

/* 功能描述: 主站中间件
 * @param null
* @return: 
* @Author: lj
* @Date: 2020/12/28 14:38
*/
public interface MiddleWareService {

    /* 功能描述: 保存启动充电
     * @param key
     * @param value
    * @return: java.lang.String
    * @Author: lj
    * @Date: 2020/12/28 14:39
    */
    public void saveStartUpChargring(String key, String value);

    /* 功能描述: 保存启动充电结果
     * @param key
     * @param value
    * @return: void
    * @Author: lj
    * @Date: 2020/12/28 15:21
    */
    public void saveStartUpChargringRet(String key, String value);

    /* 功能描述: 保存结束充电
     * @param key
     * @param value
    * @return: void
    * @Author: lj
    * @Date: 2020/12/28 15:25
    */
    public void saveFinishChargring(String key, String value);
    
    /* 功能描述: 保存结束充电结果
     * @param key
     * @param value
    * @return: void
    * @Author: lj
    * @Date: 2020/12/28 15:29
    */
    public void saveFinishChargringRet(String key, String value);

    /* 功能描述: 保存订单
     * @param key
     * @param value
    * @return: void
    * @Author: lj
    * @Date: 2020/12/28 15:32
    */
    public void saveChargeBill(String key, String value);
}

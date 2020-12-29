package com.pengniao.cdzcharginginterface.service;


/* 功能描述: 定时任务处理
 * @param null
* @return:
* @Author: home
* @Date: 2020/11/12 11:36
*/
public interface TaskService {


    /* 功能描述: 扫描启动充电结果
     * @param
     * @return: void
     * @Author: home
     * @Date: 2020/11/12 11:08
     */
    public void resultStartUpChargring();

    /* 功能描述: 扫描实时数据
     * @param 
    * @return: void
    * @Author: lj
    * @Date: 2020/11/23 16:33
    */
    public void chargringData();

    /* 功能描述: 扫描结束充电结果
     * @param 
    * @return: void
    * @Author: lj
    * @Date: 2020/11/25 9:23
    */
    public void resultFinishCharging();
}

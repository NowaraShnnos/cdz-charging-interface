package com.pengniao.cdzcharginginterface.task;

/* 功能描述: 定时任务接口，所有定时任务都要实现该接口
 * @param null
* @return:
* @Author: home
* @Date: 2020/8/27 10:39
*/
public interface ITask {

    /* 功能描述: 执行定时任务接口
     * @param params
    * @return: void
    * @Author: home
    * @Date: 2020/8/27 10:39
    */
    void run(String params) throws Exception;

}

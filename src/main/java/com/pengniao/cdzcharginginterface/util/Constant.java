package com.pengniao.cdzcharginginterface.util;

/**
 * @ClassNameConstant
 * @Description 常量
 * @Author
 * @Date2020/8/6 11:53
 * @Version V1.0
 **/
public class Constant {


    /* 功能描述: 定时任务状态
     * @param null
    * @return:
    * @Author: home
    * @Date: 2020/8/25 10:33
    */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
package com.pengniao.cdzcharginginterface.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @ClassNameChargingJson
 * @Description 充电数据
 * @Author
 * @Date2020/11/18 15:26
 * @Version V1.0
 **/
public class ChargingObject {

    private String powerno; // 设备号

    private String phoneno; // 用户手机号码

    private Integer doway; // 启动方式,默认为0;只有当dowhat为1或2(即启动充电)时,才有效.  0:未知,1:安卓APP,2:苹果APP,3:微信小程序,4:支付宝小程序...8:刷卡启动,9:车辆VIN自启动

    private Integer gunno; // 抢号,单枪为1，默认1,取值范围为1~32

    private Integer dowhat; //操作项

    private Integer platform; // 平台     0-景郑快充  1-河南省平台  2-小桔

    private String starttime; // 充电启动时间

    public String getPowerno() {
        return powerno;
    }

    public void setPowerno(String powerno) {
        this.powerno = powerno;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public Integer getDoway() {
        return doway;
    }

    public void setDoway(Integer doway) {
        this.doway = doway;
    }

    public Integer getGunno() {
        return gunno;
    }

    public void setGunno(Integer gunno) {
        this.gunno = gunno;
    }

    public Integer getDowhat() {
        return dowhat;
    }

    public void setDowhat(Integer dowhat) {
        this.dowhat = dowhat;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }
}
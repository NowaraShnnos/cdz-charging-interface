package com.pengniao.cdzcharginginterface.entity;

/**
 * @program: cdz-charging-interface
 * @description: 启动充电接口
 * @author: lj
 * @create: 2020-11-20 09:43
 **/
public class StartChargeResult {

    private String startChargeSeq; // 充电订单号

    private Integer startChargeSeqStat; // 充电订单状态

    private String connectorID; // 充电设备接口编码

    private String startTime; // 充电启动时间

    public String getStartChargeSeq() {
        return startChargeSeq;
    }

    public void setStartChargeSeq(String startChargeSeq) {
        this.startChargeSeq = startChargeSeq;
    }

    public Integer getStartChargeSeqStat() {
        return startChargeSeqStat;
    }

    public void setStartChargeSeqStat(Integer startChargeSeqStat) {
        this.startChargeSeqStat = startChargeSeqStat;
    }

    public String getConnectorID() {
        return connectorID;
    }

    public void setConnectorID(String connectorID) {
        this.connectorID = connectorID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}

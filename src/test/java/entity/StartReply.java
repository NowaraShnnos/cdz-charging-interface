package entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.stereotype.Component;

/**
 * @program: cdz-charging-interface
 * @description: 启动充电结果类
 * @author: lj
 * @create: 2020-11-23 10:14
 **/
@Component
public class StartReply {
    @JSONField(name = "StartChargeSeq")
    private String startChargeSeq; // 订单号

    @JSONField(name = "StartChargeSeqStat")
    private Integer startChargeSeqStat; //  充电订单状态

    @JSONField(name = "ConnectorID")
    private String connectorID; // 充电设备号

    @JSONField(name = "StartTime")
    private String startTime; // 充电启动时间

    @JSONField(name = "IdentCode")
    private String identCode; // 停止充电验证码

    @JSONField(name = "Ret")
    private Integer ret; // 结果码

    @JSONField(name = "RetMessage")
    private String retMessage; // 结果描述


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

    public String getIdentCode() {
        return identCode;
    }

    public void setIdentCode(String identCode) {
        this.identCode = identCode;
    }

    public Integer getRet() {
        return ret;
    }

    public void setRet(Integer ret) {
        this.ret = ret;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }
}

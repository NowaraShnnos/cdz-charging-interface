package entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.stereotype.Component;

/**
 * @program: cdz-charging-interface
 * @description: 请求停止充电
 * @author: lj
 * @create: 2020-11-23 10:48
 **/
@Component
public class FinishCharging {

    @JSONField(name = "StartChargeSeq")
    private String startChargeSeq; // 订单号

    @JSONField(name = "ConnectorID")
    private String connectorID; // 充电设备编号

    @JSONField(name = "FinishTime")
    private String finishTime; //  请求时间

    @JSONField(name = "Platform")
    private Integer platform; // 平台

    public String getStartChargeSeq() {
        return startChargeSeq;
    }

    public void setStartChargeSeq(String startChargeSeq) {
        this.startChargeSeq = startChargeSeq;
    }

    public String getConnectorID() {
        return connectorID;
    }

    public void setConnectorID(String connectorID) {
        this.connectorID = connectorID;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }
}

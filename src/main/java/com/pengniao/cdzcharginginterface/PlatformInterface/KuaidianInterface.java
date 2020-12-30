package com.pengniao.cdzcharginginterface.PlatformInterface;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pengniao.cdzcharginginterface.response.PlatformInterfaceResult;
import com.pengniao.cdzcharginginterface.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: cdz-charging-interface
 * @description: 快电平台接口
 * @author: lj
 * @create: 2020-11-20 09:09
 **/
@Component
public class KuaidianInterface {

    @Value("${custom-parameters.kuaidian-request}")
    private String kuaidian_request; // 快电请求地址

    /* 功能描述: 发送启动充电结果
     * @param startChargeSeq 充电订单号
     * @param startChargeSeqStat 充电订单状态
     * @param connectorId 充电设备接口编码
     * @param startTime 充电启动时间
    * @return: void
    * @Author: lj
    * @Date: 2020/11/20 11:16
    */
    public PlatformInterfaceResult sendStartupCharging(String startChargeSeq, Integer startChargeSeqStat, String connectorId, String startTime, String identCode){

        Map<String, String> param = new HashMap<String, String>();
        param.put("StartChargeSeq", startChargeSeq);
        param.put("StartChargeSeqStat", String.valueOf(startChargeSeqStat));
        param.put("ConnectorID", connectorId);
        param.put("StartTime",startTime);
        param.put("IdentCode",identCode);

        String url = kuaidian_request + "notification_start_charge_result";
        return HttpClientUtil.doPost(url, param);
    }

    /* 功能描述: 发送实时数据
     * @param startChargeSeq 充电订单号
     * @param startChargeSeqStat 充电订单状态
     * @param connectorId 充电设备接口编码
     * @param connectorStatus 充电设备接口状态 1：空闲；2：占用（未充电）；3：占用（充电中）；4：占用（预约锁定）；255：故障
     * @param currentA A相电流
     * @param voltageA A相电压
     * @param soc 充电百分比
     * @param startTime 开始充电时间
     * @param endTime 本次采样时间
     * @param totalPower 累计充电量
     * @param elecMoney 电费
     * @param seviceMoney 服务费
     * @param totalMoney 总费用
    * @return: com.pengniao.cdzcharginginterface.response.PlatformInterfaceResult
    * @Author: lj
    * @Date: 2020/11/23 16:59
    */
    public PlatformInterfaceResult sendChargringData(String startChargeSeq, Integer startChargeSeqStat, String connectorId
            , Integer connectorStatus
            , BigDecimal currentA, BigDecimal voltageA, BigDecimal soc, String startTime, String endTime, BigDecimal totalPower
            , BigDecimal elecMoney, BigDecimal seviceMoney, BigDecimal totalMoney){

        Map<String, String> param = new HashMap<String, String>();
        param.put("StartChargeSeq", startChargeSeq);
        param.put("StartChargeSeqStat", String.valueOf(startChargeSeqStat));
        param.put("ConnectorID", connectorId);
        param.put("ConnectorSatatus",String.valueOf(connectorStatus));
        param.put("CurrentA",String.valueOf(currentA));
        param.put("VoltageA",String.valueOf(voltageA));
        param.put("Soc",String.valueOf(soc));
        param.put("StartTime",String.valueOf(startTime));
        param.put("EndTime",String.valueOf(endTime));
        param.put("TotalPower",String.valueOf(totalPower));
        param.put("ElecMoney",String.valueOf(elecMoney));
        param.put("SeviceMoney",String.valueOf(seviceMoney));
        param.put("TotalMoney",String.valueOf(totalMoney));

        String url = kuaidian_request + "notification_equip_charge_status";
        return HttpClientUtil.doPost(url, param);
    }

    /* 功能描述: 发送结束充电结果
     * @param startChargeSeq
     * @param startChargeSeqStat
     * @param succStat
     * @param failReason
    * @return: com.pengniao.cdzcharginginterface.response.PlatformInterfaceResult
    * @Author: lj
    * @Date: 2020/11/25 9:51
    */
    public PlatformInterfaceResult sendFinishChargring(String startChargeSeq, Integer startChargeSeqStat, String connectorID
            ,Integer succStat, Integer failReason){

        Map<String, String> param = new HashMap<String, String>();
        param.put("StartChargeSeq", startChargeSeq);
        param.put("StartChargeSeqStat", String.valueOf(startChargeSeqStat));
        param.put("ConnectorID", String.valueOf(connectorID));
        param.put("SuccStat", String.valueOf(succStat));
        param.put("FailReason", String.valueOf(failReason));

        String url = kuaidian_request + "notification_stop_charge_result";
        return HttpClientUtil.doPost(url, param);
    }
}

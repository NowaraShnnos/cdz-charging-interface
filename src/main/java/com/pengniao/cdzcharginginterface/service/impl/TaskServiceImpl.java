package com.pengniao.cdzcharginginterface.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pengniao.cdzcharginginterface.PlatformInterface.KuaidianInterface;
import com.pengniao.cdzcharginginterface.response.PlatformInterfaceResult;
import com.pengniao.cdzcharginginterface.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassNameTaskServiceImpl
 * @Description
 * @Author
 * @Date2020/11/12 11:46
 * @Version V1.0
 **/

@RequestMapping("redis")
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private ChargingServiceImpl chargingServiceImpl;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private KuaidianInterface kuaidianInterface;


    /* 功能描述: 扫描启动充电结果
     * @param
    * @return: void
    * @Author: lj
    * @Date: 2020/12/23 14:37
    */
    @Override
    public void resultStartUpChargring() {

        Map entriesMap = redisTemplate.boundHashOps("startReply").entries();

        Iterator<Map.Entry<Integer, Integer>> entries = entriesMap.entrySet().iterator();

        while (entries.hasNext()) {

            Map.Entry<Integer, Integer> entry = entries.next();
            String key = String.valueOf(entry.getKey()); // 转换key值
            String value = String.valueOf(entry.getValue()); // 转换value值
            String deviceNo = key.split(":")[0]; // 获取设备编号
            String platform = key.split(":")[1]; // 获取来源平台 1-景郑快充  2-河南省平台  3-快电

            if(platform.equals("2")){
                // 河南省平台
            }else if(platform.equals("3")){
                // 快电
                JSONObject jsonObject = JSON.parseObject(value);
                String startChargeSeq = jsonObject.getString("StartChargeSeq"); // 充电订单号
                Integer startChargeSeqStat = jsonObject.getInteger("StartChargeSeqStat"); // 充电订单状态 1-启动中；2-充电中；3- 停止中；4-已结束；5-未知
                String connectorId = jsonObject.getString("ConnectorID") ; // 充电设备接口编码
                String startTime = jsonObject.getString("StartTime"); // 充电启动时间
                String identCode = jsonObject.getString("IdentCode"); // 停止充电验证码
                PlatformInterfaceResult result = kuaidianInterface.sendStartupCharging(startChargeSeq, startChargeSeqStat, connectorId, startTime, identCode);

                if(result.getCode() == 0){
                    // 推送成功，删除当前用户启动结果
//                    System.out.println("推送成功，删除当前用户启动结果");
//                    System.out.println(String.valueOf(entry.getKey()));
                    chargingServiceImpl.deleteStartUpChargringResult(key);
                }
            }
        }
    }

    /* 功能描述: 扫描充电实时数据
     * @param 
    * @return: void
    * @Author: lj
    * @Date: 2020/11/23 16:34
    */
    @Override
    public void chargringData() {
        Map entriesMap = redisTemplate.boundHashOps("ChargeBill").entries();

        Iterator<Map.Entry<Integer, Integer>> entries = entriesMap.entrySet().iterator();

        while (entries.hasNext()) {

            Map.Entry<Integer, Integer> entry = entries.next();
            String key = String.valueOf(entry.getKey()); // 转换key值
            String value = String.valueOf(entry.getValue()); // 转换value值
            String platform = key.split(":")[1]; // 获取来源平台 1-景郑快充  2-河南省平台  3-快电

            if(platform.equals("2")){
                // 河南省平台
            }else if(platform.equals("1")){
                // 快电
                JSONObject jsonObject = JSON.parseObject(value);
                String startChargeSeq = jsonObject.getString("StartChargeSeq"); // 充电订单号
                Integer startChargeSeqStat = jsonObject.getInteger("StartChargeSeqStat"); // 充电订单状态 1-启动中；2-充电中；3- 停止中；4-已结束；5-未知
                String connectorId = jsonObject.getString("ConnectorID") ; // 充电设备接口编码
                Integer connectorStatus = jsonObject.getInteger("ConnectorStatus"); // 充电设备接口状态
                BigDecimal currentA = jsonObject.getBigDecimal("CurrentA"); // A相电流
                BigDecimal voltageA = jsonObject.getBigDecimal("VoltageA"); // A相电压
                BigDecimal soc = jsonObject.getBigDecimal("Soc"); // 充电百分比
                String startTime = jsonObject.getString("StartTime"); // 开始充电时间
                String endTime = jsonObject.getString("EndTime"); // 本次采样时间
                BigDecimal totalPower = jsonObject.getBigDecimal("TotalPower"); // 累计充电量
                BigDecimal elecMoney = jsonObject.getBigDecimal("ElecMoney"); // 电费
                BigDecimal seviceMoney = jsonObject.getBigDecimal("SeviceMoney"); // 服务费
                BigDecimal totalMoney = jsonObject.getBigDecimal("TotalMoney"); // 总费用


                PlatformInterfaceResult result = kuaidianInterface.sendChargringData(startChargeSeq, startChargeSeqStat, connectorId, connectorStatus
                        , currentA, voltageA, soc, startTime, endTime, totalPower, elecMoney, seviceMoney, totalMoney);

                if(result.getCode() == 0){
                    //System.out.println("实时数据推送成功");
                }
            }
        }
    }

    /* 功能描述: 扫描结束充电结果
     * @param
    * @return: void
    * @Author: lj
    * @Date: 2020/11/25 9:24
    */
    @Override
    public void resultFinishCharging() {

        Map entriesMap = redisTemplate.boundHashOps("finishReply").entries();

        Iterator<Map.Entry<Integer, Integer>> entries = entriesMap.entrySet().iterator();

        while (entries.hasNext()) {

            Map.Entry<Integer, Integer> entry = entries.next();

            String key = String.valueOf(entry.getKey()); // 转换key值
            String value = String.valueOf(entry.getValue()); // 转换value值
//            String startChargeSeq = key.split(":")[0]; // 获取订单编号
            String platform = key.split(":")[1]; // 获取来源平台 1-景郑快充  2-河南省平台  3-快电

            if(platform.equals("2")){
                // TODO 河南省平台
            }else if(platform.equals("3")){
                // TODO 快电
                JSONObject jsonObject = JSON.parseObject(value);
                String startChargeSeq = jsonObject.getString("StartChargeSeq"); // 充电订单号
                Integer startChargeSeqStat = jsonObject.getInteger("StartChargeSeqStat"); // 充电订单状态 1-启动中；2-充电中；3- 停止中；4-已结束；5-未知
                String connectorID = jsonObject.getString("ConnectorID") ; // 设备编号
                Integer succStat = jsonObject.getInteger("SuccStat") ; // 操作结果
                Integer failReason = jsonObject.getInteger("FailReason"); // 失败原因
                PlatformInterfaceResult result = kuaidianInterface.sendFinishChargring(startChargeSeq, startChargeSeqStat, connectorID, succStat, failReason);
                if(result.getCode() == 0){
                    // 推送成功，删除当前用户启动结果
//                    System.out.println("推送成功，删除当前用户停止充电结果");
//                    System.out.println(String.valueOf(entry.getKey()));
                    chargingServiceImpl.deleteFinishChargringResult(key);
                }
            }
        }

    }
}
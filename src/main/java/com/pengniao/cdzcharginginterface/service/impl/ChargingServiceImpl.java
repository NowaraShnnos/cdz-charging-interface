package com.pengniao.cdzcharginginterface.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pengniao.cdzcharginginterface.service.ChargingService;
import com.pengniao.cdzcharginginterface.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassNameChargingServiceImpl
 * @Description
 * @Author
 * @Date2020/11/11 15:56
 * @Version V1.0
 **/

@RequestMapping("redis")
@Service
public class ChargingServiceImpl implements ChargingService {


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /* 功能描述: 保存启动充电
     * @param deviceNo
     * @param platform
     * @param jsonObject
    * @return: void
    * @Author: home
    * @Date: 2020/11/11 15:57
    */
    @Override
    public String saveStartUpChargring(String  message) {

        JSONObject jsonObject = JSONObject.parseObject(message);
        String connectorId = jsonObject.getString("ConnectorID"); // 设备编号
        Integer platform = jsonObject.getInteger("Platform"); //  1 - 景郑快充  2 - 河南省平台  3 - 快电

        JSONObject chargingObject = jsonObject.getJSONObject("Detail"); //  启动充电json数据
        ((JSONObject) chargingObject).put("StartTime", DateTimeUtil.getSystemTime()); // json数据中增加启动充电时间

        // 生成订单编号 （景郑平台使用） 桩号+手机号码+年月日时分秒
        if(platform == 1){
            String phoneNo = chargingObject.getString("PhoneNo");
            ((JSONObject) chargingObject).put("StartChargeSeq",  connectorId + phoneNo + DateTimeUtil.getSystemTimeString().substring(2,DateTimeUtil.getSystemTimeString().length())); // json数据中增加订单编号
        }

        //保存启动充电
        redisTemplate.opsForHash().put("startCharging", connectorId+":"+platform, JSON.toJSONString(chargingObject));

        // 返回订单编号
        String startChargeSeq = chargingObject.getString("StartChargeSeq");
        return startChargeSeq;
    }

    /* 功能描述: 删除启动充电结果
     * @param key
    * @return: void
    * @Author: lj
    * @Date: 2020/11/20 14:51
    */
    @Override
    public void deleteStartUpChargringResult(String key) {
        redisTemplate.opsForHash().delete("startReply",key);
    }

    /* 功能描述: 获取启动充电结果（景郑平台使用）
     * @param connectorId 充电设备编号
     * @param platform 平台
    * @return: void
    * @Author: lj
    * @Date: 2020/11/23 11:45
    */
    @Override
    public JSONObject getStartUpChargringResult(String message)
    {
        JSONObject jsonObject = JSONObject.parseObject(message);
        String startChargeSeq = jsonObject.getString("StartChargeSeq"); // 订单编号
        Integer platform = jsonObject.getInteger("Platform"); //  1 - 景郑快充  2 - 河南省平台  3 - 快电
        String key = startChargeSeq+":"+platform;
        Object mapValue = redisTemplate.opsForHash().get("startReply", key);
        return JSONObject.parseObject(String.valueOf(mapValue));
    }

    /* 功能描述: 获取充电实时数据（景郑平台使用）
     * @param message
    * @return: com.alibaba.fastjson.JSONObject
    * @Author: lj
    * @Date: 2020/11/24 9:01
    */
    @Override
    public JSONObject getChargeData(String message)
    {
        JSONObject jsonObject = JSONObject.parseObject(message);
        String StartChargeSeq = jsonObject.getString("StartChargeSeq"); // 订单编号
        Integer platform = jsonObject.getInteger("Platform"); //  1 - 景郑快充  2 - 河南省平台  3 - 快电
        String key = StartChargeSeq+":"+platform;
        System.out.println("key："+key);
        Object mapValue = redisTemplate.opsForHash().get("ChargeBill", key);
        System.out.println(mapValue);
        return JSONObject.parseObject(String.valueOf(mapValue));
    }

    /* 功能描述: 保存结束充电
     * @param message
    * @return: void
    * @Author: lj
    * @Date: 2020/11/20 15:25
    */
    @Override
    public void saveFinishCharging(String message) {
        JSONObject jsonObject = JSONObject.parseObject(message);
        String connectorId = jsonObject.getString("ConnectorID"); // 设备编号
        Integer platform = jsonObject.getInteger("Platform"); //  1 - 景郑快充  2 - 河南省平台  3 - 快电
        JSONObject finishChargingObject = jsonObject.getJSONObject("Detail"); //  结束充电json数据
        ((JSONObject) finishChargingObject).put("FinishTime", DateTimeUtil.getSystemTime()); // json数据中增加结束充电时间

        //保存结束充电
        redisTemplate.opsForHash().put("finishCharging", connectorId+":"+platform, JSON.toJSONString(finishChargingObject));
    }

    /* 功能描述: 请求停止充电
     * @param message
    * @return: void
    * @Author: lj
    * @Date: 2020/11/24 9:57
    */
    @Override
    public void stopCharge(String message) {
        JSONObject jsonObject = JSONObject.parseObject(message);
        String startChargeSeq = jsonObject.getString("StartChargeSeq"); // 订单编号
        Integer platform = jsonObject.getInteger("Platform"); //  1 - 景郑快充  2 - 河南省平台  3 - 快电

        JSONObject stopChargingObject = jsonObject.getJSONObject("Detail"); //  启动充电json数据
        ((JSONObject) stopChargingObject).put("StartTime", DateTimeUtil.getSystemTime()); // json数据中增加启动充电时间

        //保存停止充电
        redisTemplate.opsForHash().put("stopCharging", startChargeSeq+":"+platform, JSON.toJSONString(stopChargingObject));
    }

    /* 功能描述: 获取停止充电的结果
     * @param message
    * @return: java.lang.Object
    * @Author: lj
    * @Date: 2020/11/24 12:32
    */
    @Override
    public Object getStopChargringResult(String message) {
        JSONObject jsonObject = JSONObject.parseObject(message);
        String startChargeSeq = jsonObject.getString("StartChargeSeq"); // 订单编号
        Integer platform = jsonObject.getInteger("Platform"); //  1 - 景郑快充  2 - 河南省平台  3 - 快电
        String key = startChargeSeq+":"+platform;
//        System.out.println("key:"+key);
        Object mapValue = redisTemplate.opsForHash().get("finishReply", key);
//        System.out.println(mapValue);
        return JSONObject.parseObject(String.valueOf(mapValue));
    }

    @Override
    public void deleteFinishChargringResult(String key) {
        redisTemplate.opsForHash().delete("finishReply",key);
    }
}
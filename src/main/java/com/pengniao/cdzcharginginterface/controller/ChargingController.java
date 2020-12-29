package com.pengniao.cdzcharginginterface.controller;

import com.alibaba.fastjson.JSONObject;
import com.pengniao.cdzcharginginterface.response.Result;
import com.pengniao.cdzcharginginterface.service.ChargingService;
import com.pengniao.cdzcharginginterface.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassNameChargingController
 * @Description 充电操作
 * @Author
 * @Date2020/11/11 18:02
 * @Version V1.0
 **/
@RequestMapping("charging")
@RestController
public class ChargingController {


    @Autowired
    private ChargingService chargingService;

    /* 功能描述: 请求启动充电
     * @param deviceNo 设备号
     * @param message json 启动充电数据
     * @param platform 1 - 景郑快充，2-河南平台，3-小桔平台
    * @return: com.pengniao.cdzcharginginterface.response.Result
    * @Author: home   String deviceNo, String message, Integer platform    @RequestBody String message
    * @Date: 2020/11/11 18:20
    */
    @PostMapping("startCharging")
    public Result startCharging( @RequestBody String message){
//        System.out.println("请求启动充电");
//        System.out.println(message);
        String startChargeSeq = chargingService.saveStartUpChargring(message);
        return Result.SUCCESS().put("startChargeSeq", startChargeSeq);
    }

    /* 功能描述: 请求结束充电
     * @param message
    * @return: com.pengniao.cdzcharginginterface.response.Result
    * @Author: lj
    * @Date: 2020/11/20 15:23
    */
    @PostMapping("finishCharging")
    public Result finishCharging(@RequestBody String message){
//        System.out.println("请求结束充电");
        chargingService.saveFinishCharging(message);
        return Result.SUCCESS();
    }

    /* 功能描述: 获取启动充电结果（景郑平台使用）
     * @param message
    * @return: com.pengniao.cdzcharginginterface.response.Result
    * @Author: lj
    * @Date: 2020/11/23 11:30
    */
    @PostMapping("getStartChargingRet")
    public Result getStartChargingRet(@RequestBody String message){
//        System.out.println("获取启动充电结果");
        Object res = chargingService.getStartUpChargringResult(message);
        return Result.SUCCESS().put("data",res);
    }

    /* 功能描述: 获取充电实时数据
     * @param message
    * @return: com.pengniao.cdzcharginginterface.response.Result
    * @Author: lj
    * @Date: 2020/11/24 8:58
    */
    @PostMapping("getChargeData")
    public Result getChargeData(@RequestBody String message){
//        System.out.println("获取充电实时数据");
        System.out.println("message = "+ message);
        Object res = chargingService.getChargeData(message);
        return Result.SUCCESS().put("data",res);
    }

    /* 功能描述: 请求停止充电
     * @param message
    * @return: com.pengniao.cdzcharginginterface.response.Result
    * @Author: lj
    * @Date: 2020/11/24 9:54
    */
    @PostMapping("stopCharge")
    public Result stopCharge(@RequestBody String message){
        System.out.println("请求停止充电");
        System.out.println(message);
        chargingService.stopCharge(message);
        return Result.SUCCESS();
    }

    /* 功能描述: 请求停止充电结果
     * @param message
    * @return: com.pengniao.cdzcharginginterface.response.Result
    * @Author: lj
    * @Date: 2020/11/24 12:34
    */
    @PostMapping("getStopChargeRet")
    public Result getStopChargeRet(@RequestBody String message){
        System.out.println("获取停止充电结果");
        System.out.println(message);
        Object res = chargingService.getStopChargringResult(message);
        System.out.println(res);
        return Result.SUCCESS().put("data",res);
    }

}
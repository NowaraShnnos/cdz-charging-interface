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

    /* 功能描述: 启动充电
     * @param deviceNo 设备号
     * @param message json 启动充电数据
     * @param platform 1 - 景郑快充，2-河南平台，3-小桔平台
    * @return: com.pengniao.cdzcharginginterface.response.Result
    * @Author: home   String deviceNo, String message, Integer platform    @RequestBody String message
    * @Date: 2020/11/11 18:20
    */
    @PostMapping("startCharging")
    public Result startCharging( @RequestBody String message){

//        System.out.println("message="+message);


        chargingService.saveStartUpChargring(message);
        return Result.SUCCESS();
    }



}
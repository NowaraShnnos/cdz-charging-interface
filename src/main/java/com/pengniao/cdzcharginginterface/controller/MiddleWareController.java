package com.pengniao.cdzcharginginterface.controller;

import com.pengniao.cdzcharginginterface.response.Result;
import com.pengniao.cdzcharginginterface.service.MiddleWareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: cdz-charging-interface
 * @description: 主站中间件
 * @author: lj
 * @create: 2020-12-28 14:32
 **/
@RequestMapping("middleware-charging")
@RestController
public class MiddleWareController {

    @Autowired
    private MiddleWareService middleWareService;

    /* 功能描述: 保存启动充电
     * @param key
     * @param value
    * @return: com.pengniao.cdzcharginginterface.response.Result
    * @Author: lj
    * @Date: 2020/12/28 15:20
    */
    @GetMapping("saveStartUpChargring")
    public Result saveStartUpChargring(String key, String value){
        System.out.println("中间件发送启动充电接口");
        System.out.println("key:"+key+"|"+"value:"+value);
        middleWareService.saveStartUpChargring(key, value);
        return Result.SUCCESS();
    }

    /* 功能描述: 保存启动充电结果
     * @param key
     * @param value
    * @return: com.pengniao.cdzcharginginterface.response.Result
    * @Author: lj
    * @Date: 2020/12/28 15:20
    */
    @GetMapping("saveStartUpChargringRet")
    public Result saveStartUpChargringRet(String key, String value){
        System.out.println("中间件发送启动充电结果接口");
        System.out.println("key:"+key+"|"+"value:"+value);
        middleWareService.saveStartUpChargringRet(key, value);
        return Result.SUCCESS();
    }

    /* 功能描述: 保存结束充电
     * @param key
     * @param value
    * @return: com.pengniao.cdzcharginginterface.response.Result
    * @Author: lj
    * @Date: 2020/12/28 15:27
    */
    @GetMapping("saveFinishChargring")
    public Result saveFinishChargring(String key, String value){
        System.out.println("中间件发送结束充电接口");
        System.out.println("key:"+key+"|"+"value:"+value);
        middleWareService.saveFinishChargring(key, value);
        return Result.SUCCESS();
    }

    /* 功能描述: 保存结束充电结果
     * @param key
     * @param value
    * @return: com.pengniao.cdzcharginginterface.response.Result
    * @Author: lj
    * @Date: 2020/12/28 15:30
    */
    @GetMapping("saveFinishChargringRet")
    public Result saveFinishChargringRet(String key, String value){
        System.out.println("中间件发送结束充电结果接口");
        System.out.println("key:"+key+"|"+"value:"+value);
        middleWareService.saveFinishChargringRet(key, value);
        return Result.SUCCESS();
    }

    /* 功能描述: 保存订单
     * @param key
     * @param value
    * @return: com.pengniao.cdzcharginginterface.response.Result
    * @Author: lj
    * @Date: 2020/12/28 15:31
    */
    @GetMapping("saveChargeBill")
    public Result saveChargeBill(String key, String value){
        System.out.println("中间件发送订单结果接口");
        System.out.println("key:"+key+"|"+"value:"+value);
        middleWareService.saveChargeBill(key, value);
        return Result.SUCCESS();
    }




}

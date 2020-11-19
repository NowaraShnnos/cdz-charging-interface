package com.pengniao.cdzcharginginterface.service.impl;

import com.pengniao.cdzcharginginterface.service.TaskService;
import org.omg.CORBA.OBJECT_NOT_EXIST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private RedisTemplate<String,Object> redisTemplate;

    //   扫描启动充电结果
    @Override
    public void resultStartUpChargring() {

        Map entriesMap = redisTemplate.boundHashOps("startCharging").entries();

        Iterator<Map.Entry<Integer, Integer>> entries = entriesMap.entrySet().iterator();

        while (entries.hasNext()) {

            Map.Entry<Integer, Integer> entry = entries.next();

            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }
}
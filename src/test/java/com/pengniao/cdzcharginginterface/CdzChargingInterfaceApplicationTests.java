package com.pengniao.cdzcharginginterface;

import com.pengniao.cdzcharginginterface.service.ChargingService;
import com.pengniao.cdzcharginginterface.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CdzChargingInterfaceApplicationTests {

    @Autowired
    private ChargingService chargingService;

    @Autowired
    private TaskService taskService;

//    @Test
//    public void saveStartUpChargring(){
//        String deviceNo = "03710001";
//        String message = "{98989880}";
//        //chargingService.saveStartUpChargring(deviceNo,message);
//    }


    @Test
    public void resultStartUpChargring(){
        taskService.resultStartUpChargring();
    }
}

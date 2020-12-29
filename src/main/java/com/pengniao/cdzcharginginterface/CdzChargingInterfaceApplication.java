package com.pengniao.cdzcharginginterface;

import com.pengniao.cdzcharginginterface.controller.AppChargingController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@MapperScan("com.pengniao.cdzcharginginterface.mapper")
@SpringBootApplication
public class CdzChargingInterfaceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(CdzChargingInterfaceApplication.class, args);
    }

}

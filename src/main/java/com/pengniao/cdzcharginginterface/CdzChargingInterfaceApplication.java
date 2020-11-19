package com.pengniao.cdzcharginginterface;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.pengniao.cdzcharginginterface.mapper")
@SpringBootApplication
public class CdzChargingInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CdzChargingInterfaceApplication.class, args);
    }

}

package com.pengniao.cdzcharginginterface.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @program: cdz-charging-interface
 * @description: WebSocket配置
 * @author: lj
 * @create: 2020-12-25 10:49
 **/

@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}

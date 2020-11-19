package com.pengniao.cdzcharginginterface.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfiguration.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("======================================add interceptors");
        registry.addInterceptor(new LoginRequiredInterceptor()).excludePathPatterns(Arrays.asList("/static/**", "/public/**", "/templates/**", "/mybatis/**"));
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedHeaders("*");
    }


}

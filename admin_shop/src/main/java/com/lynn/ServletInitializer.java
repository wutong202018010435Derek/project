package com.lynn;

import com.lynn.mqtt.MqttConsumerClient;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.annotation.Resource;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        if (mqttConsumerClient != null)
//            mqttConsumerClient.connect();
        return application.sources(LynnApplication.class);

    }

    @Resource
    private MqttConsumerClient mqttConsumerClient;

}

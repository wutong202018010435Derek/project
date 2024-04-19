package com.lynn;

import com.lynn.mqtt.MqttConsumerClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.annotation.Resource;

//@SpringBootApplication
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class LynnApplication {

    public static void main(String[] args) {
        SpringApplication.run(LynnApplication.class, args);
//https://lbs.amap.com/tools/picker    坐标拾取器 | 高德地图API
    }

}


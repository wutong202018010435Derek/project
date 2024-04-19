package com.lynn.admin.controller;

import com.lynn.mqtt.MqttConsumerClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class MyController {


    // http://localhost:8015/testing本地测试

    // 用于测试服务器是否开启
    @RequestMapping("/app/testing")
    public String hello() {
//		logger.info("logback 成功了");
//        logger.error("logback111 成功了");
        System.err.println("测试看看--admin-bg");

        return "Hello world 2023";
    }




}

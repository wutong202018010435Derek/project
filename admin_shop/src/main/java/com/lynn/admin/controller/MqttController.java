package com.lynn.admin.controller;


import com.alibaba.fastjson.JSONObject;

import com.lynn.mqtt.MqttConsumerClient;
import com.lynn.web.model.DeviceDataModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class MqttController {

//    @Resource
//    private MqttConsumerClient mqttConsumerClient;
//
//    @RequestMapping("/app1/mqtt/send")
//    public Map<String, Object> mqttSend(HttpServletRequest request) {
//        String sentContent = request.getParameter("sentContent");
//
//        boolean bl = mqttConsumerClient.pushMsg(sentContent);
//        RestControllerHelper helper = new RestControllerHelper();
//        helper.setCode(bl ? RestControllerHelper.SUCCESS : RestControllerHelper.FAIL_SIGN);
//        helper.setMsg(bl ? "发送成功" : "发送失败");
//        return helper.toJsonMap();
////        return bl ? "发送成功" : "发送失败";
//    }
//
//    @PostMapping("/app1/mqtt/send")
//    public Map<String, Object> mqttSendPost(@RequestBody DeviceDataModel deviceDataModel) {
//        boolean bl = false;
//        if (deviceDataModel != null)
//            bl = mqttConsumerClient.pushMsg(JSONObject.toJSONString(deviceDataModel));
//        RestControllerHelper helper = new RestControllerHelper();
//        helper.setCode(bl ? RestControllerHelper.SUCCESS : RestControllerHelper.FAIL_SIGN);
//        helper.setMsg(bl ? "发送成功" : "发送失败");
//        return helper.toJsonMap();
////        return bl ? "发送成功" : "发送失败";
//    }



}

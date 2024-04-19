package com.lynn.mqtt;


import com.alibaba.fastjson.JSONObject;
import com.lynn.common.constant.Constants;
import com.lynn.system.service.ISysDataService;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class MqttConsumerCallback implements MqttCallback {

    @Value("${spring.mqtt.client.id}")
    private String clientId;

    /**
     * 断开连接回调
     */
    @Override
    public void connectionLost(Throwable throwable) {

        System.out.println(clientId + "与服务器断开连接...");
    }

    /**
     * 监听消息回调
     */
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {

        System.out.println("接收消息主题：{}" + topic);

        System.out.println("接收消息Qos：{}" + mqttMessage.getQos());

        System.out.println("接收消息内容：{}" + new String(mqttMessage.getPayload()));

        System.out.println("接收消息retained：{}" + mqttMessage.isRetained());


    }





    /**
     * 发送消息完成回调
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}

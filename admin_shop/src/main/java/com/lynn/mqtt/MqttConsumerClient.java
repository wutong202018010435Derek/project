package com.lynn.mqtt;


import com.alibaba.fastjson.JSONObject;
import com.lynn.common.constant.Constants;
import com.lynn.common.core.domain.entity.SysData;
import com.lynn.common.core.domain.entity.SysDictType;
import com.lynn.system.service.ISysDataService;
import com.lynn.system.service.ISysDictTypeService;
import com.lynn.web.model.DeviceDataModel;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

import java.util.Date;

import static com.lynn.common.constant.Constants.SEDTOPIC;

@Component
public class MqttConsumerClient {

//    @Value("${spring.mqtt.username}")
//    private String username;
//    @Value("${spring.mqtt.password}")
//    private String password;
//    @Value("${spring.mqtt.url}")
//    private String hostUrl;
//    @Value("${spring.mqtt.client.id}")
//    private String clientId;
//    @Value("${spring.mqtt.default.topic}")
//    private String defaultTopic;
//
//    /**
//     * mqtt 客户端
//     */
//    private MqttClient mqttClient;
//
//    /**
//     * 初始化客户端
//     */
//    @PostConstruct
//    public void init() {
//        // 初始化逻辑
//        connect();
//    }
//
//    /**
//     * 初始化客户端连接服务器
//     */
//    public void connect() {
//        try {
//            // 创建服务器连接
//            mqttClient = new MqttClient(hostUrl, clientId, new MemoryPersistence());
//
//            // 配置连接参数
//            MqttConnectOptions options = new MqttConnectOptions();
//            // 是否清空session，设置为false表示服务器会保留客户端的连接记录，客户端重连之后能获取到服务器在客户端断开连接期间推送的消息
//            // 设置为true表示每次连接到服务端都是以新的身份
//            options.setCleanSession(true);
//            // 配置用户名和密码
//            options.setUserName(username);
//            options.setPassword(password.toCharArray());
//            // 设置超时时间，单位为秒
//            options.setConnectionTimeout(100);
//            // 设置心跳时间 单位为秒，表示服务器每隔1.5*20秒的时间向客户端发送心跳判断客户端是否在线
//            options.setKeepAliveInterval(20);
//            // 设置遗嘱消息的话题，若客户端和服务器之间的连接意外断开，服务器将发布客户端的遗嘱信息
//            options.setWill("willTopic", (clientId + "与服务器断开连接").getBytes(), 0, false);
//            // 设置回调
//            mqttClient.setCallback(mqttConsumerCallback);
//            // 执行连接
//            mqttClient.connect(options);
//
//            // 配置订阅主题
//            int[] qos = {1, 1, 1};
//            String[] topics = {"test", "test1", Constants.GET_TOPIC};
//            // 执行订阅
//            mqttClient.subscribe(topics, qos);
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Autowired
//    private ISysDataService sysDataService;
//
//    @Autowired
//    private ISysDictTypeService dictTypeService;
//
//    MqttConsumerCallback mqttConsumerCallback = new MqttConsumerCallback() {
//
//        @Override
//        public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
////            super.messageArrived(topic, mqttMessage);
//
//            if (topic.equals(Constants.GET_TOPIC)) {
//                toDealData(new String(mqttMessage.getPayload()));
//            }
//        }
//    };
//
//    private void toDealData(String str) {
//        if (!StringUtils.hasText(str)) {
//            return;
//        }
//
//        JSONObject object = JSONObject.parseObject(str);
////        Integer typeInt = object.getInteger("type");
//        System.out.println("---接受到" + str);
//        if (object != null) {
//            SysData sysData = new SysData();
//            sysData.setDataName("设备1");
//            sysData.setDataDeviceid("A001");
//            String deviceId = object.getString("deviceId");
//            String wendu = object.getString("temp");
//            String shidu = object.getString("humi");
//            String turang_humi = object.getString("soilHumi");
//            String turang_temp = object.getString("soilTemp");
////            String pH = object.getString("PH");
//
//            String co = object.getString("co2");
//            String light = object.getString("light");
//            if (StringUtils.hasText(deviceId)) {
//                SysDictType sysDictType = dictTypeService.selectDictTypeByMqtt(deviceId);
//                if (sysDictType != null) {
//                    sysData.setDataDeviceid(sysDictType.getDictMqtt());
//                    sysData.setDataName(sysDictType.getDictName());
//                }
//            }
//            if (StringUtils.hasText(wendu)) {
//                sysData.setDataWendu(Float.parseFloat(wendu));
//            }
//            if (StringUtils.hasText(shidu)) {
//                sysData.setDataShidu(Float.parseFloat(shidu));
//            }
//            if (StringUtils.hasText(co)) {
//                sysData.setDataCo(Float.parseFloat(co));
//            }
//            if (StringUtils.hasText(light)) {
//                sysData.setDataLight(Float.parseFloat(light));
//            }
//
//            if (StringUtils.hasText(turang_humi)) {
//                sysData.setTurangShidu(Float.parseFloat(turang_humi));
//            }
//
//            if (StringUtils.hasText(turang_temp)) {
//                sysData.setTurangTemp(Float.parseFloat(turang_temp));
//            }
////            if (StringUtils.hasText(pH)) {
////                sysData.setDataPh(Float.parseFloat(pH));
////            }
//            System.out.println("---接受到*****::**");
////            sysData.setUpdateTime(new Date());
//            try {
//                int inserInt = sysDataService.insertDataType(sysData);
//                System.out.println("---接受到*****::" + inserInt);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//        }
//
//    }
//
//    public boolean pushMsg(String content) {
//        try {
//
//            // 创建消息并设置 QoS
//            MqttMessage message = new MqttMessage(content.getBytes());
//            int qos = 0;
//            message.setQos(qos);
//            // 发布消息
//            if (mqttClient != null) {
//                mqttClient.publish(Constants.SEDTOPIC, message);
//                return true;
//            }
//
//
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//
//    /**
//     * 服务端断开连接
//     */
//    public void disConnect() {
//        try {
//            mqttClient.disconnect();
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static String changeToSendSet(int actionType, SysDictType dict) {
//        DeviceDataModel deviceDataModel = new DeviceDataModel();
//        deviceDataModel.setActionType(actionType);
//        deviceDataModel.setDeviceId(dict.getDictMqtt());
//        deviceDataModel.setCarbonMin(dict.getCarbonMin());
//        deviceDataModel.setCarbonMax(dict.getCarbonMax());
//        deviceDataModel.setTempMin(dict.getTempMin());
//        deviceDataModel.setTempMax(dict.getTempMax());
//        deviceDataModel.setHumMin(dict.getHumMin());
//        deviceDataModel.setHumMax(dict.getHumMax());
//        deviceDataModel.setLightMin(dict.getLightMin());
//        deviceDataModel.setLightMax(dict.getLightMax());
////{  "actionType" : 1,  "carbonMax" : "55",  "carbonMin" : "44",  "humMax" : "33",  "humMin" : "22",  "lightMax" : "44",  "lightMin" : "33",  "tempMax" : "11",  "tempMin" : "22"}
//        return JSONObject.toJSONString(deviceDataModel);
//
//    }
//
//    public static String changeToSendData(int actionType, SysDictType dict) {
//        DeviceDataModel deviceDataModel = new DeviceDataModel();
//        deviceDataModel.setActionType(actionType);
//        deviceDataModel.setDeviceId(dict.getDictMqtt());
//        deviceDataModel.setLight(dict.getLight());
//        deviceDataModel.setCarbon(dict.getCarbon());
//        deviceDataModel.setTemp(dict.getTemp());
//        deviceDataModel.setHum(dict.getHum());
//
//        return JSONObject.toJSONString(deviceDataModel);
//
//    }
}

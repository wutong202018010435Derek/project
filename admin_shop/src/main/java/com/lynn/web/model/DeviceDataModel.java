package com.lynn.web.model;

public class DeviceDataModel {

    private int actionType;//命令类型
    private String deviceName;//

    private String deviceId;//
    private String temp;//温度

    private String tempMax;//
    private String tempMin;//


    private String hum;//湿度
    private String humMax;//
    private String humMin;//

    private String carbon;//二氧化碳
    private String carbonMax;//
    private String carbonMin;//

    private String light;//光照强度
    private String lightMax;//
    private String lightMin;//

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getHumMax() {
        return humMax;
    }

    public void setHumMax(String humMax) {
        this.humMax = humMax;
    }

    public String getHumMin() {
        return humMin;
    }

    public void setHumMin(String humMin) {
        this.humMin = humMin;
    }

    public String getCarbon() {
        return carbon;
    }

    public void setCarbon(String carbon) {
        this.carbon = carbon;
    }

    public String getCarbonMax() {
        return carbonMax;
    }

    public void setCarbonMax(String carbonMax) {
        this.carbonMax = carbonMax;
    }

    public String getCarbonMin() {
        return carbonMin;
    }

    public void setCarbonMin(String carbonMin) {
        this.carbonMin = carbonMin;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getLightMax() {
        return lightMax;
    }

    public void setLightMax(String lightMax) {
        this.lightMax = lightMax;
    }

    public String getLightMin() {
        return lightMin;
    }

    public void setLightMin(String lightMin) {
        this.lightMin = lightMin;
    }


}

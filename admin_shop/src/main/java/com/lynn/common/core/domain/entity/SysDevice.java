package com.lynn.common.core.domain.entity;

import com.lynn.common.annotation.Excel;
import com.lynn.common.core.domain.BaseEntity;

public class SysDevice extends BaseEntity {
    private static final long serialVersionUID = 1L;


    /**
     * 设备主键
     */

    private Long deviceId;
    private String deviceName;

    private String deviceHead;
    private String devicePhone;
    private String deviceMqtt;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceHead() {
        return deviceHead;
    }

    public void setDeviceHead(String deviceHead) {
        this.deviceHead = deviceHead;
    }

    public String getDevicePhone() {
        return devicePhone;
    }

    public void setDevicePhone(String devicePhone) {
        this.devicePhone = devicePhone;
    }

    public String getDeviceMqtt() {
        return deviceMqtt;
    }

    public void setDeviceMqtt(String deviceMqtt) {
        this.deviceMqtt = deviceMqtt;
    }
    private String status;

}

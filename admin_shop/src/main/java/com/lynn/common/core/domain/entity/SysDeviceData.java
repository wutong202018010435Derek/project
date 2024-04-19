package com.lynn.common.core.domain.entity;

import com.lynn.common.annotation.Excel;
import com.lynn.common.core.domain.BaseEntity;

import java.util.Date;

public class SysDeviceData extends BaseEntity {
    private static final long serialVersionUID = 1L;


    /**
     * 设备主键
     */
    @Excel(name = "设备主键", cellType = Excel.ColumnType.NUMERIC)
    private Long sysId;

    public Long getSysId() {
        return sysId;
    }

    public void setSysId(Long sysId) {
        this.sysId = sysId;
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

    public String getDeviceUser() {
        return deviceUser;
    }

    public void setDeviceUser(String deviceUser) {
        this.deviceUser = deviceUser;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 设备名称
     */
    @Excel(name = "设备名称")
    private String deviceName;

    /**
     * 负责人
     */
    @Excel(name = "设备唯一ID")
    private String deviceId;

    /**
     * 状态（0正常 1停用）
     */
    @Excel(name = "对应的用户")
    private String deviceUser;

    @Excel(name = "记录时间")
    private Date updateTime;

}

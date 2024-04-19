package com.edu.squash.bean;

/**
 * Created by ABC
 * on 2024/2/22
 * note
 */
public class ParkListBean {

    private Long pId;

    private String pName;

    private String pAddress;
    private double pLng;
    private double pLat;
    private String pRemark;
    private int pType;//1停车场 2充电桩
    private String pLocation;
    private String pDesc;

    private float pMoney;
    private int pState;//0空闲 1忙碌



    public String getpLocation() {
        return pLocation;
    }

    public void setpLocation(String pLocation) {
        this.pLocation = pLocation;
    }

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
    }

    public float getpMoney() {
        return pMoney;
    }

    public void setpMoney(float pMoney) {
        this.pMoney = pMoney;
    }

    public int getpState() {
        return pState;
    }

    public void setpState(int pState) {
        this.pState = pState;
    }







    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public double getpLng() {
        return pLng;
    }

    public void setpLng(double pLng) {
        this.pLng = pLng;
    }

    public double getpLat() {
        return pLat;
    }

    public void setpLat(double pLat) {
        this.pLat = pLat;
    }

    public String getpRemark() {
        return pRemark;
    }

    public void setpRemark(String pRemark) {
        this.pRemark = pRemark;
    }

    public int getpType() {
        return pType;
    }

    public void setpType(int pType) {
        this.pType = pType;
    }




}

package com.edu.squash.bean;

import java.util.Date;

public class SysYue {
    private Long yId;
    private String yParkid;//停车位id
    private String yParkname;
    private String yParklocation;
    private String yParkaddress;
    private Float yParkprice;
    private String yMoney;
    private String yDate;
    private String yTime;
    private int yState;

    public int getyType() {
        return yType;
    }

    public void setyType(int yType) {
        this.yType = yType;
    }

    private int yType;
    private String yUserid;


//    private Date createTime;


    public Long getyId() {
        return yId;
    }

    public void setyId(Long yId) {
        this.yId = yId;
    }





    public String getyParkid() {
        return yParkid;
    }

    public void setyParkid(String yParkid) {
        this.yParkid = yParkid;
    }

    public String getyParkname() {
        return yParkname;
    }

    public void setyParkname(String yParkname) {
        this.yParkname = yParkname;
    }

    public String getyParklocation() {
        return yParklocation;
    }

    public void setyParklocation(String yParklocation) {
        this.yParklocation = yParklocation;
    }

    public String getyParkaddress() {
        return yParkaddress;
    }

    public void setyParkaddress(String yParkaddress) {
        this.yParkaddress = yParkaddress;
    }

    public Float getyParkprice() {
        return yParkprice;
    }

    public void setyParkprice(Float yParkprice) {
        this.yParkprice = yParkprice;
    }

    public String getyMoney() {
        return yMoney;
    }

    public void setyMoney(String yMoney) {
        this.yMoney = yMoney;
    }

    public String getyDate() {
        return yDate;
    }

    public void setyDate(String yDate) {
        this.yDate = yDate;
    }

    public String getyTime() {
        return yTime;
    }

    public void setyTime(String yTime) {
        this.yTime = yTime;
    }

    public int getyState() {
        return yState;
    }

    public void setyState(int yState) {
        this.yState = yState;
    }

    public String getyUserid() {
        return yUserid;
    }

    public void setyUserid(String yUserid) {
        this.yUserid = yUserid;
    }

//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
}

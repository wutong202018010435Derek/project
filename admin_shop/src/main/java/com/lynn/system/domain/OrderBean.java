package com.lynn.system.domain;

import com.lynn.common.core.domain.BaseEntity;

public class OrderBean extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long oId;

    private String oGoodsname;

    private String oGoodsimg;
    private String oGoodsprice;
    private String oGoodsnum;
    private String oMoney;
    private String oBuyid;
    private String oSellerid;
    private String oAddress;
    private int oState;
    private String oComment;

    private String oGoodsid;

    public Long getoId() {
        return oId;
    }

    public void setoId(Long oId) {
        this.oId = oId;
    }

    public String getoGoodsname() {
        return oGoodsname;
    }

    public void setoGoodsname(String oGoodsname) {
        this.oGoodsname = oGoodsname;
    }

    public String getoGoodsimg() {
        return oGoodsimg;
    }

    public void setoGoodsimg(String oGoodsimg) {
        this.oGoodsimg = oGoodsimg;
    }

    public String getoGoodsprice() {
        return oGoodsprice;
    }

    public void setoGoodsprice(String oGoodsprice) {
        this.oGoodsprice = oGoodsprice;
    }

    public String getoGoodsnum() {
        return oGoodsnum;
    }

    public void setoGoodsnum(String oGoodsnum) {
        this.oGoodsnum = oGoodsnum;
    }

    public String getoMoney() {
        return oMoney;
    }

    public void setoMoney(String oMoney) {
        this.oMoney = oMoney;
    }

    public String getoBuyid() {
        return oBuyid;
    }

    public void setoBuyid(String oBuyid) {
        this.oBuyid = oBuyid;
    }

    public String getoSellerid() {
        return oSellerid;
    }

    public void setoSellerid(String oSellerid) {
        this.oSellerid = oSellerid;
    }

    public String getoAddress() {
        return oAddress;
    }

    public void setoAddress(String oAddress) {
        this.oAddress = oAddress;
    }

    public int getoState() {
        return oState;
    }

    public void setoState(int oState) {
        this.oState = oState;
    }

    public String getoComment() {
        return oComment;
    }

    public void setoComment(String oComment) {
        this.oComment = oComment;
    }

    public String getoGoodsid() {
        return oGoodsid;
    }

    public void setoGoodsid(String oGoodsid) {
        this.oGoodsid = oGoodsid;
    }





}

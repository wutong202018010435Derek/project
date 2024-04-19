package com.lynn.system.domain;

import com.lynn.common.core.domain.BaseEntity;

public class SysGoods extends BaseEntity {

    private static final long serialVersionUID = 1L;


    private Long goodsId;
    private String goodsName;
    private String goodsDesc;
    private String goodsTypeInt;

    private String goodsTypeCategory;
    private String goodsPrice;
    private String goodsSellerId;
    private String goodsSellerName;
    private String goodsImg;



    private int goodsState;


    public int getGoodsState() {
        return goodsState;
    }

    public void setGoodsState(int goodsState) {
        this.goodsState = goodsState;
    }


    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsTypeInt() {
        return goodsTypeInt;
    }

    public void setGoodsTypeInt(String goodsTypeInt) {
        this.goodsTypeInt = goodsTypeInt;
    }

    public String getGoodsTypeCategory() {
        return goodsTypeCategory;
    }

    public void setGoodsTypeCategory(String goodsTypeCategory) {
        this.goodsTypeCategory = goodsTypeCategory;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsSellerId() {
        return goodsSellerId;
    }

    public void setGoodsSellerId(String goodsSellerId) {
        this.goodsSellerId = goodsSellerId;
    }

    public String getGoodsSellerName() {
        return goodsSellerName;
    }

    public void setGoodsSellerName(String goodsSellerName) {
        this.goodsSellerName = goodsSellerName;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }





}

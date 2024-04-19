package com.edu.squash.bean;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.lynn.base.net.OkHttpTool;

import java.io.Serializable;

@DatabaseTable(tableName = "goodslist")
public class SysGoods  implements Serializable {



    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String goodsId;
    @DatabaseField
    private String goodsName;
    @DatabaseField
    private String goodsDesc;
    @DatabaseField
    private String goodsTypeInt;
    @DatabaseField
    private String goodsTypeCategory;
    @DatabaseField
    private String goodsPrice;
    @DatabaseField
    private String goodsSellerId;
    @DatabaseField
    private String goodsSellerName;
    @DatabaseField
    private String goodsImg;

    @DatabaseField
    private int goodsCollect; // 收藏  ==1

    @DatabaseField
    private int goodsShop; //购物车  ==1




    @DatabaseField
    private int goodsShopNum=1; //商品购物车的数量

    @DatabaseField
    private String goodsUserId;//购物车或者 收藏的人id

    public int getGoodsState() {
        return goodsState;
    }

    public void setGoodsState(int goodsState) {
        this.goodsState = goodsState;
    }

    @DatabaseField
    private int goodsState;//


    public int getGoodsShopNum() {
        return goodsShopNum;
    }

    public void setGoodsShopNum(int goodsShopNum) {
        this.goodsShopNum = goodsShopNum;
    }



    @DatabaseField
    private Long releaseTime;//加入数据库的时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsCollect() {
        return goodsCollect;
    }

    public void setGoodsCollect(int goodsCollect) {
        this.goodsCollect = goodsCollect;
    }

    public int getGoodsShop() {
        return goodsShop;
    }

    public void setGoodsShop(int goodsShop) {
        this.goodsShop = goodsShop;
    }

    public String getGoodsUserId() {
        return goodsUserId;
    }

    public void setGoodsUserId(String goodsUserId) {
        this.goodsUserId = goodsUserId;
    }





    public Long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Long releaseTime) {
        this.releaseTime = releaseTime;
    }


    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
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
//        return OkHttpTool.Base_URL + goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }





}

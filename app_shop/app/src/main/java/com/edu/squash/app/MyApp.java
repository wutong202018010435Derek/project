package com.edu.squash.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.amap.api.location.AMapLocationClient;
import com.edu.squash.ui.login.LoginAty;
import com.lynn.base.beans.UserBean;
import com.lynn.base.uitls.JsonUtil;
import com.lynn.base.uitls.SharePrefUtil;
import com.lynn.base.uitls.Utils;
import com.socks.library.KLog;


/**
 * Created by ABC

 * note
 */
public class MyApp extends Application {

    private static final String TAG = "MyApp";//


    private static MyApp mMyApp;
    private Context mContext;

/*
* 1、商品详情下的评价 1
* 2、支付类型

*
*
* 1、商品上下架  1
*2、订单管理 处理退款 1
* 3、商品图片的上传  1
*
*
* 1、banner图片上传 1
* 2、商品管理   1
* 3、订单管理 查询删除  1
*
* */



    public static MyApp getApp() {
        return mMyApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMyApp = this;
        mContext = this;
        AMapLocationClient.updatePrivacyShow(mContext,true,true);
        AMapLocationClient.updatePrivacyAgree(mContext,true);
        KLog.init(true);

    }



    UserBean mUserBean;

    public UserBean getUser() {
        if (mUserBean == null) {
            try {
                String cacheData = SharePrefUtil.getString(this, SharePrefUtil.KEY.SAVE_USER, "");
                if (!TextUtils.isEmpty(cacheData)) {
                    mUserBean = JsonUtil.parseJson(cacheData, UserBean.class);
                }
                if (mUserBean == null) {
                    mUserBean = new UserBean();
                }
            } catch (Exception ignored) {
            }

        }


        return mUserBean;
    }

    public boolean isLogin() {
        if (getUser() != null && !TextUtils.isEmpty(mUserBean.getLoginName())) {
            return true;
        }

        return false;
    }

    public void reSetUser() {
        try {
            String cacheData = SharePrefUtil.getString(this, SharePrefUtil.KEY.SAVE_USER, "");
            if (!TextUtils.isEmpty(cacheData)) {
                mUserBean = JsonUtil.parseJson(cacheData, UserBean.class);
            }
        } catch (Exception ignored) {
        }
    }

    public void exitUser(Context mContext) {
        SharePrefUtil.saveString(this, SharePrefUtil.KEY.SAVE_USER, "");
        mUserBean = null;
        mContext.startActivity(new Intent(mContext, LoginAty.class));
    }

    public void SaveUserInfo(UserBean userBean) {
        mUserBean = userBean;
        String spCacheData = JsonUtil.toJson(userBean);
        SharePrefUtil.saveString(Utils.getApp(), SharePrefUtil.KEY.SAVE_USER, spCacheData);//保存登录的信息
    }


}

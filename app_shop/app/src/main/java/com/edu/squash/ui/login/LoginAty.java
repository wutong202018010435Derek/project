package com.edu.squash.ui.login;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.edu.squash.MainActivity;

import com.edu.squash.R;
import com.edu.squash.app.MyApp;
import com.edu.squash.ui.seller.SellerAty;
import com.edu.squash.utils.MyPermissionsCallback;
import com.edu.squash.utils.RxPermissionsUtils;
import com.lynn.base.base.BaseActivity;
import com.lynn.base.beans.UserBean;
import com.lynn.base.net.HttpResponse;
import com.lynn.base.net.OkHttpTool;
import com.lynn.base.net.RuseltBean;
import com.lynn.base.uitls.JsonUtil;
import com.lynn.base.uitls.ToastUtil;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by ABC
 * <p>
 * note
 */
public class LoginAty extends BaseActivity {

    @BindView(R.id.etName)
    EditText etName;

    @BindView(R.id.etPsw)
    EditText etPsw;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    @BindView(R.id.tvRegister)
    TextView tvRegister;


    @Override
    protected int getLayoutId() {
        return R.layout.aty_login;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        initlister();

    }

    private void initlister() {
        //登录
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mName = etName.getText().toString();

                if (TextUtils.isEmpty(mName)) {
                    ToastUtil.showShortCenter(mContext, "请输入账号");
                    return;
                }
                if (TextUtils.isEmpty(etPsw.getText().toString())) {
                    ToastUtil.showShortCenter(mContext, "请输入密码");
                    return;
                }
//                if (mName.startsWith("s")) {
//                    startActivity(new Intent(mContext, SellerAty.class));
//                } else {
//                    startActivity(new Intent(mContext, MainActivity.class));
//                }

                toService(etName.getText().toString(), etPsw.getText().toString());


            }
        });
        //注册
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAty.this, RegisterUserActivity.class));
//                startActivity(new Intent(LoginAty.this, LineRoadAty.class));
            }
        });

        RxPermissionsUtils.requestPermissions(this, new MyPermissionsCallback() {
            @Override
            public void succ() {

            }
        }, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    /*
     * 获取用户录入的账号密码
     * */
    private void toService(String userName, String userPass) {
        String url = OkHttpTool.Base_URL + "app/login?my=1";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", userName);
        parameters.put("password", userPass);

        OkHttpTool.httpGet(url, parameters, new OkHttpTool.ResponseCallback() {
            @Override
            public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {

                KLog.e("测试", "----" + isSuccess + "  " + response + "    ");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (isSuccess && !TextUtils.isEmpty(response)) {
                            RuseltBean message = JsonUtil.parseJson(response, RuseltBean.class);
                            if (message != null) {
                                if (message.getCode() == 200) {

                                    HttpResponse<UserBean> rub = JsonUtil.parseJson(response, HttpResponse.class);
                                    UserBean userBean = JsonUtil.parseJson(JsonUtil.toJson(rub.getData()), UserBean.class);
                                    if (rub != null && userBean != null) {
                                        MyApp.getApp().SaveUserInfo(userBean);
                                        if (TextUtils.equals(userBean.getUserType(), "01"))
                                            startActivity(new Intent(LoginAty.this, SellerAty.class));
                                        else
                                            startActivity(new Intent(LoginAty.this, MainActivity.class));

//                                        ToastUtil.showShort(mContext, "登录成功");

                                        finish();
                                    }
                                } else if (message.getCode() == 201) {
                                    ToastUtil.showShort(mContext, "  FAIL");
                                } else {
                                    ToastUtil.showShort(mContext, "  NO REGISTER");
                                    startActivity(new Intent(mContext, RegisterUserActivity.class));
                                }

                            }

                        }
                    }
                });
            }
        });
    }


}

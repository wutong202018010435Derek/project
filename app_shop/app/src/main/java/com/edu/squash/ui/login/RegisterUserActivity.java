package com.edu.squash.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


import com.edu.squash.MainActivity;
import com.edu.squash.R;
import com.edu.squash.app.MyApp;
import com.google.gson.reflect.TypeToken;
import com.lynn.base.base.BaseActivity;
import com.lynn.base.beans.UserBean;
import com.lynn.base.net.HttpResponse;
import com.lynn.base.net.OkHttpTool;
import com.lynn.base.net.RuseltBean;
import com.lynn.base.uitls.JsonUtil;
import com.lynn.base.uitls.ToastUtil;
import com.lynn.base.view.AppTitleLayout;
import com.socks.library.KLog;


import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by ABC
 * <p>
 * note
 */
public class RegisterUserActivity extends BaseActivity {

    @BindView(R.id.backTitle)
    AppTitleLayout backTitle;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.linearLayout1)
    LinearLayout linearLayout1;
    @BindView(R.id.etCode)
    EditText etCode;

    @BindView(R.id.tvGetCode)
    TextView tvGetCode;
    @BindView(R.id.llCode)
    LinearLayout llCode;
    @BindView(R.id.etPsw)
    EditText etPsw;
    @BindView(R.id.etConfirmPsw)
    EditText etConfirmPsw;
    @BindView(R.id.btnRrgister)
    Button btnRrgister;

    @BindView(R.id.spinner)
    Spinner spinner;

    @Override
    protected int getLayoutId() {
        return R.layout.aty_register;
    }

    @Override
    protected void onInitView(Bundle bundle) {

        initListern();
    }

    private String name, code, confirmpsw;

    private void initListern() {
        backTitle.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //注册
        btnRrgister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();
                //register_phoneUser-false-start
                code = etCode.getText().toString();
                //register_phoneUser-false-end
                String psw = etPsw.getText().toString();
                confirmpsw = etConfirmPsw.getText().toString();
                String mType = spinner.getSelectedItem().toString();

                if (TextUtils.isEmpty(name)) {
                    ToastUtil.showShortCenter(mContext, "Data is null");
                    return;
                }

                if (TextUtils.isEmpty(code)) {
                    ToastUtil.showShortCenter(mContext, "Data is Null");
                    return;
                }


                if (TextUtils.isEmpty(confirmpsw)) {
                    ToastUtil.showShortCenter(mContext, "Data is null");
                    return;
                }
                if (!psw.equals(confirmpsw)) {
                    ToastUtil.showShortCenter(mContext, "Password no correct");
                    return;
                }
                if (psw.length() < 6) {
                    ToastUtil.showShortCenter(mContext, "Password legth no correct");
                    return;
                }
                toRegister(name, confirmpsw);


            }
        });
        //获取验证码
        tvGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    ToastUtil.showShortCenter(mContext, "Data no correct");
                    return;
                }
                ToastUtil.showShortCenter(mContext, "Sending");

            }
        });
    }

    private void toRegister(String name, String confirmpsw) {
        toService(name, confirmpsw);

    }

    /*
     * 获取用户录入的账号密码，然后去请求服务器接口
     * */
    private void toService(String userName, String userPass) {
        String url = OkHttpTool.Base_URL + "app/register?my=1";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", userName);
        parameters.put("password", userPass);
        parameters.put("usertype", TextUtils.equals(spinner.getSelectedItem().toString(), "Seller") ? "01" : "");


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

//                                    HttpResponse<UserBean> rub = JsonUtil.parseJson(response, HttpResponse<UserBean>.class);
                                    HttpResponse<UserBean> rub = JsonUtil.parseJson(response, new TypeToken<HttpResponse<UserBean>>() {
                                    }.getType());

                                    UserBean userBean = JsonUtil.parseJson(JsonUtil.toJson(rub.getData()), UserBean.class);
                                    if (rub != null && userBean != null) {
                                        MyApp.getApp().SaveUserInfo(userBean);
                                        startActivity(new Intent(RegisterUserActivity.this, MainActivity.class));

                                        ToastUtil.showShort(mContext, "Success");

                                        finish();
                                    }
                                } else if (message.getCode() == 201) {
                                    ToastUtil.showShort(mContext, "Fail");
                                } else {
                                    ToastUtil.showShort(mContext, "Fail No 200");

                                }

                            }

                        }
                    }
                });
            }
        });
    }

}

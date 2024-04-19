package com.edu.squash.ui.me;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.edu.squash.R;
import com.edu.squash.app.MyApp;
import com.lynn.base.base.BaseActivity;
import com.lynn.base.net.OkHttpTool;
import com.lynn.base.net.RuseltBean;
import com.lynn.base.uitls.JsonUtil;
import com.lynn.base.uitls.ToastUtil;
import com.lynn.base.view.AppTitleLayout;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class SugesstionCommitActivity extends BaseActivity {


    @BindView(R.id.titleLayout)
    AppTitleLayout titleLayout;


    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.et_email)
    EditText etEmail;

    @BindView(R.id.et_birthday)
    EditText etBirthday;

    @BindView(R.id.btn_commint)
    Button btnCommint;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sugesstion;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        titleLayout.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishOverAnim();
            }
        });
        btnCommint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String mTitle = etNoticeTitle.getText().toString().trim();
                String name = etName.getText().toString().trim();
                String birthday = etBirthday.getText().toString().trim();
                String email = etEmail.getText().toString().trim();


                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(birthday) || TextUtils.isEmpty(email)) {
                    ToastUtil.showShort(mContext, "Data is Null");
                    return;
                }
                toAdd(name, birthday, email);
            }
        });
        etBirthday.setText(TextUtils.isEmpty(MyApp.getApp().getUser().getRemark())?"":MyApp.getApp().getUser().getRemark());
        etName.setText(TextUtils.isEmpty(MyApp.getApp().getUser().getUserName())?"":MyApp.getApp().getUser().getUserName());
        etEmail.setText(TextUtils.isEmpty(MyApp.getApp().getUser().getEmail())?"":MyApp.getApp().getUser().getEmail());

    }

    private void toAdd(String name, String birthday, String email) {


        String url = OkHttpTool.Base_URL + "app/updataUser?my=1";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", MyApp.getApp().getUser().getUserId());
        parameters.put("userName", name);
        parameters.put("email", email);
        parameters.put("remark", birthday);
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
                                    MyApp.getApp().getUser().setRemark(birthday);
                                    MyApp.getApp().getUser().setEmail(email);
                                    MyApp.getApp().getUser().setUserName(name);
                                    finish();

                                }  ToastUtil.showShort(mContext, message.getMsg());

                            }
                        }

                    }
                });
            }
        });

    }
}

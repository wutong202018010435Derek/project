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

public class EvaluateCommitActivity extends BaseActivity {


    @BindView(R.id.titleLayout)
    AppTitleLayout titleLayout;


    @BindView(R.id.et_name)
    EditText etName;

//    @BindView(R.id.et_email)
//    EditText etEmail;
//
//    @BindView(R.id.et_birthday)
//    EditText etBirthday;

    @BindView(R.id.btn_commint)
    Button btnCommint;

    @Override
    protected int getLayoutId() {
        return R.layout.aty_evaluate;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        titleLayout.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishOverAnim();
            }
        });

        String strId = getIntent().getStringExtra("mData");
        btnCommint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String mTitle = etNoticeTitle.getText().toString().trim();
                String name = etName.getText().toString().trim();



                if (TextUtils.isEmpty(name) ) {
                    ToastUtil.showShort(mContext, "Data is Null");
                    return;
                }
                toAdd(strId,name);
            }
        });


    }

    private void toAdd(String oid, String name) {


        String url = OkHttpTool.Base_URL + "app/updateOrder?my=2";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("oId", oid);
        parameters.put("ocomment", name);
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

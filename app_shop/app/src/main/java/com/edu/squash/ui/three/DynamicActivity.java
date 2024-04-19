package com.edu.squash.ui.three;

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


public class DynamicActivity extends BaseActivity {
    @BindView(R.id.titleLayout)
    AppTitleLayout titleLayout;
    @BindView(R.id.et_notice_desc)
    EditText etNoticeDesc;
    @BindView(R.id.et_notice_title)
    EditText et_notice_title;
    @BindView(R.id.et_notice_type)
    EditText et_notice_type;


    @BindView(R.id.btn_commint)
    Button btnCommint;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dynamic;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        titleLayout.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnCommint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mTitle = et_notice_title.getText().toString().trim();
                String mContent = etNoticeDesc.getText().toString().trim();
                String mType = et_notice_type.getText().toString().trim();
                toAdd(mTitle, mType, mContent);

            }
        });
    }

    private void toAdd(String mTitle, String mType, String mContent) {

        if (TextUtils.isEmpty(mTitle) || TextUtils.isEmpty(mContent)) {
            ToastUtil.showShort(mContext, "内容不能为空");
            return;
        }
        toService(mTitle, mType, mContent);
//        DynamicBean sb = new DynamicBean();
//        sb.setZuozheId(MyApp.getApp().getUser().getId());
////        sb.setZuozheHead(MyApp.getApp().getUser().getUserHead());
////        sb.setZuozheNick(MyApp.getApp().getUser().getUserNick());
//        sb.setdTitle(mTitle);
//        sb.setdContent(mContent);
//        sb.setReleaseTime(System.currentTimeMillis());
//
//        if (DynamicDaoManager.getInstance().addDynamicToList(sb)) {
//            ToastUtil.showShort(mContext, "恭喜发布成功！");
//
//            finish();
//        } else {
//            ToastUtil.showShort(mContext, "提交失败！");
//        }


    }


    private void toService(String mTitle, String mType, String mContent) {

        if (TextUtils.isEmpty(mContent)) {
            ToastUtil.showShort(mContext, "内容不能为空");
            return;
        }

        String url = OkHttpTool.Base_URL + "app/addGongLv?my=1";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", MyApp.getApp().getUser().getLoginName());
        parameters.put("userid", MyApp.getApp().getUser().getUserId());
        parameters.put("content", mContent);
        parameters.put("title", mTitle);
        parameters.put("mTpye", mType);

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
                                    ToastUtil.showShort(mContext, "提交成功");
                                    finish();

                                } else {
                                    ToastUtil.showShort(mContext, "提交失败");
                                }

                            } else {
                                ToastUtil.showShort(mContext, "提交失败2");
                            }
                        }

                    }
                });
            }
        });

    }

}

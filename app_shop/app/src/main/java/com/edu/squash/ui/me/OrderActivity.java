package com.edu.squash.ui.me;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.squash.R;
import com.edu.squash.app.MyApp;
import com.edu.squash.bean.OrderBean;
import com.edu.squash.bean.SysGoods;
import com.edu.squash.bean.SysYue;
import com.google.gson.reflect.TypeToken;
import com.lynn.base.base.BaseActivity;
import com.lynn.base.net.HttpResponse;
import com.lynn.base.net.OkHttpTool;
import com.lynn.base.uitls.JsonUtil;
import com.lynn.base.uitls.ToastUtil;
import com.lynn.base.view.AppTitleLayout;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class OrderActivity extends BaseActivity {


    @BindView(R.id.titleLayout)
    AppTitleLayout titleLayout;


    @BindView(R.id.rv_device)
    RecyclerView rv_list;


    @Override
    protected int getLayoutId() {
        return R.layout.aty_chart;
    }


    private MyOrderAdapter mEvnetMyAdapter;
    private List<OrderBean> mEventList = new ArrayList<>();

    //    private SysYue parkListBean;
    @Override
    protected void onInitView(Bundle bundle) {

//        String  mClassType = getIntent().getStringExtra("mData");
//
//        parkListBean = JsonUtil.parseJson(mClassType,ParkListBean.class);

        titleLayout.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishOverAnim();
            }
        });

//        for (int i = 0; i < 10; i++) {
//            mEventList.add(new SysYue());
//        }


        mEvnetMyAdapter = new MyOrderAdapter(R.layout.item_my_order, mEventList, new MyOrderAdapter.OnOverClickLister() {
            @Override
            public void OnOver(OrderBean sysYue) {

                toOver(sysYue);
            }

            @Override
            public void OnCancle(OrderBean sysYue) {
//                toOver(sysYue);
            }
        });

        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_list.setAdapter(mEvnetMyAdapter);


    }

    private void toOver(OrderBean sysYue) {
        String url = OkHttpTool.Base_URL + "app/updateOrder?my=2";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("oId", sysYue.getoId());
        parameters.put("ostate", 3);


        OkHttpTool.httpGet(url, parameters, new OkHttpTool.ResponseCallback() {
            @Override
            public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {

                KLog.e("测试", "----" + isSuccess + "  " + response + "    ");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (isSuccess && !TextUtils.isEmpty(response)) {

                            HttpResponse<List<SysYue>> message = JsonUtil.parseJson(response, new TypeToken<HttpResponse<List<SysYue>>>() {
                            }.getType());
                            if (message != null) {
                                if (message.getCode() == 200) {
                                    ToastUtil.showShort(mContext, "success");
                                    onResume();

                                } else {
                                    ToastUtil.showShort(mContext, "fail");
                                }

                            }

                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        toService();
    }

    public void toService() {
        String url = OkHttpTool.Base_URL + "app/getMyOrder?my=2";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", MyApp.getApp().getUser().getUserId() + "");


        OkHttpTool.httpGet(url, parameters, new OkHttpTool.ResponseCallback() {
            @Override
            public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {

                KLog.e("测试", "----" + isSuccess + "  " + response + "    ");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (isSuccess && !TextUtils.isEmpty(response)) {

                            HttpResponse<List<OrderBean>> message = JsonUtil.parseJson(response, new TypeToken<HttpResponse<List<OrderBean>>>() {
                            }.getType());
                            if (message != null) {
                                if (message.getCode() == 200) {
                                    mEventList.clear();
                                    if (message.getData() != null && message.getData().size() > 0) {
                                        mEventList.addAll(message.getData());
                                    }
                                    mEvnetMyAdapter.notifyDataSetChanged();

                                } else {
                                    ToastUtil.showShort(mContext, "no data");
                                }

                            }

                        }
                    }
                });
            }
        });
    }


}

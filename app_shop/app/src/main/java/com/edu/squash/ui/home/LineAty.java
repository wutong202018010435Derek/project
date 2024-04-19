package com.edu.squash.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.edu.squash.R;
import com.edu.squash.bean.LineBean;
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

public class LineAty extends BaseActivity {


    @BindView(R.id.titleLayout)
    AppTitleLayout titleLayout;


    @BindView(R.id.rv_device)
    RecyclerView rv_list;


    @Override
    protected int getLayoutId() {
        return R.layout.aty_my_device;
    }


    private LineAdapter mEvnetMyAdapter;
    private List<LineBean> mEventList = new ArrayList<>();

    @Override
    protected void onInitView(Bundle bundle) {
        titleLayout.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishOverAnim();
            }
        });

        titleLayout.setTitleText("推荐线路");

//        titleLayout.setRightTitleText("添加");
//        titleLayout.setOnRightTvClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(mContext, AddDeviceAty.class));
//            }
//        });
//        for (int i = 0; i < 10; i++) {
//            mEventList.add(new LineBean());
//        }

        mEvnetMyAdapter = new LineAdapter(R.layout.item_line, mEventList);

        mEvnetMyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


            }
        });

        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_list.setAdapter(mEvnetMyAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        toService();
    }

    public void toService() {
        String url = OkHttpTool.Base_URL + "app/getAllLine?my=1";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("userid", MyApp.getApp().getUser().getUserId() + "");


        OkHttpTool.httpGet(url, parameters, new OkHttpTool.ResponseCallback() {
            @Override
            public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {

                KLog.e("测试", "----" + isSuccess + "  " + response + "    ");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (isSuccess && !TextUtils.isEmpty(response)) {

                            HttpResponse<List<LineBean>> message = JsonUtil.parseJson(response, new TypeToken<HttpResponse<List<LineBean>>>() {
                            }.getType());
                            if (message != null) {
                                if (message.getCode() == 200) {
                                    mEventList.clear();
                                    if (message.getData() != null && message.getData().size() > 0) {
                                        mEventList.addAll(message.getData());
                                    }
                                    mEvnetMyAdapter.notifyDataSetChanged();

                                } else {
                                    ToastUtil.showShort(mContext, "还没数据");
                                }

                            }

                        }
                    }
                });
            }
        });
    }
}

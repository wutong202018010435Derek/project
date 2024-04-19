package com.edu.squash.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.edu.squash.R;
import com.edu.squash.app.MyApp;
import com.edu.squash.bean.OrderBean;
import com.edu.squash.bean.ParkListBean;
import com.edu.squash.bean.SysAddress;
import com.edu.squash.bean.SysYue;
import com.edu.squash.ui.three.DetailActivity;
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

public class MyAdressAty extends BaseActivity {


    @BindView(R.id.titleLayout)
    AppTitleLayout titleLayout;


    @BindView(R.id.rv_device)
    RecyclerView rv_list;


    @Override
    protected int getLayoutId() {
        return R.layout.aty_my_device;
    }


    private MyDeviceAdapter mEvnetMyAdapter;
    private List<SysAddress> mEventList = new ArrayList<>();
//    private ParkListBean parkListBean;
    @Override
    protected void onInitView(Bundle bundle) {

      String  mClassType = getIntent().getStringExtra("mData");

//        parkListBean = JsonUtil.parseJson(mClassType,ParkListBean.class);

        titleLayout.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishOverAnim();
            }
        });

        titleLayout.setRightTitleText("Add");
        titleLayout.setOnRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext,AddAdressAty.class));
            }
        });


        mEvnetMyAdapter = new MyDeviceAdapter(R.layout.item_device, mEventList, new MyDeviceAdapter.OnOverClickLister() {
            @Override
            public void OnCancle(SysAddress sysYue) {
                toOver(sysYue);
            }
        });
//        mEvnetMyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent mIntent = new Intent(mContext, DetailActivity.class);
//                mIntent.putExtra("mData", JsonUtil.toJson(mEventList.get(position)));
//                startActivity(mIntent);
//            }
//        });
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_list.setAdapter(mEvnetMyAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        toService();

    }

    public void toService() {
        String url = OkHttpTool.Base_URL + "app/getaddress?parkGroud=2";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", MyApp.getApp().getUser().getUserId());


        OkHttpTool.httpGet(url, parameters, new OkHttpTool.ResponseCallback() {
            @Override
            public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {

                KLog.e("测试", "----" + isSuccess + "  " + response + "    ");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (isSuccess && !TextUtils.isEmpty(response)) {

                            HttpResponse<List<SysAddress>> message = JsonUtil.parseJson(response, new TypeToken<HttpResponse<List<SysAddress>>>() {
                            }.getType());
                            if (message != null) {
                                if (message.getCode() == 200) {
                                    mEventList.clear();
                                    if (message.getData() != null && message.getData().size() > 0) {
                                        mEventList.addAll(message.getData());
                                    }
                                    mEvnetMyAdapter.notifyDataSetChanged();

                                } else {
                                    ToastUtil.showShort(mContext, "NO DATA");
                                }

                            }

                        }
                    }
                });
            }
        });
    }

    private void toOver(SysAddress sysYue) {
        String url = OkHttpTool.Base_URL + "app/deleaddress?my=2";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("aId", sysYue.getaId());



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

}

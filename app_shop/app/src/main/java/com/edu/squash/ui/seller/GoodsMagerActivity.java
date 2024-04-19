package com.edu.squash.ui.seller;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.edu.squash.R;
import com.edu.squash.app.MyApp;
import com.edu.squash.bean.OrderBean;
import com.edu.squash.bean.SysGoods;
import com.edu.squash.bean.SysYue;
import com.edu.squash.ui.me.AddAdressAty;
import com.edu.squash.ui.me.MyOrderAdapter;
import com.google.gson.reflect.TypeToken;
import com.lynn.base.base.BaseActivity;
import com.lynn.base.net.HttpResponse;
import com.lynn.base.net.OkHttpTool;
import com.lynn.base.net.RuseltBean;
import com.lynn.base.uitls.JsonUtil;
import com.lynn.base.uitls.ToastUtil;
import com.lynn.base.view.AppTitleLayout;
import com.socks.library.KLog;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by ABC
 * <p>
 * note
 */
public class GoodsMagerActivity extends BaseActivity {

    @BindView(R.id.titleLayout)
    AppTitleLayout titleLayout;


    @BindView(R.id.rv_rv)
    RecyclerView rv_list;

    //
    private List<SysGoods> mList = new ArrayList<>();
    AddGoodsAdapter mShopAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.aty_goods_mager;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        titleLayout.setRightTitleText("ADD");
        titleLayout.setOnRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, AddGoodsAty.class));
            }
        });
        titleLayout.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mShopAdapter = new AddGoodsAdapter(R.layout.item_goods_add, mList, new AddGoodsAdapter.OnOverClickLister() {
            @Override
            public void OnOver(SysGoods sysGoods) {
                toOver(sysGoods);
            }

            @Override
            public void OnDel(SysGoods sysGoods) {
                toDel(sysGoods);
            }
        });
//        mEvnetMyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent mIntent = new Intent(mContext, LookDynamicActivity.class);
//                mIntent.putExtra("mData", JsonUtil.toJson(mEventList.get(position)));
//                startActivity(mIntent);
//            }
//        });
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_list.setAdapter(mShopAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        toGetData();
    }

    private void toGetData() {
        String url = OkHttpTool.Base_URL + "app/getMyGoods?my=2";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("goodsSellerId", MyApp.getApp().getUser().getUserId());


        OkHttpTool.httpGet(url, parameters, new OkHttpTool.ResponseCallback() {
            @Override
            public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {

                KLog.e("测试", "----" + isSuccess + "  " + response + "    ");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isSuccess && !TextUtils.isEmpty(response)) {
                            HttpResponse<List<SysGoods>> message = JsonUtil.parseJson(response, new TypeToken<HttpResponse<List<SysGoods>>>() {
                            }.getType());
                            if (message != null) {
                                if (message.getCode() == 200) {
                                    mList.clear();
                                    if (message.getData() != null && message.getData().size() > 0) {
                                        mList.addAll(message.getData());
                                    }
                                    mShopAdapter.notifyDataSetChanged();
                                }
                            }

                        }
                    }
                });
            }
        });

    }


    private void toOver(SysGoods sysYue) {
        String url = OkHttpTool.Base_URL + "app/updateGoods?my=2";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("goodsId", sysYue.getGoodsId());
        parameters.put("goodsState", sysYue.getGoodsState());


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


    private void toDel(SysGoods sysYue) {
        String url = OkHttpTool.Base_URL + "app/delGoods?my=2";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("goodsId", sysYue.getGoodsId());


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

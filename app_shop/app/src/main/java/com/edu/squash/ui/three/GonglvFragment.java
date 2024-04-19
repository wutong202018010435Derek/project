package com.edu.squash.ui.three;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.edu.squash.R;
import com.edu.squash.bean.SysGoods;
import com.google.gson.reflect.TypeToken;
import com.lynn.base.base.BaseFragment;
import com.lynn.base.net.HttpResponse;
import com.lynn.base.net.OkHttpTool;
import com.lynn.base.uitls.JsonUtil;
import com.scwang.smart.refresh.header.BezierRadarHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by ABC
 * on 2024/1/20
 * note
 */
public class GonglvFragment extends BaseFragment {

    @BindView(R.id.rv_rv)
    RecyclerView rvSugess;

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private String className;

    public static GonglvFragment getInstance(int classType, String className) {
        GonglvFragment fragment = new GonglvFragment();
        Bundle bundle = new Bundle();

        bundle.putInt("classType", classType);
        bundle.putString("className", className);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chart;
    }

    private int mClassType;

    private List<SysGoods> mList = new ArrayList<>();
    DynamicAdapter mNoticeAdapter;

    @Override
    protected void onInitView(Bundle bundle) {

        if (getArguments() != null)
            mClassType = getArguments().getInt("classType");
        className = getArguments().getString("className");
//
//        for (int i = 0; i < 20; i++) {
//            ParkListBean parkListBean = new ParkListBean();
//            parkListBean.setpName("停车场" + i);
//            parkListBean.setpAddress("广东深圳");
//            mList.add(parkListBean);
//        }

        mNoticeAdapter = new DynamicAdapter(R.layout.item_dynamic, mList);
        rvSugess.setLayoutManager(new GridLayoutManager(mContext,2));
//        rvSugess.setLayoutManager(new LinearLayoutManager(mContext));
        rvSugess.setAdapter(mNoticeAdapter);
        mNoticeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent mIntent = new Intent(mContext, DetailActivity.class);
                mIntent.putExtra("mData", JsonUtil.toJson(mList.get(position)));
                startActivity(mIntent);
            }
        });

        setRefresh();

    }

    @Override
    public void onResume() {
        super.onResume();
        toService();
    }

    /*
     * 设置刷新的方法
     * */
    private void setRefresh() {

        //设置头部刷新的样式
        smartRefreshLayout.setRefreshHeader(new BezierRadarHeader(getActivity()));
        //设置页脚刷新的样式
//        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(this));
        //设置头部刷新时间监听
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {


                //添加一条新数据，再最开头的位置
//                addData();
//                Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
                toService();
            }
        });
//        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//
//                smartRefreshLayout.finishLoadMore(2000);
//                //添加一条新数据，再最后的位置
//                addDatas();
//                Toast.makeText(MainActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
//            }
//        });

    }



    public void toService() {
        String url = OkHttpTool.Base_URL + "app/getMyGoods?my=1";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("goodsTypeCategory", className);

        OkHttpTool.httpGet(url, parameters, new OkHttpTool.ResponseCallback() {
            @Override
            public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {

                KLog.e("测试", "----" + isSuccess + "  " + response + "    ");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        smartRefreshLayout.finishRefresh(isSuccess);//传入false表示刷新失败
                        if (isSuccess && !TextUtils.isEmpty(response)) {

                            HttpResponse<List<SysGoods>> message = JsonUtil.parseJson(response, new TypeToken<HttpResponse<List<SysGoods>>>() {
                            }.getType());
                            if (message != null) {
                                if (message.getCode() == 200) {
                                    mList.clear();
                                    if (message.getData() != null && message.getData().size() > 0) {
                                        mList.addAll(message.getData());
                                    }
                                    mNoticeAdapter.notifyDataSetChanged();

                                }

                            }

                        }
                    }
                });
            }
        });
    }


}

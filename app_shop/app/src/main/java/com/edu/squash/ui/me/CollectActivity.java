package com.edu.squash.ui.me;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.edu.squash.R;
import com.edu.squash.app.MyApp;
import com.edu.squash.bean.SysGoods;
import com.edu.squash.bean.SysYue;
import com.edu.squash.sql.GoodsDaoManager;
import com.edu.squash.ui.three.DetailActivity;
import com.edu.squash.ui.three.DynamicAdapter;
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

public class CollectActivity extends BaseActivity {


    @BindView(R.id.titleLayout)
    AppTitleLayout titleLayout;


    @BindView(R.id.rv_device)
    RecyclerView rvSugess;


    @Override
    protected int getLayoutId() {
        return R.layout.aty_chart;
    }


    private List<SysGoods> mList = new ArrayList<>();
    DynamicAdapter mNoticeAdapter;

    //    private SysYue parkListBean;
    @Override
    protected void onInitView(Bundle bundle) {

//        String  mClassType = getIntent().getStringExtra("mData");
//
//        parkListBean = JsonUtil.parseJson(mClassType,ParkListBean.class);
        titleLayout.setTitleText("MyCollect");
        titleLayout.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishOverAnim();
            }
        });


        mNoticeAdapter = new DynamicAdapter(R.layout.item_dynamic, mList);
        rvSugess.setLayoutManager(new GridLayoutManager(mContext, 2));
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


    }


    @Override
    protected void onResume() {
        super.onResume();
        toService();
    }

    public void toService() {
        List<SysGoods> messageList = GoodsDaoManager.getInstance().getMyCollect(MyApp.getApp().getUser().getUserId());

        if (messageList != null && messageList.size() > 0) {
            mList.clear();

            mList.addAll(messageList);

            mNoticeAdapter.notifyDataSetChanged();
        }
    }


}

package com.edu.squash.ui.two;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.squash.R;
import com.edu.squash.app.MyApp;
import com.edu.squash.bean.EventMessage;
import com.edu.squash.bean.SysGoods;
import com.edu.squash.bean.SysYue;
import com.edu.squash.sql.GoodsDaoManager;
import com.edu.squash.ui.seller.AddGoodsAdapter;
import com.edu.squash.ui.seller.GoodsMagerActivity;
import com.edu.squash.ui.three.CommitOrderActivity;
import com.google.gson.reflect.TypeToken;
import com.lynn.base.base.BaseFragment;
import com.lynn.base.net.HttpResponse;
import com.lynn.base.net.OkHttpTool;
import com.lynn.base.uitls.JsonUtil;
import com.lynn.base.uitls.ToastUtil;
import com.lynn.base.uitls.ZeroUtil;
import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ABC
 * on 2022/3/4
 * note
 */
public class TwoFragment extends BaseFragment {


    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;
    @BindView(R.id.tv_total)
    TextView tvTotal;

    private List<SysGoods> mList = new ArrayList<>();
    ShopGoodsAdapter mShopGoodsAdapter;

    public static TwoFragment getInstance() {
        TwoFragment fragment = new TwoFragment();
//        Bundle bundle = new Bundle();
//        if (!TextUtils.isEmpty(classType))
//            bundle.putString(Contact.KEY_TYPE, classType);
//        bundle.putBoolean("isLocalData", isLocalData);
//        bundle.putBoolean("isLoginInit", isLoginInit);
//        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_two;
    }

    @Override
    protected void onInitView(Bundle bundle) {

        EventBus.getDefault().register(this);
        mShopGoodsAdapter = new ShopGoodsAdapter(R.layout.item_device_add, mList, new ShopGoodsAdapter.OnOverClickLister() {
            @Override
            public void OnNumdel(SysGoods sysGoods, int postion) {
                if (sysGoods.getGoodsShopNum() > 0) {
                    mList.get(postion).setGoodsShopNum(sysGoods.getGoodsShopNum() - 1);
                    mShopGoodsAdapter.notifyDataSetChanged();
                }
                toCalc();
            }

            @Override
            public void OnNumAdd(SysGoods sysGoods, int postion) {
                mList.get(postion).setGoodsShopNum(sysGoods.getGoodsShopNum() + 1);
                mShopGoodsAdapter.notifyDataSetChanged();
                toCalc();
            }

            @Override
            public void OnDel(SysGoods sysGoods, int postion) {
                if (GoodsDaoManager.getInstance().dellData(sysGoods)) {
                    ToastUtil.showShortCenter(mContext, "success");
                }
                toGetData();

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
        rvGoods.setLayoutManager(new LinearLayoutManager(mContext));
        rvGoods.setAdapter(mShopGoodsAdapter);

        toGetData();

    }

    @OnClick({R.id.tv_to_settlement})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_to_settlement:
                startActivityForResult(new Intent(mContext, CommitOrderActivity.class).putExtra("GoodsList", JsonUtil.toJson(mList)), 0);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {

        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    //接收事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEventMsg(EventMessage message) {
        KLog.e("EventBus_Subscriber", "onReceiveMsg_MAIN: " + message.toString());
        toGetData();
    }

    float totalMoney = 0;

    private void toGetData() {

        List<SysGoods> messageList = GoodsDaoManager.getInstance().getMyShop(MyApp.getApp().getUser().getUserId());

//        if (messageList != null && messageList.size() > 0) {
            mList.clear();

            mList.addAll(messageList);

            mShopGoodsAdapter.notifyDataSetChanged();

        toCalc();

    }

    private void toCalc(){
        totalMoney = 0;
        for (int i = 0; i < mList.size(); i++) {
            totalMoney = totalMoney + (Float.parseFloat(mList.get(i).getGoodsPrice()) * mList.get(i).getGoodsShopNum());
        }

//        }

        tvTotal.setText("Total:" + ZeroUtil.subZeroAndDotTwo(totalMoney));
    }


}

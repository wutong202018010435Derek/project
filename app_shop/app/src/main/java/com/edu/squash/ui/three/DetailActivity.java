package com.edu.squash.ui.three;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.squash.R;
import com.edu.squash.app.MyApp;
import com.edu.squash.bean.EventMessage;
import com.edu.squash.bean.OrderBean;
import com.edu.squash.bean.ParkListBean;
import com.edu.squash.bean.SysGoods;
import com.edu.squash.bean.SysYue;
import com.edu.squash.sql.GoodsDaoManager;
import com.edu.squash.utils.ImageLoadUtils;
import com.google.gson.reflect.TypeToken;
import com.lynn.base.base.BaseActivity;
import com.lynn.base.net.HttpResponse;
import com.lynn.base.net.OkHttpTool;
import com.lynn.base.uitls.JsonUtil;
import com.lynn.base.uitls.ToastUtil;
import com.lynn.base.uitls.ZeroUtil;
import com.lynn.base.view.AppTitleLayout;
import com.lynn.base.view.dialog.RechargeDialog;
import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


public class DetailActivity extends BaseActivity {
    @BindView(R.id.titleLayout)
    AppTitleLayout titleLayout;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.btn_add)
    TextView btnAdd;


    @BindView(R.id.btn_yue)
    TextView btnYue;
    @BindView(R.id.rv_reply_list)
    RecyclerView rvReplyList;

    @BindView(R.id.iv_collect)
    ImageView ivCollect;


    List<OrderBean> mList = new ArrayList<>();
    ReplayAdapter mReplyAdapter;


    SysGoods dynamicBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dynamic_look;
    }

    @Override
    protected void onInitView(Bundle bundle) {

        titleLayout.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishOverAnim();
            }
        });

//        mId = getIntent().getIntExtra("dynamicId", -1);
//        dynamicBean = DynamicDaoManager.getInstance().getPersonDb(mId);
        dynamicBean = JsonUtil.parseJson(getIntent().getStringExtra("mData"), SysGoods.class);


        ImageLoadUtils.loadImage(mContext, dynamicBean.getGoodsImg(), ivHead);


        tvGoodsName.setText(dynamicBean.getGoodsName());
        tvDesc.setText(dynamicBean.getGoodsDesc() + "");
        tvPrice.setText("$" + dynamicBean.getGoodsPrice() + "");
//        tvAddress.setText("地址：" + dynamicBean.getpAddress() + "");
//        tv_price.setText("价格：" + dynamicBean.getpMoney() + "");
        ivCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ivCollect.isSelected()) {

                    SysGoods sysGoods = GoodsDaoManager.getInstance().getMyCollectGoods(MyApp.getApp().getUser().getUserId(), dynamicBean.getGoodsId());
                    if (sysGoods != null || sysGoods.getGoodsCollect() == 0) {
                        if (GoodsDaoManager.getInstance().dellData(sysGoods)) {
                            ivCollect.setSelected(false);
                        }
                    }

                } else {
                    dynamicBean.setGoodsShop(0);
                    dynamicBean.setGoodsCollect(1);
                    dynamicBean.setReleaseTime(System.currentTimeMillis());
                    dynamicBean.setGoodsUserId(MyApp.getApp().getUser().getUserId());
                    if (GoodsDaoManager.getInstance().addDataToList(dynamicBean))
                        ivCollect.setSelected(true);
                }

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SysGoods sysGoods = GoodsDaoManager.getInstance().getMyShopGoods(MyApp.getApp().getUser().getUserId(), dynamicBean.getGoodsId());
                if (sysGoods == null || sysGoods.getGoodsShop() == 0) {
                    dynamicBean.setGoodsShop(1);
                    dynamicBean.setGoodsCollect(0);
                    dynamicBean.setReleaseTime(System.currentTimeMillis());
                    dynamicBean.setGoodsUserId(MyApp.getApp().getUser().getUserId());
                    if (GoodsDaoManager.getInstance().addDataToList(dynamicBean)) {
                        EventBus.getDefault().post(new EventMessage(1, "refresh shop"));
                        ToastUtil.showShortCenter(mContext, "success");
                    }
                } else {
                    ToastUtil.showShortCenter(mContext, "success");
                }


            }
        });

        List<SysGoods> goodsList = new ArrayList<>();
        btnYue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (goodsList.size() > 0)
                    goodsList.clear();
                goodsList.add(dynamicBean);

                startActivityForResult(new Intent(mContext, CommitOrderActivity.class).putExtra("GoodsList", JsonUtil.toJson(goodsList)), 0);
            }

        });


//        mList = MessageDaoManager.getInstance().getforreplyId(mId);
        mReplyAdapter = new ReplayAdapter(R.layout.item_reply, mList);
        rvReplyList.setLayoutManager(new LinearLayoutManager(mContext));
        rvReplyList.setAdapter(mReplyAdapter);
        SysGoods sysGoods = GoodsDaoManager.getInstance().getMyCollectGoods(MyApp.getApp().getUser().getUserId(), dynamicBean.getGoodsId());
        if (sysGoods != null && sysGoods.getGoodsCollect() == 1) {

            ivCollect.setSelected(true);

        }

        toComment();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void toComment() {
        String url = OkHttpTool.Base_URL + "app/getMyOrder?my=2";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("parkId", dynamicBean.getpId());
        parameters.put("oGoodsid", dynamicBean.getGoodsId());
//        parameters.put("yueDate", tvDate.getText().toString());
//        parameters.put("yueTime", et_time.getText().toString());
//        parameters.put("yueMoney", payMoney);

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
                                    mList.addAll(message.getData());
                                    mReplyAdapter.notifyDataSetChanged();

                                } else {
                                }

                            }

                        }
                    }
                });
            }
        });
    }


}

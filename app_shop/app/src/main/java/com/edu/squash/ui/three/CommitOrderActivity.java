package com.edu.squash.ui.three;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.edu.squash.R;
import com.edu.squash.app.MyApp;
import com.edu.squash.bean.SysGoods;
import com.google.gson.reflect.TypeToken;
import com.lynn.base.base.BaseActivity;
import com.lynn.base.net.OkHttpTool;
import com.lynn.base.net.RuseltBean;
import com.lynn.base.uitls.JsonUtil;
import com.lynn.base.uitls.ToastUtil;
import com.lynn.base.uitls.ZeroUtil;
import com.lynn.base.view.AppTitleLayout;
import com.lynn.base.view.dialog.RechargeDialog;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by ABC
 */
public class CommitOrderActivity extends BaseActivity {


    @BindView(R.id.titleLayout)
    AppTitleLayout titleLayout;
    @BindView(R.id.rv_order_goods)
    RecyclerView rvOrderGoods;
    @BindView(R.id.tv_money)
    TextView tvMoney;

    @BindView(R.id.tv_to_pay)
    TextView tvToPay;

    //    SysGoods mOrderBean;
    private List<SysGoods> orderList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_commit;
    }

    @Override
    protected void onInitView(Bundle bundle) {

//        mOrderBean = (SysGoods) getIntent().getSerializableExtra("SysGoods");

        titleLayout.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishOverAnim();
            }
        });


//        orderList = (List<SysGoods>) getIntent().getSerializableExtra("GoodsList");
        String str = getIntent().getStringExtra("GoodsList");

        orderList = JsonUtil.parseJson(str, new TypeToken<List<SysGoods>>() {
        }.getType());

        if (orderList == null) {
            orderList = new ArrayList<>();
        }


        OrderAdapter mOrderAdapter = new OrderAdapter(R.layout.item_food_order, orderList);
        rvOrderGoods.setLayoutManager(new LinearLayoutManager(mContext));
        rvOrderGoods.setAdapter(mOrderAdapter);

        toCountMoney();

        tvToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RechargeDialog rechargeDialog = new RechargeDialog(mContext, R.style.NoticeDialog, "pay order info", String.valueOf(ft), new RechargeDialog.IClickPayCallBack() {
                    @Override
                    public void toPayMoney(String mMoney) {

                        toSave();
                    }

                    @Override
                    public void toZhiFuBao() {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("alipayqr://platformapi/startapp"));  // 打开支付宝扫码界面
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });

                rechargeDialog.OnShowDialog();
            }
        });

    }

    private void toSave() {
        for (int i = 0; i < orderList.size(); i++) {
//            KLog.e(JsonUtil.toJson(orderList.get(i)));
            toCommitData(orderList.get(i), i, orderList.size());
        }
    }

    float ft = 0;


    private void toCountMoney() {
        for (int i = 0; i < orderList.size(); i++) {
            ft = ft + (orderList.get(i).getGoodsShopNum() * Float.parseFloat(orderList.get(i).getGoodsPrice()));

        }
        tvMoney.setText("Total:" + ZeroUtil.subZeroAndDotTwo(ft));

    }


    private void toCommitData(SysGoods sysAddress, int i, int size) {


//        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(price) || TextUtils.isEmpty(desc)) {
//            ToastUtil.showShortCenter(mContext, "no data");
//            return;
//        }


        String url = OkHttpTool.Base_URL + "app/addOrder?my=1";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("oGoodsname", sysAddress.getGoodsName());
        parameters.put("oGoodsimg", sysAddress.getGoodsImg());
        parameters.put("oGoodsprice", sysAddress.getGoodsPrice());
        parameters.put("oGoodsnum", sysAddress.getGoodsShopNum());
        parameters.put("oMoney", ZeroUtil.subZeroAndDotTwo(sysAddress.getGoodsShopNum() * Float.parseFloat(sysAddress.getGoodsPrice())));
        parameters.put("oSellerid", sysAddress.getGoodsSellerId());
//        parameters.put("oAddress", sysAddress.getaPhone());
        parameters.put("oBuyid", MyApp.getApp().getUser().getUserId());
        parameters.put("oGoodsid", sysAddress.getGoodsId());
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
                                ToastUtil.showShortCenter(mContext, message.getMsg());
                                if (message.getCode() == 200) {

                                    if ((i + 1) == size) {
                                        ToastUtil.showShort(mContext, "Success");
                                        finish();

                                    }

                                }
                            }

                        }
                    }
                });
            }
        });

    }


}

package com.edu.squash.ui.seller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.edu.squash.R;
import com.edu.squash.app.MyApp;
import com.lynn.base.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by ABC
 * on 2024/3/3
 * note
 */
public class SellerAty extends BaseActivity {




    @Override
    protected int getLayoutId() {
        return R.layout.aty_seller_home;
    }

    @Override
    protected void onInitView(Bundle bundle) {

    }

    @OnClick({R.id.btn_exit, R.id.ll_goods
            , R.id.ll_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.ll_goods://
                startActivity(new Intent(mContext, GoodsMagerActivity.class));
                break;
            case R.id.ll_order://
                startActivity(new Intent(mContext, OrderMagerActivity.class));
                break;

            case R.id.btn_exit://
                MyApp.getApp().exitUser(mContext);
                break;

        }
    }


}

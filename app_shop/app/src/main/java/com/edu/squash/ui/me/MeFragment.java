package com.edu.squash.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;


import com.edu.squash.R;
import com.edu.squash.app.MyApp;
import com.lynn.base.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;


public class MeFragment extends BaseFragment {

    @BindView(R.id.top_layout)
    Toolbar topLayout;


    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_name)
    TextView tvName;

    public static MeFragment getInstance() {
        MeFragment fragment = new MeFragment();

        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void onInitView(Bundle bundle) {


    }

    @OnClick({R.id.ll_app_set, R.id.ll_result
            , R.id.ll_contact,R.id.btn_exit,R.id.ll_app_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.ll_app_set://
                startActivity(new Intent(mContext, SugesstionCommitActivity.class));
                break;
            case R.id.ll_app_data://
                startActivity(new Intent(mContext, MyAdressAty.class));
                break;
            case R.id.ll_result://
                startActivity(new Intent(mContext, OrderActivity.class));

                break;



            case R.id.ll_contact://
                startActivity(new Intent(mContext, CollectActivity.class));

                break;
            case R.id.btn_exit://
                MyApp.getApp().exitUser(mContext);
                break;

        }
    }


}

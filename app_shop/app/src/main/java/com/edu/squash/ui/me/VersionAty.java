package com.edu.squash.ui.me;

import android.os.Bundle;
import android.view.View;

import com.edu.squash.R;
import com.lynn.base.base.BaseActivity;
import com.lynn.base.view.AppTitleLayout;

import butterknife.BindView;


public class VersionAty extends BaseActivity {
    @BindView(R.id.app_back)
    AppTitleLayout appBack;

    @Override
    protected int getLayoutId() {
        return R.layout.aty_version;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        appBack.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}

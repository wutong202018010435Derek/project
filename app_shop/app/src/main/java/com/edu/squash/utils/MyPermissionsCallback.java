package com.edu.squash.utils;


import androidx.fragment.app.FragmentActivity;

import com.lynn.base.uitls.ToastUtil;


public abstract class MyPermissionsCallback {


    public abstract void succ();

    public void fail(FragmentActivity activity, Throwable throwable) {
        if (activity == null || activity.isFinishing())
            return;
            ToastUtil.showShortCenter(activity, throwable.toString());

    }
}

package com.edu.squash.utils;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.fragment.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by ABC
 * on 2020/12/1
 * note
 */
public class RxPermissionsUtils {

    public static void requestPermissions(FragmentActivity activity, MyPermissionsCallback callback, String... permissions) {
        myRequestPermissions(activity, true, callback, permissions);
    }

    public static void myRequestPermissions(FragmentActivity activity, boolean isCancelable, MyPermissionsCallback callback, String... permissions) {
        if (activity == null)
            return;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            if (callback != null)
                callback.succ();
        } else {
            if (permissions == null || permissions.length == 0)
                return;
                 requestPers(activity, callback, permissions);
        }
    }


    @SuppressLint("CheckResult")
    private static void requestPers(FragmentActivity activity, MyPermissionsCallback callback, String[] permissions) {
        if (activity == null || activity.isFinishing())
            return;
        try {
            new RxPermissions(activity).request(permissions)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aBoolean -> {
                        if (callback == null)
                            return;
                        if (aBoolean)
                            callback.succ();
                        else
                            callback.fail(activity, null);
                    }, throwable -> {
                        if (callback != null)
                            callback.fail(activity, throwable);
                    });
        } catch (Exception ignored) {
        }
    }


}

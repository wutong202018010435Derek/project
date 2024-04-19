package com.lynn.base.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.lynn.base.R;


public abstract class BaseDialog extends Dialog {

    protected Context mContext;

    public BaseDialog(@NonNull Context context) {
        super(context, R.style.NoticeDialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        Window mWindow = getWindow();
        if (mWindow != null) {
            mWindow.setGravity(showGravity()); // 此处可以设置dialog显示的地位
            int animRes = setDialogAnim();
            if (animRes != 0)
                mWindow.setWindowAnimations(animRes); // 添加动画
            mWindow.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = mWindow.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            mWindow.setAttributes(lp);
        }

        initView();
    }

    @Override
    public void show() {
        if (mContext != null) {
            if (mContext instanceof Activity) {
                Activity activity = (Activity) mContext;
                if (activity.isFinishing())
                    return;
            }
            super.show();
        }
    }

    @Override
    public void dismiss() {
        if (isShowing())
            super.dismiss();
    }

    protected abstract int getLayoutResId();

    protected abstract int showGravity();

    protected abstract void initView();

    protected int setDialogAnim() {
        return R.style.dialog_style;
    }
}

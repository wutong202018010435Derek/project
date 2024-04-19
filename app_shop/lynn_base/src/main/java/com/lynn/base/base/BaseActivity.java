package com.lynn.base.base;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.lynn.base.R;
import com.lynn.base.uitls.DensityUtil;
import com.lynn.base.uitls.ToastUtil;


import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ABC

 * note
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Unbinder unbinder;
    protected FragmentActivity mContext;
    protected ImmersionBar mImmersionBar;
    protected int barHeight;
    protected boolean isWhite;
    protected boolean isOpenRightIn = true;
    private Handler mHandler;

    //获取布局资源文件
    protected abstract int getLayoutId();

    //初始化数据
    protected abstract void onInitView(Bundle bundle);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        initWindowParameter();
        setFontSize();

        mImmersionBar = ImmersionBar.with(this);
        if (isWhite)
            mImmersionBar.statusBarDarkFont(true, 0.2f).init();
        else
            mImmersionBar.statusBarDarkFont(false).init();



        init(savedInstanceState);

    }

    private void init(@Nullable Bundle savedInstanceState) {
        if (getLayoutId() != 0) {

//            设置布局资源文件
            setContentView(getLayoutId());
//            注解绑定
            unbinder = ButterKnife.bind(this);

            autoSetBarHeight();

            onInitView(savedInstanceState);
        }
    }

    private void autoSetBarHeight() {
        try {
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                barHeight = getResources().getDimensionPixelSize(resourceId);
                View view = findViewById(R.id.top_layout);
                if (view != null) {
                    int space = DensityUtil.dip2px(6);
                    view.setPadding(0, barHeight + space, 0, space);//bar高度加上view距离状态栏的间距
                }
            }
        } catch (Exception ignored) {
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setFontSize();
    }

    protected void finishOverAnim() {
        finish();
    }

    public void finish() {
        super.finish();
//        if (isOpenRightIn)
//            overridePendingTransition(0, R.anim.base_slide_right_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishOverAnim();
    }




    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mHandler != null)
            mHandler.removeCallbacksAndMessages(null);
        if (mImmersionBar != null)
            mImmersionBar.destroy();

    }

    protected void userVisible() {
    }

    protected void userInvisible() {
    }

    /**
     * 设置状态栏等与Window相关的参数
     */
    protected void initWindowParameter() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0+
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            //getWindow().setNavigationBarColor();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4~5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                    localLayoutParams.flags);
        }
    }

    public void setFontSize() {
        //字体不随手机设置不变化
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        displayMetrics.scaledDensity = displayMetrics.density;

    }

    protected void showToast(String msg) {
        if (isDestroyed() || isFinishing())
            return;
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    protected void showCenterToast(String msg) {
        if (isDestroyed() || isFinishing())
            return;
        ToastUtil.showShortCenter(mContext, msg);
    }

    protected void showLongToast(String msg) {
        if (isDestroyed() || isFinishing())
            return;
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

//    public void isRightSlipExit() {
//        layout.attachToActivity(this);
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
//            if (KeyboardUtils.isShouldHideInput(v, ev)) {
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                if (imm != null) {
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                }
//            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        try {
            if (getWindow().superDispatchTouchEvent(ev)) {
                return true;
            }
        } catch (Exception ignored) {
        }
        return onTouchEvent(ev);
    }


    protected Handler getHandler() {
        if (mHandler == null)
            mHandler = new Handler();
        return mHandler;
    }
}

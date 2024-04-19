package com.lynn.base.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;


import com.lynn.base.R;
import com.lynn.base.uitls.DensityUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected Unbinder unbinder;
    protected View rootView;
    protected FragmentActivity mContext = null;//context
    protected Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            BaseFragment.this.handleMessage(msg);
        }
    };
    protected int barHeight;
    protected String pageId;
    protected int fragmentType;//默认0，一般是多个fragment时。1：单个fragment。2、3：特殊生命周期管制的，区别：2fragment中又有多个fragment，3是单独的

    //获取布局资源文件
    protected abstract int getLayoutId();

    //初始化数据
    protected abstract void onInitView(Bundle bundle);

    protected void handleMessage(Message msg) {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
            return rootView;
        }
        if (getLayoutId() != 0) {
            rootView = inflater.inflate(getLayoutId(), container, false);
        } else {
            rootView = super.onCreateView(inflater, container, savedInstanceState);
        }
        unbinder = ButterKnife.bind(this, rootView);

        autoSetBarHeight(rootView);

        onInitView(savedInstanceState);
        return rootView;
    }

    private void autoSetBarHeight(View rootView) {
        if (rootView == null || getActivity() == null)
            return;
        try {
            int resourceId = getActivity().getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                barHeight = getActivity().getResources().getDimensionPixelSize(resourceId);
                View view = rootView.findViewById(R.id.top_layout);
                if (view != null) {
                    int space = DensityUtil.dip2px(6);
                    view.setPadding(0, barHeight + space, 0, space);
                }
            }
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setFontSize();
    }

    public void setFontSize() {
        //字体不随手机设置不变化
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        displayMetrics.scaledDensity = displayMetrics.density;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHandler.removeCallbacksAndMessages(null);
        if (unbinder != null) {
            unbinder.unbind();
        }
        ViewGroup viewGroup = (ViewGroup) rootView.getParent();
        if (viewGroup != null && rootView != null) {
            viewGroup.removeView(rootView);
            rootView = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    protected void showToast(String msg) {
        if (getActivity() == null || getActivity().isFinishing() || getActivity().isDestroyed())
            return;
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(String msg) {
        if (getActivity() == null || getActivity().isFinishing() || getActivity().isDestroyed())
            return;
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    protected boolean isHidden = false;



    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }
}

package com.edu.squash;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.edu.squash.app.MyApp;
import com.edu.squash.ui.home.HomeFragment;
import com.edu.squash.ui.me.MeFragment;
import com.edu.squash.ui.two.TwoFragment;
import com.lynn.base.base.BaseActivity;
import com.socks.library.KLog;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.frame_Layout)
    FrameLayout frameLayout;
    @BindView(R.id.tab_home)
    LinearLayout tabHome;
    @BindView(R.id.tab_two)
    LinearLayout tabTwo;
//    @BindView(R.id.tab_three)
//    LinearLayout tabThree;
    @BindView(R.id.tab_me)
    LinearLayout tabMe;
    @BindView(R.id.root_layout)
    FrameLayout rootLayout;

    private HomeFragment mHomeFragment;
    private TwoFragment mTwoFragment;
//    private ThreeFragment mThreeFragment;
    private MeFragment mMeFragment;

    private List<View> tabList;

    private int currentTabIndex;
    private Fragment currFragment;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        isWhite = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView(Bundle bundle){
        ButterKnife.bind(this);

        tabList = new ArrayList<>();
        tabList.add(tabHome);
        tabList.add(tabTwo);
//        tabList.add(tabThree);
        tabList.add(tabMe);

        for (View view : tabList)
            view.setOnClickListener(this);

        tabHome.performClick();

        try {
//            startDingwei();
        }catch (Exception e){

        }

//        //检测是否有写的权限
//        int permission = ActivityCompat.checkSelfPermission(MainActivity.this,
//                "android.permission.WRITE_EXTERNAL_STORAGE");
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            try {
//                // 没有写的权限，去申请写的权限，会弹出对话框
//                ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_home:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.getInstance();
                }
                switchFragment(currFragment, mHomeFragment, "home");
                changePageSelect(0);
                break;
            case R.id.tab_two:
                if (mTwoFragment == null) {
                    mTwoFragment = TwoFragment.getInstance();
                }
                switchFragment(currFragment, mTwoFragment, "shop");
                changePageSelect(1);
                break;
//            case R.id.tab_three:
//                if (mThreeFragment == null) {
//                    mThreeFragment = ThreeFragment.getInstance();
//                }
//                switchFragment(currFragment, mThreeFragment, "ai");
//                changePageSelect(1);
//                break;

            case R.id.tab_me:
//                if (MyApp.getApp().isLogin()){
                if (mMeFragment == null) {
                    mMeFragment = MeFragment.getInstance();
                }
                switchFragment(currFragment, mMeFragment, "me");
                changePageSelect(2);
//                }else {
//                    startActivity(new Intent(mContext, LoginAty.class));
//                }


                break;
        }

    }

    public void switchFragment(Fragment from, Fragment to, String tag) {
        if (to == null)
            return;
        if (from == to)
            return;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!to.isAdded()) {
            if (from == null) {
                transaction.add(R.id.frame_Layout, to, tag).show(to).commitAllowingStateLoss();
            } else {
                // 隐藏当前的fragment，add下一个fragment到Activity中并显示
                transaction.hide(from).add(R.id.frame_Layout, to, tag).show(to).commitAllowingStateLoss();
            }
        } else {
            // 隐藏当前的fragment，显示下一个
            transaction.hide(from).show(to).commitAllowingStateLoss();
        }
        currFragment = to;
    }

    public void changePageSelect(int index) {
        View view = tabList.get(index);
        tabList.get(index).setSelected(true);

        if (currentTabIndex == index) {
            return;
        }
        tabList.get(currentTabIndex).setSelected(false);
        currentTabIndex = index;
    }




}
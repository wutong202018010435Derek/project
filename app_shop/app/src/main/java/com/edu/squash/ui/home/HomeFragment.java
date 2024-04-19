package com.edu.squash.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.edu.squash.R;
import com.edu.squash.bean.DataBean;
import com.edu.squash.bean.SysCategory;
import com.edu.squash.bean.SysNotice;
import com.edu.squash.ui.three.GonglvFragment;
import com.edu.squash.utils.ImageLoadUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.reflect.TypeToken;
import com.lynn.base.base.BaseFragment;
import com.lynn.base.net.HttpResponse;
import com.lynn.base.net.OkHttpTool;
import com.lynn.base.uitls.DensityUtil;
import com.lynn.base.uitls.JsonUtil;
import com.socks.library.KLog;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by ABC
 * on 2022/3/4
 * note
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.ll_search)
    LinearLayout ll_search;

    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager2)
    ViewPager2 viewpager2;


    TabLayoutMediator mediator;

    private int activeColor = Color.parseColor("#ff678f");
    private int normalColor = Color.parseColor("#666666");

    private int activeSize = 16;
    private int normalSize = 15;



    public static HomeFragment getInstance() {
        HomeFragment fragment = new HomeFragment();
//        Bundle bundle = new Bundle();
//        if (!TextUtils.isEmpty(classType))
//            bundle.putString(Contact.KEY_TYPE, classType);
//        bundle.putBoolean("isLocalData", isLocalData);
//        bundle.putBoolean("isLoginInit", isLoginInit);
//        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        initView();
    }

    private void initView() {
        toService();
        toGetBanner();
        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,HotelListAty.class));
            }
        });






    }

    private void toGetBanner() {
        String url = OkHttpTool.Base_URL + "app/queryAllBanner?parkGroud=1";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();



        OkHttpTool.httpGet(url, parameters, new OkHttpTool.ResponseCallback() {
            @Override
            public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {

                KLog.e("测试", "----" + isSuccess + "  " + response + "    ");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (isSuccess && !TextUtils.isEmpty(response)) {

                            HttpResponse<List<DataBean>> message = JsonUtil.parseJson(response, new TypeToken<HttpResponse<List<DataBean>>>() {
                            }.getType());
                            if (message != null) {
                                if (message.getCode() == 200 && !message.getData().isEmpty()) {

                                    banner.setAdapter(new BannerImageAdapter<DataBean>(message.getData()) {
                                                @Override
                                                public void onBindView(BannerImageHolder holder, DataBean data, int position, int size) {
                                                    String urlImg = data.getbImg();
                                                    urlImg =  urlImg.replaceAll("localhost",OkHttpTool.BASE_IP);
                                                    KLog.e(" -----测试"   + urlImg);
                                                    //图片加载自己实现
                                                    Glide.with(holder.itemView)
                                                            .load(urlImg)
                                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                                                            .into(holder.imageView);

//                        ImageLoadUtils.loadRoundImageCenterCrop(getActivity(),data.imageRes, DensityUtil.dip2px(20),holder.imageView);
                                                }
                                            })
                                            .addBannerLifecycleObserver(getActivity())//添加生命周期观察者
                                            .setIndicator(new CircleIndicator(getActivity()));

                                    banner.start();

                                } else {
//                                    ToastUtil.showShort(mContext, "还没数据");
                                }

                            }

                        }
                    }
                });
            }
        });
    }


    public void toService() {
        String url = OkHttpTool.Base_URL + "app/getCategory?parkGroud=1";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();



        OkHttpTool.httpGet(url, parameters, new OkHttpTool.ResponseCallback() {
            @Override
            public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {

                KLog.e("测试", "----" + isSuccess + "  " + response + "    ");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (isSuccess && !TextUtils.isEmpty(response)) {

                            HttpResponse<List<SysCategory>> message = JsonUtil.parseJson(response, new TypeToken<HttpResponse<List<SysCategory>>>() {
                            }.getType());
                            if (message != null) {
                                if (message.getCode() == 200 && !message.getData().isEmpty()) {
                                    String[] items = new String[message.getData().size()];
                                    for (int i = 0; i < message.getData().size(); i++) {
                                        items[i] = message.getData().get(i).getCategoryName();
                                    }
                                    iniTab(items);
                                } else {
//                                    ToastUtil.showShort(mContext, "还没数据");
                                }

                            }

                        }
                    }
                });
            }
        });
    }

    private void iniTab(String[] tabs) {


        //禁用预加载
        viewpager2.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);
        //Adapter
        viewpager2.setAdapter(new FragmentStateAdapter(getChildFragmentManager(), getLifecycle()) {

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                //FragmentStateAdapter内部自己会管理已实例化的fragment对象。
                // 所以不需要考虑复用的问题
                return GonglvFragment.getInstance(position, tabs[position]);
            }

            @Override
            public int getItemCount() {
                return tabs.length;
            }
        });
        //viewPager 页面切换监听监听
        viewpager2.registerOnPageChangeCallback(changeCallback);

        mediator = new TabLayoutMediator(tabLayout, viewpager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @SuppressLint("WrongConstant")
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                //这里可以自定义TabView
                TextView tabView = new TextView(mContext);

                int[][] states = new int[2][];
                states[0] = new int[]{android.R.attr.state_selected};
                states[1] = new int[]{};

                int[] colors = new int[]{activeColor, normalColor};
                ColorStateList colorStateList = new ColorStateList(states, colors);
                tabView.setText(tabs[position]);
                tabView.setTextSize(normalSize);
                tabView.setTextColor(colorStateList);
                tabView.setGravity(Gravity.CENTER);
                tabView.setLayoutDirection(Gravity.CENTER);
//                ViewGroup.LayoutParams layoutParams = tabView.getLayoutParams();
//                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//                tabView.setLayoutParams(layoutParams);

                tab.setCustomView(tabView);
            }
        });
        //要执行这一句才是真正将两者绑定起来
        mediator.attach();

    }


    private ViewPager2.OnPageChangeCallback changeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            //可以来设置选中时tab的大小
            int tabCount = tabLayout.getTabCount();
            for (int i = 0; i < tabCount; i++) {
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                TextView tabView = (TextView) tab.getCustomView();
                if (tab.getPosition() == position) {
                    tabView.setTextSize(activeSize);
                    tabView.setTypeface(Typeface.DEFAULT_BOLD);
                } else {
                    tabView.setTextSize(normalSize);
                    tabView.setTypeface(Typeface.DEFAULT);
                }
            }
        }
    };

    //    @Override
    public void onDestroy() {
        if (mediator != null)
            mediator.detach();
        if (viewpager2 != null)
            viewpager2.unregisterOnPageChangeCallback(changeCallback);
        super.onDestroy();
    }


}


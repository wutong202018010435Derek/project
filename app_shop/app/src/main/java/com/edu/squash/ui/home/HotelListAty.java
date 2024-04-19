package com.edu.squash.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.edu.squash.R;
import com.edu.squash.app.MyApp;
import com.edu.squash.bean.ParkListBean;
import com.edu.squash.bean.SysGoods;
import com.edu.squash.ui.me.MyAdressAty;
import com.edu.squash.ui.three.DetailActivity;
import com.edu.squash.ui.three.DynamicAdapter;
import com.google.gson.reflect.TypeToken;
import com.lynn.base.base.BaseActivity;
import com.lynn.base.net.HttpResponse;
import com.lynn.base.net.OkHttpTool;
import com.lynn.base.uitls.JsonUtil;
import com.lynn.base.uitls.ToastUtil;
import com.lynn.base.view.AppTitleLayout;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class HotelListAty extends BaseActivity {


    @BindView(R.id.titleLayout)
    AppTitleLayout titleLayout;


    @BindView(R.id.rv_device)
    RecyclerView rvSugess;
    @BindView(R.id.et_keyword)
    EditText et_keyword;
    @BindView(R.id.iv_search)
    ImageView iv_search;
//    @BindView(R.id.ll_name)
//    LinearLayout ll_name;


//    @Override
//    protected int getLayoutId() {
//        return R.layout.aty_my_device;
//    }
    protected int getLayoutId() {
        return R.layout.aty_search;
    }


    private List<SysGoods> mList = new ArrayList<>();
    DynamicAdapter mNoticeAdapter;

    @Override
    protected void onInitView(Bundle bundle) {
//        ll_name.setVisibility(View.VISIBLE);

        titleLayout.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishOverAnim();
            }
        });
//        titleLayout.setTitleText("附近");


        mNoticeAdapter = new DynamicAdapter(R.layout.item_dynamic, mList);
        rvSugess.setLayoutManager(new GridLayoutManager(mContext,2));
//        rvSugess.setLayoutManager(new LinearLayoutManager(mContext));
        rvSugess.setAdapter(mNoticeAdapter);
        mNoticeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent mIntent = new Intent(mContext, DetailActivity.class);
                mIntent.putExtra("mData", JsonUtil.toJson(mList.get(position)));
                startActivity(mIntent);
            }
        });

        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str  = et_keyword.getText().toString();
                if (!TextUtils.isEmpty(str)){
                    toService(str);
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void toService(String keyword) {
        String url = OkHttpTool.Base_URL + "app/getMyGoods?my=1";//也可以在这拼接参数
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("goodsName", keyword);

        OkHttpTool.httpGet(url, parameters, new OkHttpTool.ResponseCallback() {
            @Override
            public void onResponse(final boolean isSuccess, final int responseCode, final String response, Exception exception) {

                KLog.e("测试", "----" + isSuccess + "  " + response + "    ");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (isSuccess && !TextUtils.isEmpty(response)) {

                            HttpResponse<List<SysGoods>> message = JsonUtil.parseJson(response, new TypeToken<HttpResponse<List<SysGoods>>>() {
                            }.getType());
                            if (message != null) {
                                if (message.getCode() == 200) {
                                    mList.clear();
                                    if (message.getData() != null && message.getData().size() > 0) {
                                        mList.addAll(message.getData());
                                    }
                                    mNoticeAdapter.notifyDataSetChanged();

                                }else {
                                    ToastUtil.showShort(mContext,"no data");
                                }

                            }

                        }
                    }
                });
            }
        });
    }

}

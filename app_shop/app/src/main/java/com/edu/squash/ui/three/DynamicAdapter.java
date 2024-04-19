package com.edu.squash.ui.three;



import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.edu.squash.R;
import com.edu.squash.app.MyApp;
import com.edu.squash.bean.EventMessage;
import com.edu.squash.bean.ParkListBean;
import com.edu.squash.bean.SysGoods;
import com.edu.squash.sql.GoodsDaoManager;
import com.edu.squash.utils.ImageLoadUtils;
import com.lynn.base.uitls.ToastUtil;


import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by ABC
 * on 2020/3/18
 * note
 */
public class DynamicAdapter extends BaseQuickAdapter<SysGoods, BaseViewHolder> {




    public DynamicAdapter(int layoutResId, @Nullable List<SysGoods> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final SysGoods item) {
        helper.setText(R.id.tv_title, item.getGoodsName() + "");

        ImageView imageView = helper.getView(R.id.iv_head);
        ImageLoadUtils.loadImage(mContext,item.getGoodsImg(),imageView);
//        helper.setText(R.id.tv_title, item.getGoodsName() + "");
        helper.setText(R.id.tv_desc, item.getGoodsDesc() + "");
        helper.setText(R.id.tv_price, "$" + item.getGoodsPrice());

        helper.getView(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setGoodsShop(1);
                item.setGoodsCollect(0);
                item.setReleaseTime(System.currentTimeMillis());
                item.setGoodsUserId(MyApp.getApp().getUser().getUserId());
                if (GoodsDaoManager.getInstance().addDataToList(item)) {
                    ToastUtil.showShortCenter(mContext, "success");

                    EventBus.getDefault().post(new EventMessage(1,"refresh shop"));
                }
            }
        });
//
//
//        ImageView iv_head = helper.getView(R.id.iv_head);
//        if (item.getpType() ==1){
//            iv_head.setBackgroundResource(R.mipmap.bc_1);
//        }else {
//            iv_head.setBackgroundResource(R.mipmap.bc_2);
//        }


    }
}

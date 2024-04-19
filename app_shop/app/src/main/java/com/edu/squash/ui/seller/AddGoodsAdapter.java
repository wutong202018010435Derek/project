package com.edu.squash.ui.seller;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.edu.squash.R;
import com.edu.squash.bean.SysGoods;
import com.edu.squash.bean.SysYue;
import com.edu.squash.utils.ImageLoadUtils;

import java.util.List;

/**
 * Created by ABC

 * note
 */
public class AddGoodsAdapter extends BaseQuickAdapter<SysGoods, BaseViewHolder> {


    public interface OnOverClickLister {
        void OnOver(SysGoods sysGoods);

        void OnDel(SysGoods sysGoods);
    }

    private OnOverClickLister onOverClickLister;

    public AddGoodsAdapter(int layoutResId, @Nullable List<SysGoods> data, OnOverClickLister onOverClickLister) {
        super(layoutResId, data);
        this.onOverClickLister = onOverClickLister;

    }

    @Override
    protected void convert(final BaseViewHolder helper, final SysGoods item) {
        ImageView iv_img = helper.getView(R.id.iv_img);
        ImageLoadUtils.loadImage(mContext,item.getGoodsImg(),iv_img);
        helper.setText(R.id.tv_name, item.getGoodsName());
        helper.setText(R.id.tv_desc, "" + item.getGoodsDesc() );
        helper.setText(R.id.tv_price, "$" + item.getGoodsPrice() );

//        helper.setText(R.id.tv_desc, "预约了" + item.getyDate() + item.getyTime() + "小时");
//        helper.setText(R.id.tv_state, item.getyState() == 0 ? "预约..." : (item.getyState() == 1 ? "完成" : "取消..."));
//        if (item.getyState() == 0) {
//            helper.getView(R.id.btn_over).setVisibility(View.VISIBLE);
//            helper.getView(R.id.btn_cancle).setVisibility(View.VISIBLE);
//
//        } else {
//            helper.getView(R.id.btn_over).setVisibility(View.INVISIBLE);
//            helper.getView(R.id.btn_cancle).setVisibility(View.INVISIBLE);
//        }

        helper.getView(R.id.btn_shangjia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setGoodsState(1);

                onOverClickLister.OnOver(item);
            }
        });

        helper.getView(R.id.btn_xiajia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setGoodsState(0);
                onOverClickLister.OnOver(item);
            }
        });


        helper.getView(R.id.btn_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onOverClickLister.OnDel(item);
            }
        });

        if (item.getGoodsState() == 1){
            helper.getView(R.id.btn_xiajia).setVisibility(View.VISIBLE);
            helper.getView(R.id.btn_shangjia).setVisibility(View.GONE);
        }else {
            helper.getView(R.id.btn_xiajia).setVisibility(View.GONE);
            helper.getView(R.id.btn_shangjia).setVisibility(View.VISIBLE);
        }


    }
}

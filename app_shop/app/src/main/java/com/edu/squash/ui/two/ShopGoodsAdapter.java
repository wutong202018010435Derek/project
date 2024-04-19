package com.edu.squash.ui.two;

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
public class ShopGoodsAdapter extends BaseQuickAdapter<SysGoods, BaseViewHolder> {


    public interface OnOverClickLister {
        void OnNumdel(SysGoods sysGoods,int postion);
        void OnNumAdd(SysGoods sysGoods,int postion);
        void OnDel(SysGoods sysGoods,int postion);
    }

    private OnOverClickLister onOverClickLister;

    public ShopGoodsAdapter(int layoutResId, @Nullable List<SysGoods> data, OnOverClickLister onOverClickLister) {
        super(layoutResId, data);
        this.onOverClickLister = onOverClickLister;

    }

    @Override
    protected void convert(final BaseViewHolder helper, final SysGoods item) {
        ImageView iv_img = helper.getView(R.id.iv_img);
        ImageLoadUtils.loadImage(mContext,item.getGoodsImg(),iv_img);
        helper.setText(R.id.tv_name, item.getGoodsName());
        helper.setText(R.id.tv_num, "" + item.getGoodsShopNum() );
        helper.setText(R.id.tv_price, "$" + item.getGoodsPrice() );

//        helper.setText(R.id.tv_state, item.getyState() == 0 ? "预约..." : (item.getyState() == 1 ? "完成" : "取消..."));
//        if (item.getyState() == 0) {
//            helper.getView(R.id.btn_over).setVisibility(View.VISIBLE);
//            helper.getView(R.id.btn_cancle).setVisibility(View.VISIBLE);
//
//        } else {
//            helper.getView(R.id.btn_over).setVisibility(View.INVISIBLE);
//            helper.getView(R.id.btn_cancle).setVisibility(View.INVISIBLE);
//        }

        helper.getView(R.id.iv_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                onOverClickLister.OnNumdel(item,helper.getAdapterPosition());
            }
        });

        helper.getView(R.id.iv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onOverClickLister.OnNumAdd(item,helper.getAdapterPosition());
            }
        });
        helper.getView(R.id.btn_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onOverClickLister.OnDel(item,helper.getAdapterPosition());
            }
        });


    }
}

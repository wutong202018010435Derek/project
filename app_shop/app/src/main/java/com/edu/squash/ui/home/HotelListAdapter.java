package com.edu.squash.ui.home;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.edu.squash.R;
import com.edu.squash.bean.ParkListBean;

import java.util.List;

/**
 * Created by ABC
 * on 2024/2/20
 * note
 */
public class HotelListAdapter extends BaseQuickAdapter<ParkListBean, BaseViewHolder> {




    public HotelListAdapter(int layoutResId, @Nullable List<ParkListBean> data) {
        super(layoutResId, data);


    }


    @Override
    protected void convert(final BaseViewHolder helper, final ParkListBean item) {

        helper.setText(R.id.tv_title, item.getpName() + "");
        helper.setText(R.id.tv_desc, item.getpDesc() + "");


        ImageView iv_head = helper.getView(R.id.iv_head);
        if (item.getpType() ==1){
            iv_head.setBackgroundResource(R.mipmap.bc_1);
        }else {
            iv_head.setBackgroundResource(R.mipmap.bc_2);
        }



    }
}


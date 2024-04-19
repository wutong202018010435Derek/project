package com.edu.squash.ui.me;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.edu.squash.R;
import com.edu.squash.bean.SysGoods;
import com.edu.squash.bean.SysYue;

import java.util.List;

/**
 * Created by ABC
 * on 2024/2/22
 * note
 */
public class MyCollectAdapter extends BaseQuickAdapter<SysGoods, BaseViewHolder> {


    public interface OnOverClickLister {
        void OnOver(SysYue sysYue);

        void OnCancle(SysYue sysYue);
    }

    private OnOverClickLister onOverClickLister;

    public MyCollectAdapter(int layoutResId, @Nullable List<SysGoods> data, OnOverClickLister onOverClickLister) {
        super(layoutResId, data);
        this.onOverClickLister = onOverClickLister;

    }

    @Override
    protected void convert(final BaseViewHolder helper, final SysGoods item) {
        TextView tv_title = helper.getView(R.id.tv_name);
        tv_title.setText(item.getGoodsName() + "  ");
//        helper.setText(R.id.tv_title, item.getyParkname());
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
//
//        helper.getView(R.id.btn_over).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                item.setyState(1);
//                onOverClickLister.OnOver(item);
//            }
//        });
//
//        helper.getView(R.id.btn_cancle).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                item.setyState(2);
//                onOverClickLister.OnCancle(item);
//            }
//        });


    }
}

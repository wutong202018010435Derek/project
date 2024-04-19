package com.edu.squash.ui.me;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.edu.squash.R;
import com.edu.squash.bean.OrderBean;
import com.edu.squash.bean.SysGoods;
import com.edu.squash.bean.SysYue;

import java.util.List;

/**
 * Created by ABC
 * on 2024/2/22
 * note
 */
public class MyOrderAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {


    public interface OnOverClickLister {
        void OnOver(OrderBean sysYue);

        void OnCancle(OrderBean sysYue);
    }

    private OnOverClickLister onOverClickLister;

    public MyOrderAdapter(int layoutResId, @Nullable List<OrderBean> data, OnOverClickLister onOverClickLister) {
        super(layoutResId, data);
        this.onOverClickLister = onOverClickLister;

    }

    @Override
    protected void convert(final BaseViewHolder helper, final OrderBean item) {
        TextView tv_title = helper.getView(R.id.tv_name);
        tv_title.setText(item.getoGoodsname() + "  ");
        helper.setText(R.id.tv_money, item.getoMoney());
        helper.setText(R.id.tv_orderID, "orderId：" + item.getoId());

        TextView btn_cancle = helper.getView(R.id.btn_cancle);
        TextView btn_over = helper.getView(R.id.btn_over);

        //0下单 1取消 2付款  3完成 4 评论
        if (item.getoState() == 2) {
            btn_cancle.setVisibility(View.GONE);
            btn_over.setVisibility(View.VISIBLE);

        } else  if (item.getoState() == 3) {
            btn_cancle.setVisibility(View.VISIBLE);
            btn_over.setVisibility(View.GONE);

        } else {
            btn_cancle.setVisibility(View.INVISIBLE);
            btn_over.setVisibility(View.INVISIBLE);
        }
//
        helper.getView(R.id.btn_over).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOverClickLister.OnOver(item);

            }
        });

        helper.getView(R.id.btn_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mContext.startActivity(new Intent(mContext,EvaluateCommitActivity.class).putExtra("mData",item.getoId()+""));
            }
        });


    }
}

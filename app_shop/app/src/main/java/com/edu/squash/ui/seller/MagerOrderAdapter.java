package com.edu.squash.ui.seller;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.edu.squash.R;
import com.edu.squash.bean.OrderBean;
import com.edu.squash.ui.me.EvaluateCommitActivity;

import java.util.List;

/**
 * Created by ABC
 * on 2024/2/22
 * note
 */
public class MagerOrderAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {


    public interface OnOverClickLister {
        void OnOver(OrderBean sysYue);

        void OnCancle(OrderBean sysYue);
    }

    private OnOverClickLister onOverClickLister;

    public MagerOrderAdapter(int layoutResId, @Nullable List<OrderBean> data, OnOverClickLister onOverClickLister) {
        super(layoutResId, data);
        this.onOverClickLister = onOverClickLister;

    }

    @Override
    protected void convert(final BaseViewHolder helper, final OrderBean item) {
        TextView tv_state = helper.getView(R.id.tv_state);
        TextView tv_title = helper.getView(R.id.tv_name);
        tv_title.setText(item.getoGoodsname() + "  ");
        helper.setText(R.id.tv_money, item.getoMoney());
        helper.setText(R.id.tv_orderID, "orderId：" + item.getoId());

        if (item.getoState() == 2) {
            tv_state.setText("paied");
        } else if (item.getoState() == 3) {
            tv_state.setText("finish");
        } else if (item.getoState() == 4) {
            tv_state.setText("commented");
        } else if (item.getoState() == 5) {
            tv_state.setText("refund");
        } else if (item.getoState() == 1) {
            tv_state.setText("cancel");
        }

//        TextView btn_cancle = helper.getView(R.id.btn_cancle);
        TextView btn_over = helper.getView(R.id.btn_over);

        //0下单 1取消 2付款  3完成 4 评论 5退款
//        if (item.getoState() == 2) {
//            btn_cancle.setVisibility(View.GONE);
//            btn_over.setVisibility(View.VISIBLE);
//
//        } else
        if (item.getoState() == 2) {
//            btn_cancle.setVisibility(View.VISIBLE);
            btn_over.setVisibility(View.VISIBLE);

        } else {
//            btn_cancle.setVisibility(View.INVISIBLE);
            btn_over.setVisibility(View.INVISIBLE);
        }
//
        helper.getView(R.id.btn_over).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setoState(5);
                onOverClickLister.OnOver(item);

            }
        });

//        helper.getView(R.id.btn_cancle).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               mContext.startActivity(new Intent(mContext, EvaluateCommitActivity.class).putExtra("mData",item.getoId()+""));
//            }
//        });


    }
}

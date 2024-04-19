package com.edu.squash.ui.three;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.edu.squash.R;
import com.edu.squash.bean.OrderBean;
import com.edu.squash.bean.SysYue;
import com.edu.squash.utils.ImageLoadUtils;


import java.util.List;

/**
 * Created by ABC
 * on 2020/3/26
 * note
 */
public class ReplayAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {


    public ReplayAdapter(int layoutResId, @Nullable List<OrderBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final OrderBean item) {
//        helper.setText(R.id.tv_reply_nick, item.getRepyNick() + "");
        helper.setText(R.id.tv_reply_content, item.getoComment() + "");
//        if (!TextUtils.isEmpty(item.getRepyHead())) {
//            ImageLoadUtils.loadCircleImage(mContext, item.getRepyHead(), helper.getView(R.id.iv_reply_head));
//        }


    }
}

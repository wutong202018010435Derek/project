package com.edu.squash.ui.home;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.edu.squash.R;
import com.edu.squash.bean.LineBean;

import java.util.List;

public class LineAdapter extends BaseQuickAdapter<LineBean, BaseViewHolder> {


    public LineAdapter(int layoutResId, @Nullable List<LineBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final LineBean item) {
//        String str = TimeUtil.timeStamp2Date(item.getClockTime(), "yyyy-MM-dd HH:mm:ss");
//
        TextView tv_start = helper.getView(R.id.tv_start);
        tv_start.setText(item.getName1() + "    ===> ");

        TextView tv_end = helper.getView(R.id.tv_end);
        tv_end.setText(item.getName2());

        TextView tv_remark = helper.getView(R.id.tv_remark);
        tv_remark.setText(item.getRemark());


    }
}

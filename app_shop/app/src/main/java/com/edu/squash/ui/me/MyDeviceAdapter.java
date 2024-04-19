package com.edu.squash.ui.me;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.edu.squash.R;
import com.edu.squash.bean.OrderBean;
import com.edu.squash.bean.ParkListBean;
import com.edu.squash.bean.SysAddress;

import java.util.List;

public class MyDeviceAdapter extends BaseQuickAdapter<SysAddress, BaseViewHolder> {


    public interface OnOverClickLister {


        void OnCancle(SysAddress sysYue);
    }

    private OnOverClickLister onOverClickLister;

    public MyDeviceAdapter(int layoutResId, @Nullable List<SysAddress> data, OnOverClickLister onOverClickLister) {
        super(layoutResId, data);
        this.onOverClickLister = onOverClickLister;


    }

    @Override
    protected void convert(final BaseViewHolder helper, final SysAddress item) {
//        String str = TimeUtil.timeStamp2Date(item.getClockTime(), "yyyy-MM-dd HH:mm:ss");
//
        TextView tv_title = helper.getView(R.id.tv_name);
//        tv_title.setText(item.getpLocation() + "  ");
        helper.setText(R.id.tv_title,item.getaProvice() + item.getaCity() + item.getaArea() + item.getaStreet() + item.getaAddress());
        helper.setText(R.id.tv_desc,item.getaConsignee()   + "   "  + item.getaPhone());
//        helper.setText(R.id.tv_state,item.getpState() == 1?"忙碌":"空闲");
//        helper.setText(R.id.tv_name,item.getpName());
        helper.getView(R.id.tv_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOverClickLister.OnCancle(item);
            }
        });





    }
}

package com.edu.squash.ui.three;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.edu.squash.R;
import com.edu.squash.bean.OrderBean;
import com.edu.squash.bean.SysGoods;
import com.edu.squash.utils.ImageLoadUtils;
import com.lynn.base.uitls.ZeroUtil;


import java.util.List;

/**
 * Created by ABC

 * note
 */
public class OrderAdapter extends BaseQuickAdapter<SysGoods, BaseViewHolder> {
    public OrderAdapter(int layoutResId, @Nullable List<SysGoods> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, SysGoods item) {

        if (!TextUtils.isEmpty(item.getGoodsImg())) {
            ImageLoadUtils.loadImage(mContext, item.getGoodsImg(), helper.getView(R.id.iv_img));
        }
//
        helper.setText(R.id.tv_content, item.getGoodsName() + "");
        helper.setText(R.id.tv_price, "$:" + item.getGoodsPrice());
        helper.setText(R.id.tv_num, "Number：" + item.getGoodsShopNum());
//
        helper.setText(R.id.tv_count, "Total:" + ZeroUtil.subZeroAndDotTwo(item.getGoodsShopNum() * Double.parseDouble(item.getGoodsPrice())));


//        Button button = helper.getView(R.id.btn_add);
//        if (item.getSelectCount() == 0) {
//            button.setText("购买");
//        } else {
//            button.setText("已加入");
//        }
//
//        helper.getView(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //删除
//                if (mOnBuyOnclickListner != null) {
//                    mOnBuyOnclickListner.onBuy(helper.getAdapterPosition());
//                }
//
//            }
//        });


    }
}

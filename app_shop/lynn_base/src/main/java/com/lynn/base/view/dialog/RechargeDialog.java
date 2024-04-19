package com.lynn.base.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.lynn.base.R;
import com.lynn.base.uitls.ToastUtil;


/**
 * Created by ABC
 * on 2020/4/8
 * note
 */
public class RechargeDialog extends Dialog {

    private Context context;
    String mMoney;
    String oOrderString;
    IClickPayCallBack mIClickPayCallBack;

    public RechargeDialog(Context context, int theme, String oOrderString, String mMoney, IClickPayCallBack mIClickPayCallBack) {
        super(context, theme);
        this.context = context;
        this.oOrderString = oOrderString;
        this.mMoney = mMoney;
        this.mIClickPayCallBack = mIClickPayCallBack;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pay);

        Window mWindow = getWindow();
        mWindow.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的地位
        mWindow.setWindowAnimations(R.style.dialog_style); // 添加动画
        mWindow.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = mWindow.getAttributes();

        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindow.setAttributes(lp);

        TextView txt_money = findViewById(R.id.txt_money);
        TextView tv_order_info = findViewById(R.id.tv_order_info);
        EditText et_password = findViewById(R.id.et_password);

        txt_money.setText(mMoney);
        tv_order_info.setText(oOrderString);

        findViewById(R.id.btn_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String payPs = et_password.getText().toString().trim();
                if (TextUtils.equals(payPs, "123456")) {
                    if (mIClickPayCallBack != null) {
                        mIClickPayCallBack.toPayMoney(mMoney);
                        dismiss();
                    }

                } else {
                    ToastUtil.showShort(context, "incorrect ");
                }

            }
        });

        findViewById(R.id.tv_zhifubao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIClickPayCallBack != null)
                    mIClickPayCallBack.toZhiFuBao();
                dismiss();
            }
        });

    }

    public void OnShowDialog() {
        RechargeDialog.this.show();
        RechargeDialog.this.setCanceledOnTouchOutside(true);
        RechargeDialog.this.setCancelable(true);
    }

    public interface IClickPayCallBack {
        void toPayMoney(String mMoney);

        void toZhiFuBao();
    }
}

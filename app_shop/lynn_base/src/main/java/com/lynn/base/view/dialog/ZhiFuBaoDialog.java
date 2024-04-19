package com.lynn.base.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lynn.base.R;


/**
 * Created by ABC
 * on 2021/4/17
 * note
 */
public class ZhiFuBaoDialog extends Dialog {

    private Context context;

    IClickPayCallBack mIClickPayCallBack;

    public ZhiFuBaoDialog(Context context, int theme, IClickPayCallBack mIClickPayCallBack) {
        super(context, theme);
        this.context = context;
        this.mIClickPayCallBack = mIClickPayCallBack;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_zhifubao);

        Window mWindow = getWindow();
        mWindow.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的地位
        mWindow.setWindowAnimations(R.style.dialog_style); // 添加动画
        mWindow.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = mWindow.getAttributes();

        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindow.setAttributes(lp);


        findViewById(R.id.btn_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIClickPayCallBack != null) {
                    mIClickPayCallBack.toPayFinish();
                }
                dismiss();

            }
        });

    }

    public void OnShowDialog() {
        this.show();
        this.setCanceledOnTouchOutside(true);
        this.setCancelable(true);
    }

    public interface IClickPayCallBack {
        void toPayFinish();
    }
}

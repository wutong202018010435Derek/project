package com.lynn.base.view.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lynn.base.R;


public class BeeDialog extends BaseDialog {


    String beeString;

    public BeeDialog(@NonNull Context context, String beeString) {
        super(context);
        this.beeString = beeString;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_bee;
    }

    @Override
    protected int showGravity() {
        return Gravity.CENTER;
    }

    @Override
    protected void initView() {
        TextView tvTip = findViewById(R.id.tv_bee);
        tvTip.setText(beeString);

        findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


}

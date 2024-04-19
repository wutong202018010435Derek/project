package com.lynn.base.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lynn.base.R;


public class MyJiFenView extends LinearLayout {

    public MyJiFenView(Context context) {
        super(context);
    }

    public MyJiFenView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        @SuppressLint("CustomViewStyleable") TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TitleLayout);
        initView(context, array);
        array.recycle();
    }

    private TextView tv_left;
    private TextView tv_value;
    private TextView tv_right;

    private void initView(Context context, TypedArray typedArray) {


        View view = LayoutInflater.from(context).inflate(R.layout.layout_jifen, this);
        tv_left = view.findViewById(R.id.tv_left);
        tv_value = view.findViewById(R.id.tv_value);
        tv_right = view.findViewById(R.id.tv_right);


        tv_value.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tv_value.getPaint().setAntiAlias(true);//抗锯

    }

    public void setConfig(String mLeft, String mRight) {
        tv_left.setText(mLeft);
        tv_right.setText( mRight);
    }

    public void setValue(String mValue) {
        tv_value.setText(mValue);
    }
}

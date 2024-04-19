package com.lynn.base.img.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class PicFrameLayout extends FrameLayout {
    public PicFrameLayout(Context context) {
        super(context);
    }

    public PicFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PicFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}

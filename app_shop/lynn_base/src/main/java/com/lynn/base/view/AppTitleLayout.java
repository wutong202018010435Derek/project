package com.lynn.base.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.lynn.base.R;
import com.lynn.base.uitls.DensityUtil;


public class AppTitleLayout extends LinearLayout {

    private RelativeLayout mRootLayout;
    private LinearLayout mAlphaBgLayout;
    private TextView mTvTitle, mRightTvTitle, mTvLeft;
    private ImageView mLeftImg, mRightImg;
    private int barHeight;

    public AppTitleLayout(Context context) {
        super(context);
    }

    public AppTitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        @SuppressLint("CustomViewStyleable") TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TitleLayout);
        initView(context, array);
        array.recycle();
    }

    private void initView(Context context, TypedArray typedArray) {
        boolean isShowAlphaBg = typedArray.getBoolean(R.styleable.TitleLayout_is_show_alpha_bg, false);
        int alphaBgColor = typedArray.getColor(R.styleable.TitleLayout_alpha_bg_color, 101);
        int alphaBg = typedArray.getResourceId(R.styleable.TitleLayout_alpha_bg, R.color.color_226);
        boolean isBlack = typedArray.getBoolean(R.styleable.TitleLayout_is_black, false);
        int rootBgColor = typedArray.getColor(R.styleable.TitleLayout_root_bg_color, Color.BLACK);
        int rootBg = typedArray.getResourceId(R.styleable.TitleLayout_root_bg, R.color.color_226);
        int leftRes = typedArray.getResourceId(R.styleable.TitleLayout_left_icon, isBlack ? R.mipmap.login_back_b : R.mipmap.login_back);
        String titleText = typedArray.getString(R.styleable.TitleLayout_title);
        int leftImgVisible = typedArray.getInteger(R.styleable.TitleLayout_left_icon_visible, View.VISIBLE);
        int rightImgVisible = typedArray.getInteger(R.styleable.TitleLayout_right_icon_visible, View.GONE);
        int rightTvVisible = typedArray.getInteger(R.styleable.TitleLayout_right_tv_visible, View.GONE);
        int rightImgRes = typedArray.getResourceId(R.styleable.TitleLayout_right_icon, -1);
        String rightText = typedArray.getString(R.styleable.TitleLayout_right_text);
        int leftTvVisible = typedArray.getInteger(R.styleable.TitleLayout_left_tv_visible, View.GONE);
        String leftText = typedArray.getString(R.styleable.TitleLayout_left_text);


        View view = LayoutInflater.from(context).inflate(R.layout.layout_title, this);
        mAlphaBgLayout = view.findViewById(R.id.top_alpha_layout);
        mRootLayout = view.findViewById(R.id.title_top_layout);
        mLeftImg = view.findViewById(R.id.img_left);
        mTvTitle = view.findViewById(R.id.tv_center_title);
        mRightTvTitle = view.findViewById(R.id.tv_right);
        mRightImg = view.findViewById(R.id.img_right);
        mTvLeft = view.findViewById(R.id.tv_left);

        if (isShowAlphaBg) {
            mAlphaBgLayout.setVisibility(VISIBLE);
            if (alphaBgColor != 101)
                mAlphaBgLayout.setBackgroundColor(alphaBgColor);
            else {
                mAlphaBgLayout.setBackgroundResource(alphaBg);
            }
        } else {
            if (rootBgColor != Color.BLACK)
                mRootLayout.setBackgroundColor(rootBgColor);
            else
                mRootLayout.setBackgroundResource(rootBg);
        }

        if (leftTvVisible == View.VISIBLE) {
            mTvLeft.setVisibility(VISIBLE);
            mTvLeft.setText(leftText);
        }

        mLeftImg.setImageResource(leftRes);
        if (leftImgVisible != View.VISIBLE) {
            mLeftImg.setVisibility(INVISIBLE);
        }

        mTvTitle.setText(titleText);
        if (isBlack)
            mTvTitle.setTextColor(ResourcesCompat.getColor(getResources(), R.color.color_323, null));

        if (rightImgVisible == View.VISIBLE) {
            mRightImg.setImageResource(rightImgRes);
            mRightImg.setVisibility(VISIBLE);
        }

        if (rightTvVisible == View.VISIBLE) {
            mRightTvTitle.setText(rightText);
            mRightTvTitle.setVisibility(VISIBLE);
            if (isBlack)
                mRightTvTitle.setTextColor(ResourcesCompat.getColor(getResources(), R.color.color_323, null));
        }

        try {
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                barHeight = getResources().getDimensionPixelSize(resourceId);
                int space = DensityUtil.dip2px(6);
                if (mRootLayout != null) {
                    mRootLayout.setPadding(0, barHeight + space, 0, space);
                }
                if (mAlphaBgLayout != null) {
                    mAlphaBgLayout.setPadding(0, barHeight + space, 0, space);
                }
            }
        } catch (Exception ignored) {
        }
    }


    public AppTitleLayout setTitleText(String title) {
        if (mTvTitle != null) {
            mTvTitle.setText(title);
        }
        return this;
    }

    public AppTitleLayout setRightTitleText(String title) {
        if (mRightTvTitle != null) {
            mRightTvTitle.setVisibility(VISIBLE);
            mRightTvTitle.setText(title);
        }
        return this;
    }

    public TextView getRightTvTitle() {
        return mRightTvTitle;
    }

    public TextView getTvLeft() {
        return mTvLeft;
    }

    public ImageView getImgLeft() {
        return mLeftImg;
    }

    public AppTitleLayout setOnLeftClickListener(OnClickListener listener) {
        if (mLeftImg != null)
            mLeftImg.setOnClickListener(listener);
        return this;
    }

    public AppTitleLayout setBgLayoutAlpha(float alpha) {
        if (mAlphaBgLayout != null)
            mAlphaBgLayout.setAlpha(alpha);
        return this;
    }

    public AppTitleLayout setOnRightTvClickListener(OnClickListener listener) {
        if (mRightTvTitle != null)
            mRightTvTitle.setOnClickListener(listener);
        return this;
    }

    public int getBarHeight() {
        return barHeight;
    }

    public void setOnRightResource(int rightResourc) {
        if (rightResourc != 0) {
            mRightImg.setImageResource(rightResourc);
            mRightImg.setVisibility(VISIBLE);
        }
    }

    public AppTitleLayout setOnRightImgClickListener(OnClickListener listener) {
        if (mRightImg != null)
            mRightImg.setOnClickListener(listener);
        return this;
    }

    //    从白色改为app原本的颜色
    public void setReset() {
        mAlphaBgLayout.setBackgroundResource(R.drawable.shap_toolbar_top);
        mRootLayout.setBackgroundResource(R.drawable.shap_toolbar_top);
        mLeftImg.setImageResource(R.mipmap.login_back);
        mRightTvTitle.setTextColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
        mTvTitle.setTextColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
    }

    public void setBlack(boolean isBlack) {
        if (mTvTitle == null || mLeftImg == null)
            return;
        if (isBlack) {
            mTvTitle.setTextColor(ResourcesCompat.getColor(getResources(), R.color.color_323, null));
            mLeftImg.setImageResource(R.mipmap.login_back_b);
        } else {
            mTvTitle.setTextColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
            mLeftImg.setImageResource(R.mipmap.login_back);
        }
    }

    public TextView getTvTitle() {
        return mTvTitle;
    }

    public ImageView getRightImg() {
        return mRightImg;
    }
}

package com.edu.squash.ui.me;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.edu.squash.R;
import com.lynn.base.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by ABC
 * on 2024/1/20
 * note
 */
public class ChartFragment extends BaseFragment {


    @BindView(R.id.rv_rv)
    RecyclerView recyclerView;

    public static ChartFragment getInstance(int classType) {
        ChartFragment fragment = new ChartFragment();
        Bundle bundle = new Bundle();

        bundle.putInt("classType", classType);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chart;
    }

    private int mClassType;

    @Override
    protected void onInitView(Bundle bundle) {

        if (getArguments() != null)
            mClassType = getArguments().getInt("classType");




    }


}

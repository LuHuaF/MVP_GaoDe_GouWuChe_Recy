package com.umeng.soexample.monthlianxi.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.umeng.soexample.monthlianxi.R;

public abstract class BaseFragment extends Fragment {
    private LinearLayout mLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_base, null);
        mLayout = view.findViewById(R.id.layout_base);//用于添加子类布局
        View v = View.inflate(getActivity(), getLayoutId(), null);
        mLayout.addView(v);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    protected abstract void initData();

    public abstract int getLayoutId();

}

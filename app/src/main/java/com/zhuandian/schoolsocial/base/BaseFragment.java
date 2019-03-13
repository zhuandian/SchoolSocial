package com.zhuandian.schoolsocial.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * desc :
 * author：xiedong
 * date：2019/2/19
 */
abstract public class BaseFragment extends Fragment {
    public BaseActivity actitity;
    public BaseFragment fragemnt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actitity = ((BaseActivity) getActivity());
        fragemnt = this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    protected abstract int getLayoutId();

    protected abstract void initView();
}

package com.zhuandian.schoolsocial.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * desc :
 * author：xiedong
 * date：2019/2/19
 */
abstract public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setUpView();
    }

    protected abstract int getLayoutId();

    protected abstract void setUpView();
}

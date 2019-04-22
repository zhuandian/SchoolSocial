package com.zhuandian.schoolsocial.business.schoolNews.schoolsocial;


import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.base.BaseActivity;

/**
 * desc :社交页
 * author：xiedong
 * date：2019/4/22
 */
public class SchoolSocialActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_school_social;
    }

    @Override
    protected void setUpView() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment, new HeartShareListFragment())
                .commit();
    }
}

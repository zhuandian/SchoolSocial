package com.zhuandian.schoolsocial;

import android.content.Intent;
import android.view.View;

import com.zhuandian.schoolsocial.base.BaseActivity;
import com.zhuandian.schoolsocial.business.college.CollegeActivity;
import com.zhuandian.schoolsocial.business.schoolNews.SchoolNewsActivity;
import com.zhuandian.schoolsocial.business.studentActivity.StudentActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpView() {

    }


    @OnClick({R.id.tv_student_activity, R.id.tv_school_news, R.id.tv_college})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_student_activity:
                startActivity(new Intent(MainActivity.this, StudentActivity.class));
                break;
            case R.id.tv_school_news:
                startActivity(new Intent(MainActivity.this, SchoolNewsActivity.class));
                break;
            case R.id.tv_college:
                startActivity(new Intent(MainActivity.this, CollegeActivity.class));
                break;
        }
    }
}

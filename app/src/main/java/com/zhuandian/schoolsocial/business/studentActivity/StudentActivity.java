package com.zhuandian.schoolsocial.business.studentActivity;

import android.os.Bundle;
import android.view.View;

import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_student;
    }

    @Override
    protected void setUpView() {

    }



    @OnClick({R.id.tv_new_student_info, R.id.tv_school_map, R.id.tv_student_grade, R.id.tv_search_kebiao, R.id.tv_user_info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_new_student_info:
                break;
            case R.id.tv_school_map:
                break;
            case R.id.tv_student_grade:
                break;
            case R.id.tv_search_kebiao:
                break;
            case R.id.tv_user_info:
                break;
        }
    }
}

package com.zhuandian.schoolsocial.business.studentActivity;

import android.content.Intent;
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
                startActivity(new Intent(StudentActivity.this, SchoolMapActivity.class));
                break;
            case R.id.tv_student_grade:
                startActivity(new Intent(StudentActivity.this, StudentGradeActivity.class));
                break;
            case R.id.tv_search_kebiao:
                startActivity(new Intent(StudentActivity.this, SearchKeBiaoActivity.class));
                break;
            case R.id.tv_user_info:
                startActivity(new Intent(StudentActivity.this, UserInfoActivity.class));
                break;
        }
    }
}

package com.zhuandian.schoolsocial.business.schoolNews;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SchoolNewsActivity extends BaseActivity {


    @BindView(R.id.tv_new_topic)
    TextView tvNewTopic;
    @BindView(R.id.tv_jiaoxue_rili)
    TextView tvJiaoxueRili;
    @BindView(R.id.tv_lost_and_found)
    TextView tvLostAndFound;
    @BindView(R.id.tv_student_suqiu)
    TextView tvStudentSuqiu;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_school_news;
    }

    @Override
    protected void setUpView() {

    }


    @OnClick({R.id.tv_new_topic, R.id.tv_jiaoxue_rili, R.id.tv_lost_and_found, R.id.tv_student_suqiu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_new_topic:
                break;
            case R.id.tv_jiaoxue_rili:
                break;
            case R.id.tv_lost_and_found:
                break;
            case R.id.tv_student_suqiu:
                break;
        }
    }
}

package com.zhuandian.schoolsocial.business.schoolNews;

import android.content.Intent;
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
    @BindView(R.id.tv_search_topic)
    TextView tvSearchTopic;
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


    @OnClick({R.id.tv_new_topic, R.id.tv_search_topic, R.id.tv_lost_and_found, R.id.tv_student_suqiu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_new_topic:
                startActivity(new Intent(SchoolNewsActivity.this, NewTopicActivity.class));
                break;
            case R.id.tv_search_topic:
                startActivity(new Intent(SchoolNewsActivity.this, SearchTopicActivity.class));
                break;
            case R.id.tv_lost_and_found:
                startActivity(new Intent(SchoolNewsActivity.this, LostAndFoundActivity.class));
                break;
            case R.id.tv_student_suqiu:
                startActivity(new Intent(SchoolNewsActivity.this, StudentSuQiuActivity.class));
                break;
        }
    }
}

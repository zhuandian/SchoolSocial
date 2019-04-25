package com.zhuandian.schoolsocial.business.schoolNews;

import android.os.Bundle;
import android.widget.TextView;

import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.base.BaseActivity;
import com.zhuandian.schoolsocial.entity.TopicEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopicDetailActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_time)
    TextView tvTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_topic_detail;
    }

    @Override
    protected void setUpView() {

        TopicEntity entity = (TopicEntity) getIntent().getSerializableExtra("entity");
        tvTitle.setText(entity.getTopicTitle());
        tvContent.setText(entity.getTopicContent());
        tvTime.setText(entity.getCreatedAt());

    }


}

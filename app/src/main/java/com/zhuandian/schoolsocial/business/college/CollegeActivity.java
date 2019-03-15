package com.zhuandian.schoolsocial.business.college;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollegeActivity extends BaseActivity {


    @BindView(R.id.tv_college_news)
    TextView tvCollegeNews;
    @BindView(R.id.tv_library)
    TextView tvLibrary;
    @BindView(R.id.tv_hou_qin)
    TextView tvHouQin;
    @BindView(R.id.tv_jiao_wu_chu)
    TextView tvJiaoWuChu;
    @BindView(R.id.tv_xue_sheng_chu)
    TextView tvXueShengChu;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_college;
    }

    @Override
    protected void setUpView() {

    }


    @OnClick({R.id.tv_college_news, R.id.tv_library, R.id.tv_hou_qin, R.id.tv_jiao_wu_chu, R.id.tv_xue_sheng_chu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_college_news:
                startActivity(new Intent(CollegeActivity.this, CollegeNewsActivity.class));
                break;
            case R.id.tv_library:
                startActivity(new Intent(CollegeActivity.this, LibraryActivity.class));
                break;
            case R.id.tv_hou_qin:
                break;
            case R.id.tv_jiao_wu_chu:
                break;
            case R.id.tv_xue_sheng_chu:
                startActivity(new Intent(CollegeActivity.this, XueShengChuActivity.class));
                break;
        }
    }
}

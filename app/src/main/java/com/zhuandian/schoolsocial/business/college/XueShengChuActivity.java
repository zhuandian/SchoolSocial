package com.zhuandian.schoolsocial.business.college;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.base.BaseActivity;
import com.zhuandian.schoolsocial.business.schoolNews.StudentSuQiuActivity;
import com.zhuandian.schoolsocial.entity.FeedBackEntity;
import com.zhuandian.schoolsocial.entity.UserEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class XueShengChuActivity extends BaseActivity {


    @BindView(R.id.et_feedback)
    EditText etFeedback;
    @BindView(R.id.apb_commit)
    TextView apbCommit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_xue_sheng_chu;
    }

    @Override
    protected void setUpView() {

    }


    @OnClick(R.id.apb_commit)
    public void onClick() {

        FeedBackEntity feedBackEntity = new FeedBackEntity();

        feedBackEntity.setUserName(BmobUser.getCurrentUser(UserEntity.class).getNikeName());
        feedBackEntity.setUserFeedBack(etFeedback.getText().toString());
        feedBackEntity.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Toast.makeText(XueShengChuActivity.this, "提交成功...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

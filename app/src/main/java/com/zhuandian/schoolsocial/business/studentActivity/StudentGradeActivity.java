package com.zhuandian.schoolsocial.business.studentActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.base.BaseActivity;
import com.zhuandian.schoolsocial.entity.ScoreEntity;
import com.zhuandian.schoolsocial.entity.UserEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class StudentGradeActivity extends BaseActivity {


    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.tv_score)
    TextView tvScore;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_student_grade;
    }

    @Override
    protected void setUpView() {
    }


    @OnClick(R.id.tv_submit)
    public void onClick() {
        BmobQuery<ScoreEntity> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("userName", etUsername.getText().toString())
                .findObjects(new FindListener<ScoreEntity>() {
                    @Override
                    public void done(List<ScoreEntity> list, BmobException e) {
                        if (e == null) {
                            tvScore.setText(list.get(0).getScores());
                        } else {
                            tvScore.setText("暂时未查到该学生的成绩信息...");
                        }
                    }
                });
    }
}

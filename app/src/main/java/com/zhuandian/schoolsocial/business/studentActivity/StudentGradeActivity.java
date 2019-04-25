package com.zhuandian.schoolsocial.business.studentActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.base.BaseActivity;
import com.zhuandian.schoolsocial.entity.PersonalScoreEntity;
import com.zhuandian.schoolsocial.entity.UserEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class StudentGradeActivity extends BaseActivity {


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
        BmobQuery<PersonalScoreEntity> bmobQuery = new BmobQuery<>();
        bmobQuery.findObjects(new FindListener<PersonalScoreEntity>() {
            @Override
            public void done(List<PersonalScoreEntity> list, BmobException e) {
                if (e == null) {
                    String scores = list.get(0).getScores();
                    String[] scoresArray = scores.split("-");
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(BmobUser.getCurrentUser(UserEntity.class).getUsername() + "\n");
                    for (String score : scoresArray) {
                        stringBuilder.append(score)
                                .append("\n");
                    }
                    tvScore.setText(stringBuilder.toString());
                } else {
                    tvScore.setText("暂时未查到成绩信息...");
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

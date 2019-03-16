package com.zhuandian.schoolsocial.business.studentActivity;

import android.widget.EditText;
import android.widget.TextView;

import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.base.BaseActivity;
import com.zhuandian.schoolsocial.entity.StudentScoreEntity;


import java.util.List;

import butterknife.BindView;
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
        BmobQuery<StudentScoreEntity> query = new BmobQuery<>();
        query.findObjects(new FindListener<StudentScoreEntity>() {
            @Override
            public void done(List<StudentScoreEntity> list, BmobException e) {
                if (e == null) {
                    StudentScoreEntity studentScoreEntity = list.get(0);
                    tvScore.setText(String.format("成绩分布：\n\n60分以下：%s人 \n60-80人数： %s人 \n80-90分： %s人\n90分以上：%s人",
                            studentScoreEntity.getScore60(), studentScoreEntity.getScore60_80(), studentScoreEntity.getScore80_90(), studentScoreEntity.getScore90()
                    ));
                }
            }
        });
    }


    @OnClick(R.id.tv_submit)
    public void onClick() {

    }
}

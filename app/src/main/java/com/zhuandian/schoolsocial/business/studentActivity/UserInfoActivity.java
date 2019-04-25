package com.zhuandian.schoolsocial.business.studentActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.base.BaseActivity;
import com.zhuandian.schoolsocial.entity.UserEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class UserInfoActivity extends BaseActivity {


    @BindView(R.id.et_nick_name)
    EditText etNickName;
    @BindView(R.id.et_user_desc)
    EditText etUserDesc;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.et_user_password)
    EditText edPassword;
    private UserEntity userEntity;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void setUpView() {
        userEntity = BmobUser.getCurrentUser(UserEntity.class);
        etNickName.setText(userEntity.getNikeName());
        etUserDesc.setText(userEntity.getUserInfo());
    }


    @OnClick(R.id.tv_submit)
    public void onClick() {
        if (TextUtils.isEmpty(etNickName.getText().toString()) || TextUtils.isEmpty(etUserDesc.getText().toString())) {
            userEntity.setNikeName(etNickName.getText().toString());
            userEntity.setUserInfo(etUserDesc.getText().toString());
            userEntity.setPassword(edPassword.getText().toString());
            userEntity.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        Toast.makeText(UserInfoActivity.this, "更新成功...", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

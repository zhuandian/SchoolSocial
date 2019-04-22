package com.zhuandian.schoolsocial.business.schoolNews.schoolsocial;


import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.base.BaseActivity;
import com.zhuandian.schoolsocial.entity.PostEntity;
import com.zhuandian.schoolsocial.entity.UserEntity;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


/**
 * 新建动态
 * author：xiedong
 * date：2019/4/22
 */
public class NewShareActivity extends BaseActivity {

    @BindView(R.id.heart_content)
    EditText edContent;
    private String anonymousType;
    private UserEntity user;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_heart_share;
    }



    @Override
    protected void setUpView() {
        user = BmobUser.getCurrentUser(UserEntity.class);
    }


    @OnClick({R.id.commit_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.commit_content:
                commitHeartContent(false);
                break;
        }
    }


    /**
     * 提交用户发布的动态到后台数据库
     */
    private void commitHeartContent(boolean isAnonymous) {
        String heartContent = edContent.getText().toString();
        if (!"".equals(heartContent)) {

            // 创建动态信息
            final PostEntity post = new PostEntity();
            post.setContent(edContent.getText().toString());
            //添加一对一关联
            post.setAuthor(user);   //设置作者
            post.setUsername(user.getUsername());
            post.save(new SaveListener<String>() {
                @Override
                public void done(String objectId, BmobException e) {

                    if (e == null) {
                        System.out.println("提交成功");
                        Toast.makeText(NewShareActivity.this, "发布成功", Toast.LENGTH_SHORT);
                       finish();  //发布成功后，回到列表页
                    } else {
                        Toast.makeText(NewShareActivity.this, "发布失败", Toast.LENGTH_SHORT);
                    }
                }
            });
        } else {
            Toast.makeText(NewShareActivity.this, "动态内容不能为空哦", Toast.LENGTH_SHORT);

        }
    }


}

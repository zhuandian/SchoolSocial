package com.zhuandian.schoolsocial.business.schoolNews;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.base.BaseActivity;
import com.zhuandian.schoolsocial.entity.LostAndFoundEntity;
import com.zhuandian.schoolsocial.entity.UserEntity;
import com.zhuandian.schoolsocial.utils.Constant;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * desc :发布失物招领页
 * author：xiedong
 */
public class ReleaseLostAndFoundActivity extends BaseActivity {

    @BindView(R.id.ed_title)
    EditText edTitle;
    @BindView(R.id.ed_content)
    EditText edContent;
    @BindView(R.id.rb_lost)
    RadioButton rbLost;
    @BindView(R.id.rb_found)
    RadioButton rbFound;
    @BindView(R.id.tv_release)
    TextView tvRelease;
    private String releaseTitle;
    private String releaseContent;
    private int releaseType = LostAndFoundEntity.LOST; //默认类型为丢失物品
    private UserEntity currentUser;

    @Override
    public int getLayoutId() {
        return R.layout.activity_release_lost_and_found;
    }

    @Override
    protected void setUpView() {

    }


    @OnClick({R.id.rb_lost, R.id.rb_found, R.id.tv_release})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_lost:
                releaseType = LostAndFoundEntity.LOST;
                break;
            case R.id.rb_found:
                releaseType = LostAndFoundEntity.FOUND;
                break;
            case R.id.tv_release:
                commitRelease();
                break;
        }
    }

    private void commitRelease() {
        releaseTitle = edTitle.getText().toString();
        releaseContent = edContent.getText().toString();
        currentUser = BmobUser.getCurrentUser(UserEntity.class);
        if (releaseTitle.equals("") || releaseContent.equals("")) {

            new AlertDialog.Builder(this).setTitle("请完善信息")
                    .setMessage("为了物品尽快回到主人身边，请完善所有发布项...")
                    .show();
            return;
        }
        LostAndFoundEntity entity = new LostAndFoundEntity();
        entity.setTitle(releaseTitle);
        entity.setContent(releaseContent);
        entity.setUserEntity(currentUser);
        entity.setShowName("");
        entity.setType(releaseType);
        entity.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {

                    new AlertDialog.Builder(ReleaseLostAndFoundActivity.this)
                            .setTitle("发布成功")
                            .setPositiveButton("去看看", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    setResult(Constant.REQUEST_NEW_RELEASE_RESULT_OK);
                                    ReleaseLostAndFoundActivity.this.finish();
                                }
                            })
                            .show();

                } else {
                    Toast.makeText(ReleaseLostAndFoundActivity.this, "发布失败，请重试...", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}

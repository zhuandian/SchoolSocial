package com.zhuandian.schoolsocial.business.schoolNews.schoolsocial;

import android.annotation.SuppressLint;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.adapter.UserCommentAdapter;
import com.zhuandian.schoolsocial.base.BaseActivity;
import com.zhuandian.schoolsocial.entity.CommentEntity;
import com.zhuandian.schoolsocial.entity.PostEntity;
import com.zhuandian.schoolsocial.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * 动态详情页
 * author：xiedong
 * date：2019/4/22
 */
public class HeartShareItemActivity extends BaseActivity implements View.OnTouchListener {

    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.comment)
    TextView comment;
    @BindView(R.id.comment_layout)
    LinearLayout commentLayout;
    @BindView(R.id.comment_listview)
    RecyclerView commentRecyclewView;
    @BindView(R.id.comment_content)
    EditText commentContent;
    @BindView(R.id.submit_comment)
    TextView submitComment;
    @BindView(R.id.submit_comment_layout)
    LinearLayout submitCommentLayout;
    @BindView(R.id.fab)
    FloatingActionButton fabButton;
    @BindView(R.id.cl_root)
    CoordinatorLayout clRootView;
    private PostEntity mDatas;
    private UserCommentAdapter userCommentAdapter;
    private List<CommentEntity> commentDatas = new ArrayList<>();


    @Override
    protected void setUpView() {
        mDatas = (PostEntity) getIntent().getSerializableExtra("item");
        getAllUserComment();  //得到所有参与该动态评论的用户信息和内容
        Log.e(mDatas.getContent(), "xiedong");
        //给控件装值
        username.setText(mDatas.getUsername());
        content.setText(mDatas.getContent());
        setCommentCount(mDatas.getObjectId(), comment);  //设置评论个数
        String createtTime[] = mDatas.getCreatedAt().split(" ");
        String currentTime[] = MyUtils.currentTime().split(" ");
        //判断创建时间跟当前时间是否同一天，是，只显示时间，不是，显示创建的日期，不显示时间
        if (createtTime[0].equals(currentTime[0])) {
            String createtTime1[] = createtTime[1].split(":");
            time.setText("今天 " + createtTime1[0] + ":" + createtTime1[1]);
        } else {
            String createtTime1[] = createtTime[0].split("-");  //正则切割月份
            String createtTime2[] = createtTime[1].split(":");  //正则切割时间
            time.setText(createtTime1[1] + "/" + createtTime1[2] + " " + createtTime2[0] + ":" + createtTime2[1]);

        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.heart_share_list_item;
    }


    /**
     * 绑定当前动态下的所有评论信息
     */
    private void getAllUserComment() {
        BmobQuery<CommentEntity> query = new BmobQuery<CommentEntity>();
        //用此方式可以构造一个BmobPointer对象。只需要设置objectId就行
        PostEntity post = new PostEntity();
        post.setObjectId(mDatas.getObjectId());   //得到当前动态的Id号，
        query.order("updatedAt");
        query.addWhereEqualTo("postEntity", new BmobPointer(post));
        //希望同时查询该评论的发布者的信息，以及该帖子的作者的信息，这里用到上面`include`的并列对象查询和内嵌对象的查询
        query.include("myuser,PostEntity.auther");
        query.findObjects(new FindListener<CommentEntity>() {
            @Override
            public void done(List<CommentEntity> objects, BmobException e) {
                if (e == null) {
                    //插入数据，通知列表更新
//                    commentDatas = objects;  直接赋值，因为前后绑定的apapter对应的list地址不是同一个，所以 notifyDataSetChanged无效，
                    commentDatas.clear();
                    commentDatas.addAll(objects); //把数据添加进集合
                    commentRecyclewView.setLayoutManager(new LinearLayoutManager(HeartShareItemActivity.this));
                    userCommentAdapter = new UserCommentAdapter(HeartShareItemActivity.this, commentDatas);
                    commentRecyclewView.setAdapter(userCommentAdapter);
                    userCommentAdapter.notifyDataSetChanged();
                } else {
                    Log.e("查询数据失败", "xiedongdong");
                }
            }
        });


    }


    /**
     * 当前view有能力处理事件
     *
     * @param v
     * @param event
     * @return true  截断事件的传递
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @SuppressLint("RestrictedApi")
    @OnClick({R.id.submit_comment, R.id.fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submit_comment:  //提交评论
                submitUserComment();
                break;
            case R.id.fab:  //新建评论
                commentRecyclewView.setVisibility(View.INVISIBLE);
                submitCommentLayout.setVisibility(View.VISIBLE);
                fabButton.setVisibility(View.INVISIBLE);
                break;


        }
    }


    /**
     * 提交当前用户的评论，并且关联到该动态
     */
    private void submitUserComment() {
        String userComment = commentContent.getText().toString();  //得到用户输入框的评论内容
        if (!"".equals(userComment)) {
            UserEntity user = BmobUser.getCurrentUser(UserEntity.class);  //得到当前用户
            PostEntity post = new PostEntity();   //当前动态内容
            post.setObjectId(mDatas.getObjectId());  //得到当前的动态的id，与评论建立关联
            final CommentEntity commentEntity = new CommentEntity();
            commentEntity.setContent(userComment);
            commentEntity.setMyuser(user);
            commentEntity.setPostEntity(post);
//            commentEntity.setImgUrl(user.getHeadImgUrl());   //头像从user实体中取，保证更换头像之后，动态更新
            commentEntity.save(new SaveListener<String>() {
                @SuppressLint("RestrictedApi")
                @Override
                public void done(String objectId, BmobException e) {
                    if (e == null) {
                        Toast.makeText(HeartShareItemActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                        commentContent.setText(""); //清空输入框，防止用户二次评论时影响用户体验
                        commentRecyclewView.setVisibility(View.VISIBLE);
                        submitCommentLayout.setVisibility(View.INVISIBLE);
                        fabButton.setVisibility(View.VISIBLE);
                        getAllUserComment();  //重新加载一遍数据

                    } else {
                        Log.e("评论失败", "xiedongdong");
                    }
                }

            });
        } else {
            Toast.makeText(HeartShareItemActivity.this, "评论内容不允许为空", Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * 得到动态相关的评论个数
     *
     * @param objectId
     * @return
     */
    private void setCommentCount(String objectId, final TextView countView) {
        BmobQuery<CommentEntity> query = new BmobQuery<CommentEntity>();
        //用此方式可以构造一个BmobPointer对象。只需要设置objectId就行
        PostEntity post = new PostEntity();
        post.setObjectId(objectId);   //得到当前动态的Id号，
        query.addWhereEqualTo("postEntity", new BmobPointer(post));
        //希望同时查询该评论的发布者的信息，以及该帖子的作者的信息，这里用到上面`include`的并列对象查询和内嵌对象的查询
        query.include("myuser,postEntity.auther");
        query.findObjects(new FindListener<CommentEntity>() {

            @Override
            public void done(List<CommentEntity> objects, BmobException e) {
                if (e == null) {
                    countView.setText(objects.size() + "");
                } else {
                    Log.e("查询数据失败", "xiedongdong");
                }
            }
        });

    }


}

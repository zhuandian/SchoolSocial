package com.zhuandian.schoolsocial.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.base.BaseAdapter;
import com.zhuandian.schoolsocial.base.BaseViewHolder;
import com.zhuandian.schoolsocial.business.schoolNews.schoolsocial.MyUtils;
import com.zhuandian.schoolsocial.entity.CommentEntity;
import com.zhuandian.schoolsocial.entity.PostEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * desc :
 * author：xiedong
 * date：2019/4/22
 */
public class PostListAdapter extends BaseAdapter<PostEntity, BaseViewHolder> {
    @BindView(R.id.ll_click_span)
    LinearLayout llClickSpan;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.comment)
    TextView comment;
    private ItemClickListener clickListener;

    public PostListAdapter(Context context, List<PostEntity> mDatas) {
        super(context, mDatas);
    }

    @Override
    protected void converData(BaseViewHolder holder, final PostEntity heartShareEntity, final int position) {

        ButterKnife.bind(this, holder.itemView);
        username.setText(heartShareEntity.getUsername());
        setCommentCount(heartShareEntity.getObjectId(), comment);   //评论个数
        content.setText(heartShareEntity.getContent());
        System.out.println("创建Time----" + heartShareEntity.getCreatedAt() + "系统时间--" + MyUtils.currentTime());
//        创建Time----2016-12-30 10:46:44系统时间--2016-12-30 10:54:03
        String createtTime[] = heartShareEntity.getCreatedAt().split(" ");
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
        llClickSpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onItemClick(heartShareEntity);
                }
            }
        });

        llClickSpan.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if (clickListener != null) {
                    clickListener.onItemLongClick(position);
                }
                return true;
            }
        });
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
        query.include("myuser,postentity.auther");
        query.findObjects(new FindListener<CommentEntity>() {

            @Override
            public void done(List<CommentEntity> objects, BmobException e) {
                if (e == null) {
                    countView.setText(objects.size() + "");
                } else {
                    System.out.println("查询数据失败");
                }
            }
        });

    }


    @Override
    public int getItemLayoutId() {
        return R.layout.heart_share_item;
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }



    public interface ItemClickListener {
        void onItemClick(PostEntity heartShareEntity);

        void onItemLongClick(int pos);
    }
}

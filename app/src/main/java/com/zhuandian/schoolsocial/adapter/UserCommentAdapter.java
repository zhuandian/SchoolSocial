package com.zhuandian.schoolsocial.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.base.BaseAdapter;
import com.zhuandian.schoolsocial.base.BaseViewHolder;
import com.zhuandian.schoolsocial.business.schoolNews.schoolsocial.MyUtils;
import com.zhuandian.schoolsocial.entity.CommentEntity;

import org.w3c.dom.Comment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc :
 * author：xiedong
 * date：2019/4/22
 */
public class UserCommentAdapter extends BaseAdapter<CommentEntity, BaseViewHolder> {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.content)
    TextView content;


    public UserCommentAdapter(Context context, List<CommentEntity> mDatas) {
        super(context, mDatas);
    }

    @Override
    protected void converData(BaseViewHolder holder, CommentEntity comment, int position) {
        ButterKnife.bind(this, holder.itemView);
        String createtTime[] = comment.getCreatedAt().split(" ");
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
        name.setText(comment.getMyuser().getUsername());   //设置评论者信息
        content.setText(comment.getContent());

    }

    @Override
    public int getItemLayoutId() {
        return R.layout.comment_listview_item;
    }


}

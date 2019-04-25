package com.zhuandian.schoolsocial.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.base.BaseAdapter;
import com.zhuandian.schoolsocial.base.BaseViewHolder;
import com.zhuandian.schoolsocial.entity.TopicEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc :
 * author：xiedong
 * date：2019/4/4
 */
public class SeachTopicAdapter extends BaseAdapter<TopicEntity, BaseViewHolder> {

    private List<TopicEntity> datas;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_time)
    TextView tvTime;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public SeachTopicAdapter(Context context, List<TopicEntity> mDatas) {
        super(context, mDatas);
    }

    @Override
    protected void converData(BaseViewHolder myViewHolder, final TopicEntity topicEntity, int position) {
        ButterKnife.bind(this, myViewHolder.itemView);
        tvTitle.setText(topicEntity.getTopicTitle());
        tvContent.setText(topicEntity.getTopicContent());
        tvTime.setText(topicEntity.getCreatedAt());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(topicEntity);
                }
            }
        });
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_search_topic;
    }

    public interface OnItemClickListener {
        void onClick(TopicEntity topicEntity);
    }
}

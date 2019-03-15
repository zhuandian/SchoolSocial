package com.zhuandian.schoolsocial.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.base.BaseAdapter;
import com.zhuandian.schoolsocial.base.BaseViewHolder;
import com.zhuandian.schoolsocial.entity.LostAndFoundEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LostAndFoundAdapter extends BaseAdapter<LostAndFoundEntity, BaseViewHolder> {
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_time)
    TextView tvTime;

    public LostAndFoundAdapter(Context mContext, List mData) {
        super(mContext, mData);
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_lost_and_found;
    }

    @Override
    protected void converData(BaseViewHolder holder, LostAndFoundEntity lostAndFoundEntity, int position) {
        ButterKnife.bind(this, holder.itemView);
        tvType.setText(lostAndFoundEntity.getType() == LostAndFoundEntity.LOST ? "丢" : "捡");
        tvType.setBackgroundResource(lostAndFoundEntity.getType() == LostAndFoundEntity.LOST ? R.drawable.shape_lost_and_found_type_lost_bg : R.drawable.shape_lost_and_found_type_found_bg);
        tvTitle.setText(lostAndFoundEntity.getTitle());
        tvContent.setText(lostAndFoundEntity.getContent());
        formatTime(lostAndFoundEntity.getCreatedAt());

    }

    private void formatTime(String createdAt) {
        String createtTime[] = createdAt.split(" ");
        String currentTime[] = getCurrentTime().split(" ");
        //判断创建时间跟当前时间是否同一天，是，只显示时间，不是，显示创建的日期，不显示时间
        if (createtTime[0].equals(currentTime[0])) {
            String createtTime1[] = createtTime[1].split(":");
            tvTime.setText("今天 " + createtTime1[0] + ":" + createtTime1[1]);
        } else {
            String createtTime1[] = createtTime[0].split("-");  //正则切割月份
            String createtTime2[] = createtTime[1].split(":");  //正则切割时间
            tvTime.setText(createtTime1[1] + "/" + createtTime1[2] + " " + createtTime2[0] + ":" + createtTime2[1]);
        }
    }

    private String getCurrentTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        return time;

    }


}

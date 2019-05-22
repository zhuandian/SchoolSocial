package com.zhuandian.schoolsocial.business.chat.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.business.chat.base.ImageLoaderFactory;
import com.zhuandian.schoolsocial.business.chat.bean.User;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/**
 * 接收到的文本类型
 */
public class ReceiveTextHolder extends BaseViewHolder {

    @BindView(R.id.iv_avatar)
    protected ImageView iv_avatar;

    @BindView(R.id.tv_time)
    protected TextView tv_time;

    @BindView(R.id.tv_message)
    protected TextView tv_message;

    public ReceiveTextHolder(Context context, ViewGroup root, OnRecyclerViewListener onRecyclerViewListener) {
        super(context, root, R.layout.item_chat_received_message, onRecyclerViewListener);
    }

    @OnClick({R.id.iv_avatar})
    public void onAvatarClick(View view) {

    }

    @Override
    public void bindData(Object o) {
        final BmobIMMessage message = (BmobIMMessage) o;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        String time = dateFormat.format(message.getCreateTime());
        tv_time.setText(time);
        final BmobIMUserInfo info = message.getBmobIMUserInfo();
        getUserHeaderImgById(info.getUserId());
//        ImageLoaderFactory.getLoader().loadAvator(iv_avatar, info != null ? info.getAvatar() : null, R.mipmap.head);
        String content = message.getContent();
        tv_message.setText(content);
        iv_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info == null) {
                    toast("由message获得的用户信息为空");
                    return;
                }
                toast("点击" + info.getName() + "的头像");
            }
        });
        tv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("点击" + message.getContent());
                if (onRecyclerViewListener != null) {
                    onRecyclerViewListener.onItemClick(getAdapterPosition());
                }
            }
        });

        tv_message.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onRecyclerViewListener != null) {
                    onRecyclerViewListener.onItemLongClick(getAdapterPosition());
                }
                return true;
            }
        });
    }

    /**
     * 根据userId拿到存放在七牛云上的bmobUser用户头像
     *
     * @param userId
     */
    private void getUserHeaderImgById(String userId) {
        BmobQuery<User> query = new BmobQuery<User>();
        query.getObject(userId, new QueryListener<User>() {

            @Override
            public void done(User userEntity, BmobException e) {
                if (e == null) {
                    ImageLoaderFactory.getLoader().loadAvator(iv_avatar, userEntity != null ? userEntity.getAvatar() : null, R.mipmap.head);
                }
            }

        });
    }

    public void showTime(boolean isShow) {
        tv_time.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
}
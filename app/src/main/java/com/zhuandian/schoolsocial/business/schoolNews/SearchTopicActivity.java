package com.zhuandian.schoolsocial.business.schoolNews;


import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.adapter.SeachTopicAdapter;
import com.zhuandian.schoolsocial.base.BaseActivity;
import com.zhuandian.schoolsocial.entity.TopicEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SearchTopicActivity extends BaseActivity {


    @BindView(R.id.ed_content)
    EditText edContent;
    @BindView(R.id.tv_seach)
    TextView tvSeach;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private boolean isLimit = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_topic;
    }

    @Override
    protected void setUpView() {
        initData();
    }

    private void initData() {
        BmobQuery<TopicEntity> bmobQuery = new BmobQuery<>();
        if (isLimit)
            bmobQuery.addWhereEqualTo("type", edContent.getText().toString());
        bmobQuery.findObjects(new FindListener<TopicEntity>() {
            @Override
            public void done(List<TopicEntity> list, BmobException e) {
                if (e == null) {
                    rvList.setAdapter(new SeachTopicAdapter(SearchTopicActivity.this, list));
                    rvList.setLayoutManager(new LinearLayoutManager(SearchTopicActivity.this));
                    if (list.size() == 0) {
                        new AlertDialog.Builder(SearchTopicActivity.this)
                                .setTitle("抱歉")
                                .setMessage("未检索到您要搜索的话题...")
                                .create()
                                .show();
                    }
                } else {
                    new AlertDialog.Builder(SearchTopicActivity.this)
                            .setTitle("抱歉")
                            .setMessage("未检索到您要搜索的话题...")
                            .create()
                            .show();
                }
            }
        });
    }


    @OnClick(R.id.tv_seach)
    public void onClick() {
        if (TextUtils.isEmpty(edContent.getText().toString())) {
            Toast.makeText(this, "请输入关键字...", Toast.LENGTH_SHORT).show();
        } else {
            isLimit = true;
            initData();
        }
    }
}

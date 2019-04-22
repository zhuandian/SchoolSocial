package com.zhuandian.schoolsocial.business.schoolNews.schoolsocial;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.adapter.PostListAdapter;
import com.zhuandian.schoolsocial.base.BaseFragment;
import com.zhuandian.schoolsocial.entity.PostEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 用于装载所有用户分享的动态
 * Created by 谢栋
 */
public class HeartShareListFragment extends BaseFragment {
    @BindView(R.id.heart_list)
    RecyclerView recyclerView;
    @BindView(R.id.heart_list_swipeRefresh)
    SwipeRefreshLayout heartListSwipeRefresh;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private PostListAdapter myAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_heart_share_list;
    }

    @Override
    protected void initView() {
        handleViewEvent();
        loadDatas();
    }


    /**
     * 处理各个view上的各种事件
     */
    private void handleViewEvent() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(actitity);
        recyclerView.setLayoutManager(linearLayoutManager);

        //设置下拉刷新的动画的颜色
        heartListSwipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        //设置下拉刷新监听
        heartListSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (heartListSwipeRefresh.isRefreshing()) {
                    heartListSwipeRefresh.setRefreshing(false);
                    loadDatas();
                    Toast.makeText(actitity, "动态数据已经最新...", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    /**
     * 装载数据
     */
    private void loadDatas() {
        BmobQuery<PostEntity> query = new BmobQuery<PostEntity>();
        query.order("-updatedAt");
        query.setLimit(10);
        query.include("author");// 希望在查询帖子信息的同时也把发布人的信息查询出来
        query.findObjects(new FindListener<PostEntity>() {
            @Override
            public void done(List<PostEntity> object, BmobException e) {
                if (e == null) {
                    PostListAdapter adapter = new PostListAdapter(actitity, object);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(actitity));
                    adapter.setClickListener(new PostListAdapter.ItemClickListener() {
                        @Override
                        public void onItemClick(PostEntity heartShareEntity) {
                            //跳转到详情页
                            Intent intent = new Intent(actitity, HeartShareItemActivity.class);
                            intent.putExtra("item", heartShareEntity);
                            startActivity(intent);
                        }

                        @Override
                        public void onItemLongClick(int pos) {

                        }
                    });
                } else {

                }
            }

        });

    }


    @OnClick({R.id.fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab:
                //跳转到新建动态页
                Intent intent = new Intent(actitity, NewShareActivity.class);
                startActivity(intent);
                break;
        }

    }
}

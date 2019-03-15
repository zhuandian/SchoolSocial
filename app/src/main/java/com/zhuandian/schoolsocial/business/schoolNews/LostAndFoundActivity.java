package com.zhuandian.schoolsocial.business.schoolNews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhuandian.barrageview.BarrageView;
import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.adapter.LostAndFoundAdapter;
import com.zhuandian.schoolsocial.base.BaseActivity;
import com.zhuandian.schoolsocial.entity.LostAndFoundEntity;
import com.zhuandian.schoolsocial.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LostAndFoundActivity extends BaseActivity {

    @BindView(R.id.barrage_view)
    BarrageView barrageView;
    @BindView(R.id.brv_list)
    RecyclerView recyclerView;
    private List<LostAndFoundEntity> mDatas = new ArrayList<>();
    private List<String> barrageList = new ArrayList<>();
    private LostAndFoundAdapter lostAndFoundAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_lost_and_found;
    }

    @Override
    protected void setUpView() {
        lostAndFoundAdapter = new LostAndFoundAdapter(this, mDatas);
        recyclerView.setAdapter(lostAndFoundAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
    }

    private void initData() {
        BmobQuery<LostAndFoundEntity> query = new BmobQuery<>();
        query.order("-updatedAt");
        query.include("userEntity");// 查出发布人信息
//        query.setSkip(currentLoadDataSize);
        query.findObjects(new FindListener<LostAndFoundEntity>() {
            @Override
            public void done(List<LostAndFoundEntity> list, BmobException e) {
                if (e == null) {
                    for (int i = 0; i < list.size(); i++) {
                        mDatas.add(list.get(i));
                        if (i <= 10)  //取出前10条放进弹幕播放
                            barrageList.add(String.format("%s：%s"
//                                    , list.get(i).getUserEntity().getNikeName()
                                    , list.get(i).getType() == LostAndFoundEntity.LOST ? "丢失" : "捡到"
                                    , list.get(i).getTitle()));
                    }
                    lostAndFoundAdapter.notifyDataSetChanged();
                    if (list.size() != 0) {
                        barrageView.setBarrageItemList(barrageList);
                        barrageView.startBarrageView();
                    }
                }
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.REQUEST_NEW_RELEASE && resultCode == Constant.REQUEST_NEW_RELEASE_RESULT_OK) {
            mDatas.clear();
            initData(); //发布完成后，刷新数据
        }
    }


    @OnClick(R.id.tv_release)
    public void onClick() {
        startActivity(new Intent(LostAndFoundActivity.this, ReleaseLostAndFoundActivity.class));
    }
}

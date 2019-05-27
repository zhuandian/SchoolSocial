package com.zhuandian.schoolsocial;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhuandian.schoolsocial.base.BaseActivity;
import com.zhuandian.schoolsocial.business.chat.bean.User;
import com.zhuandian.schoolsocial.business.chat.event.RefreshEvent;
import com.zhuandian.schoolsocial.business.chat.ui.ChatActivity;
import com.zhuandian.schoolsocial.business.chat.util.IMMLeaks;
import com.zhuandian.schoolsocial.business.schoolNews.SchoolNewsActivity;
import com.zhuandian.schoolsocial.business.studentActivity.StudentActivity;
import com.zhuandian.schoolsocial.utils.Constant;
import com.zhuandian.schoolsocial.utils.GlideImageLoader;
import com.zhuandian.schoolsocial.utils.MyLocationListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.ConnectionStatus;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConnectStatusChangeListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;

public class MainActivity extends BaseActivity {
    public LocationClient mLocationClient = null;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_content)
    TextView tvContent;
    private MyLocationListener myListener = new MyLocationListener();
    private static final int BAIDU_READ_PHONE_STATE = 100;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void setUpView() {
        initLocation();
        List<Integer> images = new ArrayList<>();
        User userEntity = BmobUser.getCurrentUser(User.class);
        if (Constant.ROLE_ID == 1) {  //模拟用户type类型1登陆成功之后首页展示的内容
            images.add(R.drawable.ic_release_lost_and_found_head_bg);
            images.add(R.drawable.ic_release_lost_and_found_head_bg);
            images.add(R.drawable.ic_release_lost_and_found_head_bg);
            tvContent.setText(String.format("%s：你好，你上次关注的\n软件讲座论坛\n软件学院招聘会\n等内容更新了，快去看看吧...", userEntity.getNikeName()));
        } else if (Constant.ROLE_ID == 2) {//模拟用户type类型2登陆成功之后首页展示的内容
            images.add(R.drawable.ic_school_map);
            images.add(R.drawable.ic_school_map);
            images.add(R.drawable.ic_school_map);
            tvContent.setText(String.format("%s：你好，你上次关注的\n四六级成绩查询发布了\n计算机等级考试\n等内容更新了，快去看看吧...", userEntity.getNikeName()));
        }

        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置轮播时间
        banner.setDelayTime(2000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        initChatSystem();
    }

    private void initChatSystem() {
        final User user = BmobUser.getCurrentUser(User.class);
        //TODO 连接：3.1、登录成功、注册成功或处于登录状态重新打开应用后执行连接IM服务器的操作
        //判断用户是否登录，并且连接状态不是已连接，则进行连接操作
        if (!TextUtils.isEmpty(user.getObjectId()) &&
                BmobIM.getInstance().getCurrentStatus().getCode() != ConnectionStatus.CONNECTED.getCode()) {
            BmobIM.connect(user.getObjectId(), new ConnectListener() {
                @Override
                public void done(String uid, BmobException e) {
                    if (e == null) {
                        //服务器连接成功就发送一个更新事件，同步更新会话及主页的小红点
                        //TODO 会话：2.7、更新用户资料，用于在会话页面、聊天页面以及个人信息页面显示
                        BmobIM.getInstance().
                                updateUserInfo(new BmobIMUserInfo(user.getObjectId(),
                                        user.getUsername(), user.getAvatar()));
                        EventBus.getDefault().post(new RefreshEvent());
                    } else {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //TODO 连接：3.3、监听连接状态，可通过BmobIM.getInstance().getCurrentStatus()来获取当前的长连接状态
            BmobIM.getInstance().setOnConnectStatusChangeListener(new ConnectStatusChangeListener() {
                @Override
                public void onChange(ConnectionStatus status) {
                    Toast.makeText(MainActivity.this, status.getMsg(), Toast.LENGTH_SHORT).show();
                    Logger.i(BmobIM.getInstance().getCurrentStatus().getMsg());
                }
            });
        }
        //解决leancanary提示InputMethodManager内存泄露的问题
        IMMLeaks.fixFocusedViewLeak(getApplication());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initLocation() {
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                ) {
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, BAIDU_READ_PHONE_STATE);
        } else {
            initBaiduMap();
        }
        myListener.setLocationSuccess(new MyLocationListener.onLocationSuccess() {
            @Override
            public void onSuccess(String location) {
//                tvInfo.setText(String.format("根据我们的系统定位，您现在位于\n%s\n，系统根据您的地理位置，为您做出了相应的内容推荐，请您尽情享用...", location));
                Toast.makeText(MainActivity.this, String.format("根据我们的系统定位，您现在位于\n%s\n，系统根据您的地理位置，为您做出了相应的内容推荐，请您尽情享用...", location), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initBaiduMap() {
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);

        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }


    @OnClick({R.id.tv_student_activity, R.id.tv_school_news, R.id.tv_chat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_student_activity:
                startActivity(new Intent(MainActivity.this, StudentActivity.class));
                break;
            case R.id.tv_school_news:
                startActivity(new Intent(MainActivity.this, SchoolNewsActivity.class));
                break;
            case R.id.tv_chat:
                startActivity(new Intent(MainActivity.this, com.zhuandian.schoolsocial.business.chat.ui.MainActivity.class));
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            //requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case BAIDU_READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //获取到权限，做相应处理
                    //调用定位SDK应确保相关权限均被授权，否则会引起定位失败
                    initBaiduMap();
                } else {
                    //没有获取到权限，做特殊处理
                    Toast.makeText(this, "没有获取到定位权限...", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

}
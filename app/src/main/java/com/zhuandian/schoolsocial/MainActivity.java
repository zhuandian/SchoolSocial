package com.zhuandian.schoolsocial;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.zhuandian.schoolsocial.base.BaseActivity;
import com.zhuandian.schoolsocial.business.college.CollegeActivity;
import com.zhuandian.schoolsocial.business.schoolNews.SchoolNewsActivity;
import com.zhuandian.schoolsocial.business.studentActivity.StudentActivity;
import com.zhuandian.schoolsocial.utils.MyLocationListener;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private static final int BAIDU_READ_PHONE_STATE = 100;
    @BindView(R.id.tv_info)
    TextView tvInfo;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void setUpView() {
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
                tvInfo.setText(String.format("根据我们的系统定位，您现在位于\n%s\n，系统根据您的地理位置，为您做出了相应的内容推荐，请您尽情享用...", location));
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


    @OnClick({R.id.tv_student_activity, R.id.tv_school_news, R.id.tv_college})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_student_activity:
                startActivity(new Intent(MainActivity.this, StudentActivity.class));
                break;
            case R.id.tv_school_news:
                startActivity(new Intent(MainActivity.this, SchoolNewsActivity.class));
                break;
            case R.id.tv_college:
                startActivity(new Intent(MainActivity.this, CollegeActivity.class));
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
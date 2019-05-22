package com.zhuandian.schoolsocial;

import android.app.Application;

import com.orhanobut.logger.Logger;
import com.zhuandian.schoolsocial.business.chat.DemoMessageHandler;
import com.zhuandian.schoolsocial.business.chat.base.UniversalImageLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import cn.bmob.newim.BmobIM;


//TODO 集成：1.7、自定义Application，并在AndroidManifest.xml中配置
public class MyApplication extends Application {

    private static MyApplication INSTANCE;

    public static MyApplication INSTANCE() {
        return INSTANCE;
    }

    private void setInstance(MyApplication app) {
        setBmobIMApplication(app);
    }

    private static void setBmobIMApplication(MyApplication a) {
        MyApplication.INSTANCE = a;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
//        Bmob.initialize(this, "3b3a8173f7d02cc1222afb432eee0868");
        //TODO 集成：1.8、初始化IM SDK，并注册消息接收器，只有主进程运行的时候才需要初始化
        if (getApplicationInfo().packageName.equals(getMyProcessName())) {
            BmobIM.init(this);
            BmobIM.registerDefaultMessageHandler(new DemoMessageHandler(this));
        }
        Logger.init("BmobNewIMDemo");
        UniversalImageLoader.initImageLoader(this);

    }

    /**
     * 获取当前运行的进程名
     *
     * @return
     */
    public static String getMyProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

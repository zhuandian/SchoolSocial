package com.zhuandian.schoolsocial.business.schoolNews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhuandian.schoolsocial.R;
import com.zhuandian.schoolsocial.base.WebPageActivity;

public class NewTopicActivity extends WebPageActivity {

    @Override
    public void loadUrl() {
        wvPage.loadUrl("https://www.nuist.edu.cn");
    }
}

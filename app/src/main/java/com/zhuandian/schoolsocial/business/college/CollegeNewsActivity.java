package com.zhuandian.schoolsocial.business.college;


import com.zhuandian.schoolsocial.base.WebPageActivity;

/**
 * 学院动态页
 */
public class CollegeNewsActivity extends WebPageActivity {

    @Override
    public void loadUrl() {
        wvPage.loadUrl("https://www.nuist.edu.cn/875/list.htm");
    }
}

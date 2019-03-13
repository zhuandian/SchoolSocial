package com.zhuandian.schoolsocial.business.college;


import com.zhuandian.schoolsocial.base.WebPageActivity;

/**
 * 图书馆页
 */
public class LibraryActivity extends WebPageActivity {

    @Override
    public void loadUrl() {
        wvPage.loadUrl("http://www.lib.sdu.edu.cn/index.html");
    }
}

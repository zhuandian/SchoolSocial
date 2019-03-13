package com.zhuandian.schoolsocial.base;


import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zhuandian.schoolsocial.R;

import butterknife.BindView;

abstract public class WebPageActivity extends BaseActivity {


    @BindView(R.id.wv_page)
    public WebView wvPage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_page;
    }

    @Override
    protected void setUpView() {
        wvPage.getSettings().setJavaScriptEnabled(true);
        wvPage.getSettings().setDomStorageEnabled(true);
        wvPage.setWebViewClient(new WebViewClient());
        wvPage.setWebChromeClient(new WebChromeClient());
        loadUrl();
    }

    abstract public void loadUrl();

    @Override
    public void onBackPressed() {
        if (wvPage.canGoBack())
            wvPage.goBack();
        else
            super.onBackPressed();
    }
}

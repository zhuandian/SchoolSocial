package com.zhuandian.schoolsocial.base;


import android.net.http.SslError;
import android.os.Build;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
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
        wvPage.setWebViewClient(new MyWebViewClient());
        wvPage.setWebChromeClient(new WebChromeClient());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            wvPage.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
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

    class MyWebViewClient extends WebViewClient {
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//            super.onReceivedSslError(view, handler, error);
            handler.proceed();
        }
    }
}

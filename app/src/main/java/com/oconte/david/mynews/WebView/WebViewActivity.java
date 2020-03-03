package com.oconte.david.mynews.WebView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.oconte.david.mynews.Models.Article;
import com.oconte.david.mynews.R;

import java.io.Serializable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.web_view_all_new) WebView webView;



    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);


        this.configureToolbar();

        this.configureWebView();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected void configureToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My News");


        //afficher le bouton retour
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void configureWebView() {
        WebView mWebView = (WebView) findViewById(R.id.web_view_all_new);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                findViewById(R.id.web_view_all_new).setVisibility(View.VISIBLE);
            }
        });
        mWebView.loadUrl(getUrlTest());
    }

    public String getUrlTest() {
        return getIntent().getStringExtra("url");
    }
}

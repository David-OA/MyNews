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

    //private Article article;

    private WebView mWebView;

    private Serializable url;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);


        this.configureToolbar();

        this.configureWebView();

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            // Fonction qui permet l'affichage de la page lorsque tout est chargé (événement onPageFinished)

            public void onPageFinished(WebView view, String url) {
                findViewById(R.id.web_view_all_new).setVisibility(View.VISIBLE);
            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /*@Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }*/

    protected void configureToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My News");

        //afficher le bouton retour
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void configureWebView() {
        setContentView(R.layout.activity_web_view);
        mWebView = (WebView) findViewById(R.id.web_view_all_new);
        mWebView.setWebViewClient(new WebViewClient() {
            @SuppressLint("NewApi")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(geturlTest());
                return true;
            }
        });

        //mWebView.loadUrl("https://nytimes.com");
        //mWebView.loadUrl(getGeneralUrl(article));
        mWebView.loadUrl(geturlTest());

    }

    public String geturlTest() {
        Intent intent = getIntent();
        Article article = (Article) intent.getSerializableExtra("url");
        return null;
    }
}

package com.volskyioleh.nytnewsapp.presenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.volskyioleh.nytnewsapp.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsWebViewActivity extends AppCompatActivity {
     public  static final String URL_EXTRA = "URL";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webVIew)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_web_view);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        webView.loadUrl(getIntent().getStringExtra(URL_EXTRA));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

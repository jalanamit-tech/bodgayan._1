package com.winbee.bodhayanacademy.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.winbee.bodhayanacademy.LoginActivity;
import com.winbee.bodhayanacademy.Model.UrlName;
import com.winbee.bodhayanacademy.R;
import com.winbee.bodhayanacademy.SharedPrefManager;
import com.winbee.bodhayanacademy.Utils.ProgressBarUtil;
import java.util.Objects;

public class WebActivity extends AppCompatActivity {
    private WebView webView;
    private UrlName urlName;
    private ProgressBarUtil progressBarUtil;
    RelativeLayout home,histroy,logout;
    Button btm_asked_question;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView =findViewById(R.id.myWebView);
        home=findViewById(R.id.layout_home);
        histroy=findViewById(R.id.layout_history);
        btm_asked_question=findViewById(R.id.btm_asked_question);
        logout=findViewById(R.id.layout_logout);
        histroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://bodhayancoaching.com/"));
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WebActivity.this, GecHomeActivity.class);
                startActivity(intent);
            }
        });


        progressBarUtil   =  new ProgressBarUtil(this);
        progressBarUtil.showProgress();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            urlName = (UrlName) bundle.getSerializable("URL");
            if(urlName!=null){
                System.out.println("Suree:"+urlName.getType().equalsIgnoreCase("URL"));
                }
        }
        displayWebView();
        btm_asked_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(WebActivity.this,AskedQuestionActivity.class);
                intent.putExtra("documentID",urlName.getDocumentId());
                startActivity(intent);
            }
        });
    }

    private void displayWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(urlName.getURL());
                return true;

            }
        });
        webView.loadUrl(urlName.getURL());
    }

    private void logout() {
        SharedPrefManager.getInstance(this).logout();
        startActivity(new Intent(this, LoginActivity.class));
        Objects.requireNonNull(this).finish();
    }

}

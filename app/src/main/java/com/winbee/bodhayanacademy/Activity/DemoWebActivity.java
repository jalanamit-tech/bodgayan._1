package com.winbee.bodhayanacademy.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.winbee.bodhayanacademy.LoginActivity;
import com.winbee.bodhayanacademy.Model.DemoClas;
import com.winbee.bodhayanacademy.R;
import com.winbee.bodhayanacademy.SharedPrefManager;
import com.winbee.bodhayanacademy.Utils.ProgressBarUtil;
import java.util.Objects;

public class DemoWebActivity extends AppCompatActivity {
    private WebView webView;
    private DemoClas demoClas;
    private ProgressBarUtil progressBarUtil;
    RelativeLayout home,histroy,logout;
    Button btm_asked_question;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_web);
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
                Intent intent = new Intent(DemoWebActivity.this, GecHomeActivity.class);
                startActivity(intent);
            }
        });


        progressBarUtil   =  new ProgressBarUtil(this);
        progressBarUtil.showProgress();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            demoClas = (DemoClas) bundle.getSerializable("URL");
            if(demoClas!=null){
                System.out.println("Suree:"+demoClas.getType().equalsIgnoreCase("URL"));
            }
        }
        displayWebView();
        btm_asked_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DemoWebActivity.this,AskedQuestionActivity.class);
                intent.putExtra("documentID",demoClas.getDocumentId());
                startActivity(intent);
            }//ye butto hai
        });
    }

    private void displayWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(demoClas.getURL());
                return true;

            }
        });
        webView.loadUrl(demoClas.getURL());
    }

    private void logout() {
        SharedPrefManager.getInstance(this).logout();
        startActivity(new Intent(this, LoginActivity.class));
        Objects.requireNonNull(this).finish();
    }

}

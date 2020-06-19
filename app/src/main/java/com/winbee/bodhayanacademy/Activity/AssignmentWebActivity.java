package com.winbee.bodhayanacademy.Activity;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.winbee.bodhayanacademy.LoginActivity;
import com.winbee.bodhayanacademy.Model.AssignmentDatum;
import com.winbee.bodhayanacademy.R;
import com.winbee.bodhayanacademy.SharedPrefManager;
import com.winbee.bodhayanacademy.Utils.AssignmentData;
import com.winbee.bodhayanacademy.Utils.ProgressBarUtil;


import java.util.Objects;

public class AssignmentWebActivity extends AppCompatActivity {

    private WebView webView;
    private AssignmentDatum assignmentDatum;
    private ProgressBarUtil progressBarUtil;
    RelativeLayout home,histroy,logout;
    Button btm_asked_question;
    String googleDocs = "https://docs.google.com/viewer?url=";
    String pdf_url = AssignmentData.ContentName;
    ImageView image_view;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_web);
        webView =findViewById(R.id.myWebView);
        home=findViewById(R.id.layout_home);
        histroy=findViewById(R.id.layout_history);
        btm_asked_question=findViewById(R.id.btm_asked_question);
        image_view=findViewById(R.id.image_view);
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
                Intent intent = new Intent(AssignmentWebActivity.this, GecHomeActivity.class);
                startActivity(intent);
            }
        });


        progressBarUtil   =  new ProgressBarUtil(this);
        progressBarUtil.showProgress();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            assignmentDatum = (AssignmentDatum) bundle.getSerializable("URL");
        }
        if (AssignmentData.ContentName.endsWith("pdf")) {
            displayWebView();
            progressBarUtil.hideProgress();
        }else{
            image_view.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);
            Picasso.get().load(pdf_url).into(image_view);
            progressBarUtil.hideProgress();
        }
        btm_asked_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AssignmentWebActivity.this,SubmitAssignmentActivity.class);
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
                view.loadUrl(googleDocs + pdf_url);
                return true;

            }
        });
        webView.loadUrl(googleDocs + pdf_url);

    }

    private void logout() {
        SharedPrefManager.getInstance(this).logout();
        startActivity(new Intent(this, LoginActivity.class));
        Objects.requireNonNull(this).finish();
    }

}

package com.winbee.bodhayanacademy.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.winbee.bodhayanacademy.R;
import com.winbee.bodhayanacademy.SharedPrefManager;

public class MyProfile extends AppCompatActivity {

    ImageView imageView;
    TextView textViewName,textViewEmail;
    private String CurrentUserName,CurrentUserEmail;
    LinearLayout resetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" My Profile");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9B2727")));
        textViewName=findViewById(R.id.profile_username);
        textViewEmail=findViewById(R.id.profile_useremail);
        CurrentUserName = SharedPrefManager.getInstance(this).refCode().getName();
        CurrentUserEmail = SharedPrefManager.getInstance(this).refCode().getEmail();
        textViewName.setText(CurrentUserName);
        textViewEmail.setText(CurrentUserEmail);
        resetPassword=findViewById(R.id.resetPasswordlinearLayout);
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MyProfile.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

    }
}

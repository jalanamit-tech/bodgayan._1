package com.winbee.bodhayanacademy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.winbee.bodhayanacademy.Model.RefUser;
import com.winbee.bodhayanacademy.RetrofitApiCall.ApiClient;
import com.winbee.bodhayanacademy.Utils.ProgressBarUtil;
import com.winbee.bodhayanacademy.WebApi.ClientApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextname, editTextEmail, editTextPassword,editTextPhone,editTextDob,editTextRePassword;
    TextView editTextReferalCode;
    Button register;
    private ProgressBarUtil progressBarUtil;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        initView();
    }

    private void initView() {
        progressBarUtil   =  new ProgressBarUtil(this);
        editTextname =  findViewById(R.id.editTextUsername);
        editTextEmail =  findViewById(R.id.editTextEmail);
        editTextPassword =  findViewById(R.id.editTextPassword);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextRePassword  = findViewById(R.id.editTextre_Password);
        editTextReferalCode  = findViewById(R.id.editTextreferal_code);

        register=(Button)findViewById(R.id.buttonRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userValidation();
            }
        });

        findViewById(R.id.textViewLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }

    private void userValidation() {
        final String username = editTextname.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String re_password = editTextRePassword.getText().toString().trim();
        final String referal_code = editTextReferalCode.getText().toString().trim();
        final String phone = editTextPhone.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            editTextname.setError("Please enter username");
            editTextname.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            editTextPhone.setError("Please enter your mobile number");
            editTextPhone.requestFocus();
            return;
        }


        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Enter a password");
            editTextPassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(re_password)) {
            editTextRePassword.setError("Enter a password");
            editTextRePassword.requestFocus();
            return;
        }

        if (password.equals(re_password)) {

        }else{
            editTextRePassword.setError("Password are not matching");
            editTextRePassword.requestFocus();
            return;
        }
        RefUser refUser = new RefUser();
        refUser.setName(username);
        refUser.setPassword(password);
        refUser.setEmail(email);
        refUser.setMobile(phone);
        refUser.setRefcode(referal_code);


        CallSignupApi(refUser);
    }
    private void CallSignupApi(final RefUser refUser) {
        progressBarUtil.showProgress();
       ClientApi mService = ApiClient.getClient().create(ClientApi.class);
        Call<RefUser> call = mService.refUserSignIn(2, refUser.getName(),refUser.getEmail(),refUser.getMobile(),refUser.getRefcode(), refUser.getPassword());
        call.enqueue(new Callback<RefUser>() {
            @Override
            public void onResponse(Call<RefUser> call, Response<RefUser> response) {
                int statusCode = response.code();
                //List<RefUser> list ;
                if (statusCode == 200 && response.body().getRegistration_id() != null) {
                    progressBarUtil.hideProgress();
                    Intent intent = new Intent(RegisterActivity.this, OtpVerficationActivity.class);
                    intent.putExtra("message",refUser.getMobile());
                    intent.putExtra("email",refUser.getEmail());
                    intent.putExtra("name",refUser.getName());
                    intent.putExtra("password",refUser.getPassword());
                    startActivity(intent);
                    } else {
                        progressBarUtil.hideProgress();
                        Toast.makeText(RegisterActivity.this, "User Already exist", Toast.LENGTH_LONG).show();
                    }
                }


            @Override
            public void onFailure(Call<RefUser> call, Throwable t) {
                progressBarUtil.hideProgress();
                Toast.makeText(RegisterActivity.this,"Failed",Toast.LENGTH_LONG).show();
            }
        });
    }
}
package com.winbee.bodhayanacademy.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.winbee.bodhayanacademy.Adapter.GecSemesterAdapter;
import com.winbee.bodhayanacademy.Adapter.NotificationAdapter;
import com.winbee.bodhayanacademy.LoginActivity;
import com.winbee.bodhayanacademy.Model.BranchName;
import com.winbee.bodhayanacademy.Model.NotificationModel;
import com.winbee.bodhayanacademy.Model.SemesterName;
import com.winbee.bodhayanacademy.R;
import com.winbee.bodhayanacademy.RetrofitApiCall.ApiClient;
import com.winbee.bodhayanacademy.SharedPrefManager;
import com.winbee.bodhayanacademy.Utils.ProgressBarUtil;
import com.winbee.bodhayanacademy.WebApi.ClientApi;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GecNotificationActivity extends AppCompatActivity {
    private ArrayList<NotificationModel> list;
    private RecyclerView video_list_recycler;
    private NotificationAdapter adapter;
    RelativeLayout home,histroy,logout;
    private ProgressBarUtil progressBarUtil;
    String UserId;
    ImageView img_nonotification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gec_notification);
        video_list_recycler = findViewById(R.id.gec_semester_recycle);
        home=findViewById(R.id.layout_home);
        histroy=findViewById(R.id.layout_history);
        logout=findViewById(R.id.layout_logout);
        progressBarUtil   =  new ProgressBarUtil(this);
        UserId= SharedPrefManager.getInstance(this).refCode().getUserId();
        img_nonotification=findViewById(R.id.img_nonotification);

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
                Intent intent = new Intent(GecNotificationActivity.this, GecHomeActivity.class);
                startActivity(intent);
            }
        });
        callSemesterApiService();

    }

    private void callSemesterApiService(){
        progressBarUtil.showProgress();
        ClientApi mService = ApiClient.getClient().create(ClientApi.class);
        Call<ArrayList<NotificationModel>> call = mService.getNotification("WB_004",UserId);
        call.enqueue(new Callback<ArrayList<NotificationModel>>() {
            @Override
            public void onResponse(Call<ArrayList<NotificationModel>> call, Response<ArrayList<NotificationModel>> response) {

                int statusCode = response.code();
                list = new ArrayList();
                if(statusCode==200){
                    if (response.body().size()!=0) {
                        System.out.println("Suree body: " + response.body());
                        list = response.body();
                        adapter = new NotificationAdapter(GecNotificationActivity.this, list);
                        video_list_recycler.setAdapter(adapter);
                        progressBarUtil.hideProgress();
                    }else{
                        img_nonotification.setVisibility(View.VISIBLE);
                        progressBarUtil.hideProgress();
                    }
                }
                else{
                    System.out.println("Suree: response code"+response.message());
                    Toast.makeText(getApplicationContext(),"NetWork Issue,Please Check Network Connection" ,Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<NotificationModel>> call, Throwable t) {
                System.out.println("Suree: "+t.getMessage());
            }
        });
    }

    private void logout() {
        SharedPrefManager.getInstance(this).logout();
        startActivity(new Intent(this, LoginActivity.class));
        Objects.requireNonNull(this).finish();
    }
}

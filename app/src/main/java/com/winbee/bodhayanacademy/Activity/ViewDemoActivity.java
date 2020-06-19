package com.winbee.bodhayanacademy.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.winbee.bodhayanacademy.Adapter.DemoSemesterAdapter;
import com.winbee.bodhayanacademy.LoginActivity;
import com.winbee.bodhayanacademy.Model.BranchName;
import com.winbee.bodhayanacademy.Model.CoursePay;
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

public class ViewDemoActivity extends AppCompatActivity {
    private BranchName branch_data;
    private CoursePay coursePay;
    private ArrayList<SemesterName> list;
    private RecyclerView video_list_recycler;
    private DemoSemesterAdapter adapter;
    RelativeLayout home,histroy,logout;
    private ProgressBarUtil progressBarUtil;
    String UserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_demo);
        video_list_recycler = findViewById(R.id.gec_semester_recycle);
        home=findViewById(R.id.layout_home);
        histroy=findViewById(R.id.layout_history);
        logout=findViewById(R.id.layout_logout);
        progressBarUtil   =  new ProgressBarUtil(this);
        UserId=SharedPrefManager.getInstance(this).refCode().getUserId();
        String CourseID=getIntent().getStringExtra("courseID");
        if(CourseID!=null)
            System.out.println("intent "+CourseID);


            callSemesterApiService(CourseID);


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
                Intent intent = new Intent(ViewDemoActivity.this, GecHomeActivity.class);
                startActivity(intent);
            }
        });


    }






    private void callSemesterApiService(String CourseID){
            progressBarUtil.showProgress();
            ClientApi mService = ApiClient.getClient().create(ClientApi.class);
            Call<ArrayList<SemesterName>> call = mService.getSemester(1,"WB_004",CourseID,UserId);
            call.enqueue(new Callback<ArrayList<SemesterName>>() {
                @Override
                public void onResponse(Call<ArrayList<SemesterName>> call, Response<ArrayList<SemesterName>> response) {

                    int statusCode = response.code();
                    list = new ArrayList();
                    if(statusCode==200){
                        System.out.println("Suree body: "+response.body());
                       list = response.body();
                        adapter = new DemoSemesterAdapter(ViewDemoActivity.this,list);
                        video_list_recycler.setAdapter(adapter);
                        progressBarUtil.hideProgress();
                    }
                    else{
                        System.out.println("Suree: response code"+response.message());
                        Toast.makeText(getApplicationContext(),"NetWork Issue,Please Check Network Connection" ,Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ArrayList<SemesterName>> call, Throwable t) {
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

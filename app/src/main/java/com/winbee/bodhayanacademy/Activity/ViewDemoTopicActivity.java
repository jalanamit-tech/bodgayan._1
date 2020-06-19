package com.winbee.bodhayanacademy.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.winbee.bodhayanacademy.Adapter.DemoAdapter;
import com.winbee.bodhayanacademy.Adapter.GecSemesterTopicAdapter;
import com.winbee.bodhayanacademy.LoginActivity;
import com.winbee.bodhayanacademy.Model.DemoClas;
import com.winbee.bodhayanacademy.Model.SemesterName;
import com.winbee.bodhayanacademy.Model.UrlName;
import com.winbee.bodhayanacademy.R;
import com.winbee.bodhayanacademy.RetrofitApiCall.ApiClient;
import com.winbee.bodhayanacademy.SharedPrefManager;
import com.winbee.bodhayanacademy.Utils.ProgressBarUtil;
import com.winbee.bodhayanacademy.WebApi.ClientApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewDemoTopicActivity extends AppCompatActivity {
    private SemesterName semester_data;
    private ArrayList<DemoClas> list;
    private RecyclerView video_list_recycler;
    private DemoAdapter adapter;
    RelativeLayout home,histroy,logout;
    private ProgressBarUtil progressBarUtil;
    String UserID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gec_semester_topic);
        video_list_recycler = findViewById(R.id.gec_semester_topic_recycle);
        home=findViewById(R.id.layout_home);
        histroy=findViewById(R.id.layout_history);
        logout=findViewById(R.id.layout_logout);
        progressBarUtil   =  new ProgressBarUtil(this);
        UserID= SharedPrefManager.getInstance(this).refCode().getUserId();

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            semester_data = (SemesterName)bundle.getSerializable("semester_name");
            callTopicApiService();

        }
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
                Intent intent = new Intent(ViewDemoTopicActivity.this, GecHomeActivity.class);
                startActivity(intent);
            }
        });

    }
    private void callTopicApiService(){
        progressBarUtil.showProgress();
        ClientApi mService = ApiClient.getClient().create(ClientApi.class);
        Call<ArrayList<DemoClas>> call = mService.getDemoTopic(2,semester_data.getBucket_ID(),semester_data.getChild_Link(),UserID);
        call.enqueue(new Callback<ArrayList<DemoClas>>() {
            @Override
            public void onResponse(Call<ArrayList<DemoClas>> call, Response<ArrayList<DemoClas>> response) {

                int statusCode = response.code();
                list = new ArrayList();
                if(statusCode==200){
                    System.out.println("Suree body: "+response.body());
                    list = response.body();
                    adapter = new DemoAdapter(ViewDemoTopicActivity.this,list);
                    video_list_recycler.setAdapter(adapter);
                    progressBarUtil.hideProgress();
                }
                else{
                    System.out.println("Suree: response code"+response.message());
                    Toast.makeText(getApplicationContext(),"NetWork Issue,Please Check Network Connection" ,Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<DemoClas>> call, Throwable t) {
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

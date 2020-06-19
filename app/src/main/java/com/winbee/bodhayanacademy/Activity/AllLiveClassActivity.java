package com.winbee.bodhayanacademy.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.winbee.bodhayanacademy.Adapter.GecAllLiveClassesAdapter;
import com.winbee.bodhayanacademy.LoginActivity;
import com.winbee.bodhayanacademy.Model.LiveClass;
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

public class AllLiveClassActivity extends AppCompatActivity {
    private ArrayList<LiveClass> liveList;
    private RecyclerView video_list_recycler;
    private ProgressBarUtil progressBarUtil;
    private GecAllLiveClassesAdapter adapter;
    RelativeLayout home,histroy,logout;
    String UserId;
    TextView noclasses;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_live_class);
        home=findViewById(R.id.layout_home);
        histroy=findViewById(R.id.layout_history);
        logout=findViewById(R.id.layout_logout);
        noclasses=findViewById(R.id.noclasses);
        progressBarUtil   =  new ProgressBarUtil(this);
        video_list_recycler = findViewById(R.id.all_liveClasses);
        UserId=SharedPrefManager.getInstance(this).refCode().getUserId();
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
                Intent intent = new Intent(AllLiveClassActivity.this, GecHomeActivity.class);
                startActivity(intent);
            }
        });

        callLiveApiService();


    }
    private void callLiveApiService() {
        progressBarUtil.showProgress();
        ClientApi apiCAll = ApiClient.getClient().create(ClientApi.class);
        Call<ArrayList<LiveClass>> call = apiCAll.getLive(3,"WB_004",UserId);
        call.enqueue(new Callback<ArrayList<LiveClass>>() {
            @Override
            public void onResponse(Call<ArrayList<LiveClass>> call, Response<ArrayList<LiveClass>> response) {
                int statusCode = response.code();
                liveList = new ArrayList();
                if(statusCode==200){
                    System.out.println("Suree body: "+response.body());
                    if (response.body().size()!=0) {
                    liveList = response.body();
                    adapter = new GecAllLiveClassesAdapter(AllLiveClassActivity.this,liveList);
                    video_list_recycler.setAdapter(adapter);
                    progressBarUtil.hideProgress();
                    }else{
                        noclasses.setVisibility(View.VISIBLE);
                        progressBarUtil.hideProgress();

                    }
                }
                else{
                    System.out.println("Suree: response code"+response.message());
                    Toast.makeText(getApplicationContext(),"NetWork Issue,Please Check Network Connection" ,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<LiveClass>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed" + t.getMessage(),Toast.LENGTH_SHORT).show();

                System.out.println("Suree: Error "+t.getMessage());
            }
        });
    }

    private void logout() {
        SharedPrefManager.getInstance(this).logout();
        startActivity(new Intent(this, LoginActivity.class));
        Objects.requireNonNull(this).finish();
    }

}

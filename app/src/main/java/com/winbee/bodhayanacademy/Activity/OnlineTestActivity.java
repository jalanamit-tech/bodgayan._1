package com.winbee.bodhayanacademy.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.winbee.bodhayanacademy.Adapter.TestListAdapter;
import com.winbee.bodhayanacademy.Model.SIACDetailsDataModel;
import com.winbee.bodhayanacademy.Model.SIACDetailsMainModel;
import com.winbee.bodhayanacademy.R;
import com.winbee.bodhayanacademy.RetrofitApiCall.OnlineTestApiClient;
import com.winbee.bodhayanacademy.Utils.OnlineTestData;
import com.winbee.bodhayanacademy.WebApi.ClientApi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import io.supercharge.shimmerlayout.ShimmerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OnlineTestActivity extends AppCompatActivity {
    private ShimmerLayout shimmerLayout;
    private RecyclerView recycle_test;
    private Toast toast_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_test);
        iniIDs();
        getTestList();
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
    private void iniIDs(){
        shimmerLayout=findViewById(R.id.shimmerLayout);
        recycle_test=findViewById(R.id.recycle_test);
    }
    private void getTestList() {
        apiCall();
        ClientApi apiClient= OnlineTestApiClient.getClient().create(ClientApi.class);
        Call<SIACDetailsMainModel> call=apiClient.fetchSIACDetails(OnlineTestData.org_code,OnlineTestData.auth_code,OnlineTestData.bucketID);
        call.enqueue(new Callback<SIACDetailsMainModel>() {
            @Override
            public void onResponse(Call<SIACDetailsMainModel> call, Response<SIACDetailsMainModel> response) {
                apiCalled();
                SIACDetailsMainModel siacDetailsMainModel=response.body();
                if(siacDetailsMainModel!=null){
                    if (siacDetailsMainModel.getMessage().equalsIgnoreCase("true")){
                        List<SIACDetailsDataModel> siacDetailsDataModelList=new ArrayList<>(Arrays.asList(siacDetailsMainModel.getData()));
                        TestListAdapter testListAdapter=new TestListAdapter(OnlineTestActivity.this,siacDetailsDataModelList);
                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(OnlineTestActivity.this, LinearLayoutManager.VERTICAL, false);
                        recycle_test.setLayoutManager(layoutManager);
                        recycle_test.setItemAnimator(new DefaultItemAnimator());
                        recycle_test.setAdapter(testListAdapter);
                    }
                    else
                        doToast(siacDetailsMainModel.getMessage());
                }
                else
                    doToast("data null");
            }
            @Override
            public void onFailure(Call<SIACDetailsMainModel> call, Throwable t) {
                doToast(getString(R.string.went_wrong));
                System.out.println("call fail "+t);
                apiCalled();
            }
        });
    }
    private void apiCall() {
        shimmerLayout.setVisibility(View.VISIBLE);
        shimmerLayout.startShimmerAnimation();
    }
    private void apiCalled() {
        shimmerLayout.setVisibility(View.GONE);
        shimmerLayout.stopShimmerAnimation();
    }
    private void doToast(String msg){
        if(toast_msg !=null){
            toast_msg.cancel();
        }
        toast_msg = Toast.makeText(OnlineTestActivity.this, msg, Toast.LENGTH_SHORT);
        toast_msg.show();
    }
}

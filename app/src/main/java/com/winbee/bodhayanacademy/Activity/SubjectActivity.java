package com.winbee.bodhayanacademy.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.winbee.bodhayanacademy.Adapter.SubjectAdapter;
import com.winbee.bodhayanacademy.Model.SectionDetailsDataModel;
import com.winbee.bodhayanacademy.Model.SectionDetailsMainModel;
import com.winbee.bodhayanacademy.R;
import com.winbee.bodhayanacademy.RetrofitApiCall.ApiClient;
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

public class SubjectActivity extends AppCompatActivity {
    private ShimmerLayout shimmerLayout;
    private RecyclerView recycle_subject;
    private Toast toast_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        iniIDs();
        getSubjectName();
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
    private void iniIDs(){
        shimmerLayout=findViewById(R.id.shimmerLayout);
        recycle_subject=findViewById(R.id.recycle_subject);
    }
    private void getSubjectName() {
        apiCall();
        ClientApi apiClient= OnlineTestApiClient.getClient().create(ClientApi.class);
        Call<SectionDetailsMainModel> call=apiClient.fetchSectionDetails(OnlineTestData.org_code,OnlineTestData.auth_code);
        call.enqueue(new Callback<SectionDetailsMainModel>() {
            @Override
            public void onResponse(Call<SectionDetailsMainModel> call, Response<SectionDetailsMainModel> response) {
                apiCalled();
                SectionDetailsMainModel sectionDetailsMainModel=response.body();
                if(sectionDetailsMainModel!=null){
                    if (sectionDetailsMainModel.getMessage().equalsIgnoreCase("true")){
                        List<SectionDetailsDataModel> sectionDetailsDataModelList=new ArrayList<>(Arrays.asList(sectionDetailsMainModel.getData()));
                        SubjectAdapter subjectAdapter=new SubjectAdapter(SubjectActivity.this,sectionDetailsDataModelList);
                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(SubjectActivity.this, LinearLayoutManager.VERTICAL, false);
                        recycle_subject.setLayoutManager(layoutManager);
                        recycle_subject.setItemAnimator(new DefaultItemAnimator());
                        recycle_subject.setAdapter(subjectAdapter);
                    }
                    else
                        doToast(sectionDetailsMainModel.getMessage());
                }
                else
                    doToast("data null");
            }
            @Override
            public void onFailure(Call<SectionDetailsMainModel> call, Throwable t) {
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
        toast_msg = Toast.makeText(SubjectActivity.this, msg, Toast.LENGTH_SHORT);
        toast_msg.show();
    }
}

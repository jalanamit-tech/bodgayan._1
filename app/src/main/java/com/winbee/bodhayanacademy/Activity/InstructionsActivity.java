package com.winbee.bodhayanacademy.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.winbee.bodhayanacademy.Model.StartTestModel;
import com.winbee.bodhayanacademy.R;
import com.winbee.bodhayanacademy.RetrofitApiCall.OnlineTestApiClient;
import com.winbee.bodhayanacademy.SharedPrefManager;
import com.winbee.bodhayanacademy.Utils.OnlineTestData;
import com.winbee.bodhayanacademy.Utils.ProgressBarUtil;
import com.winbee.bodhayanacademy.WebApi.ClientApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InstructionsActivity extends AppCompatActivity {
    private TextView tv_subject_name;
    private RelativeLayout layout_start_test;
    String UserId;
    private ProgressBarUtil progressBarUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_instructions);
        iniIDs();
         setData();

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
    private void iniIDs() {
        tv_subject_name=findViewById(R.id.tv_subject_name);
        layout_start_test=findViewById(R.id.layout_start_test);
        UserId = SharedPrefManager.getInstance(this).refCode().getUserId();
        progressBarUtil   =  new ProgressBarUtil(this);
        layout_start_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InstructionsActivity.this, OnlineQuestionActivity.class);
                startActivity(intent);
                finish();
                callApiService();
            }
        });

    }
    private void setData() {
        tv_subject_name.setText(OnlineTestData.paperName);
    }

    private void callApiService() {
        progressBarUtil.showProgress();
        ClientApi apiCAll = OnlineTestApiClient.getClient().create(ClientApi.class);
        Call<StartTestModel> call = apiCAll.getStartTest("WB_004",UserId,OnlineTestData.paperID,"1","true");
        call.enqueue(new Callback<StartTestModel>() {
            @Override
            public void onResponse(Call<StartTestModel> call, Response<StartTestModel> response) {
                int statusCode = response.code();
                if(statusCode==200){
                    Toast.makeText(getApplicationContext(),"Success" ,Toast.LENGTH_SHORT).show();
                    progressBarUtil.hideProgress();
                }
                else{
                    System.out.println("Suree: response code"+response.message());
                    Toast.makeText(getApplicationContext(),"NetWork Issue,Please Check Network Connection" ,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StartTestModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed" + t.getMessage(),Toast.LENGTH_SHORT).show();

                System.out.println("Suree: Error "+t.getMessage());
            }
        });
    }

}

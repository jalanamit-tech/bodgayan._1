package com.winbee.bodhayanacademy.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.winbee.bodhayanacademy.Adapter.ViewResultAdapter;
import com.winbee.bodhayanacademy.Model.ViewResult;
import com.winbee.bodhayanacademy.R;
import com.winbee.bodhayanacademy.RetrofitApiCall.OnlineTestApiClient;
import com.winbee.bodhayanacademy.SharedPrefManager;
import com.winbee.bodhayanacademy.Utils.OnlineTestData;
import com.winbee.bodhayanacademy.WebApi.ClientApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewResultActivity extends AppCompatActivity {

    private TextView tv_paper_name,tv_section_name,tv_total_question,tv_total_attempt,tv_total_correct,tv_total_review,tv_total_wrong,tv_total_marks;
    private Button backbtn;
    String UserID;
    private ViewResult viewResult;
    private List<ViewResult> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);
        tv_paper_name=findViewById(R.id.tv_paper_name);
        tv_section_name=findViewById(R.id.tv_section_name);
        tv_total_question=findViewById(R.id.tv_total_question);
        tv_total_attempt=findViewById(R.id.tv_total_attempt);
        tv_total_correct=findViewById(R.id.tv_total_correct);
        tv_total_review=findViewById(R.id.tv_total_review);
        tv_total_wrong=findViewById(R.id.tv_total_wrong);
        tv_total_marks=findViewById(R.id.tv_total_marks);
        UserID = SharedPrefManager.getInstance(this).refCode().getUserId();
        backbtn=findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ViewResultActivity.this,GecHomeActivity.class);
                startActivity(intent);
            }
        });


        callApiService();

    }

    private void callApiService() {
        ClientApi apiCAll = OnlineTestApiClient.getClient().create(ClientApi.class);
        Call<ViewResult> call = apiCAll.viewResult("WB_004", OnlineTestData.paperID,UserID);
        call.enqueue(new Callback<ViewResult>() {
            @Override
            public void onResponse(Call<ViewResult> call, Response<ViewResult> response) {
                list = new ArrayList();
                int statusCode = response.code();
                if(statusCode==200 && response.body()!=null){
                    System.out.println("Suree body: "+response.body());
                    ViewResult viewResult = response.body();
                    tv_paper_name.setText(OnlineTestData.paperName);
                    tv_section_name.setText(OnlineTestData.paperName);
                    tv_total_question.setText(viewResult.getTotalQuestion());
                    tv_total_attempt.setText(viewResult.getAttempt());
                    tv_total_correct.setText(viewResult.getCorrect());
                    tv_total_review.setText(viewResult.getReview());
                    tv_total_wrong.setText(viewResult.getWrong());
                    tv_total_marks.setText(viewResult.getTotalMarks());

                }
                else{
                    System.out.println("Suree: response code"+response.message());
                    Toast.makeText(getApplicationContext(),"NetWork Issue,Please Check Network Connection" ,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ViewResult> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed" + t.getMessage(),Toast.LENGTH_SHORT).show();

                System.out.println("Suree: Error "+t.getMessage());
            }
        });
    }
}

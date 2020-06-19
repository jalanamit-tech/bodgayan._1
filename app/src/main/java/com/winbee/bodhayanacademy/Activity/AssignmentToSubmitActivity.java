package com.winbee.bodhayanacademy.Activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.winbee.bodhayanacademy.Adapter.AssignmentToSubmitAdapter;
import com.winbee.bodhayanacademy.LoginActivity;
import com.winbee.bodhayanacademy.Model.AssignmentDatum;
import com.winbee.bodhayanacademy.Model.AssignmentToSubmit;
import com.winbee.bodhayanacademy.R;
import com.winbee.bodhayanacademy.RetrofitApiCall.ApiClient;
import com.winbee.bodhayanacademy.SharedPrefManager;
import com.winbee.bodhayanacademy.Utils.ProgressBarUtil;
import com.winbee.bodhayanacademy.WebApi.ClientApi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignmentToSubmitActivity extends AppCompatActivity {
    private List<AssignmentDatum> list;
    RelativeLayout home,histroy,logout,today_classes;
    private ProgressBarUtil progressBarUtil;
    private AssignmentToSubmitAdapter assignmentToSubmitAdapter;
    private RecyclerView assignmentView;
    String Userid;
    private Button btn_submitted;
    private int mYear, mMonth, mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_to_submit);
        home=findViewById(R.id.layout_home);
        histroy=findViewById(R.id.layout_history);
        logout=findViewById(R.id.layout_logout);
        today_classes=findViewById(R.id.today_classes);
        Userid = SharedPrefManager.getInstance(this).refCode().getUserId();
        progressBarUtil   =  new ProgressBarUtil(this);
        assignmentView = findViewById(R.id.assignment_review);
        btn_submitted=findViewById(R.id.btn_submitted);
        btn_submitted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssignmentToSubmitActivity.this,SubmittedAssignmentActivity.class);
                startActivity(intent);

            }
        });

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
                Intent intent = new Intent(AssignmentToSubmitActivity.this,GecHomeActivity.class);
                startActivity(intent);
            }
        });

        callAllAssignment(Userid);
    }
    private void callAllAssignment(String Userid) {
        progressBarUtil.showProgress();
        ClientApi apiCAll = ApiClient.getClient().create(ClientApi.class);
        Call<AssignmentToSubmit> call = apiCAll.getAllAssignment("WB_004",Userid);
        call.enqueue(new Callback<AssignmentToSubmit>() {
            @Override
            public void onResponse(Call<AssignmentToSubmit> call, Response<AssignmentToSubmit> response) {
                AssignmentToSubmit assignmentToSubmit=response.body();
                int statusCode = response.code();
                list = new ArrayList();
                if(statusCode==200) {
                    if (response.body().getAssignment() == 1) {
                        list = new ArrayList<>(Arrays.asList(assignmentToSubmit.getData()));
                        System.out.println("Suree body: " + response.body());
                        assignmentToSubmitAdapter = new AssignmentToSubmitAdapter(AssignmentToSubmitActivity.this, list);
                        assignmentView.setAdapter(assignmentToSubmitAdapter);
                        progressBarUtil.hideProgress();
                    }else{
                        today_classes.setVisibility(View.VISIBLE);
                        progressBarUtil.hideProgress();
                    }
                }
                else{
                    System.out.println("Suree: response code"+response.message());
                    Toast.makeText(getApplicationContext(),"NetWork Issue,Please Check Network Connection" ,Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<AssignmentToSubmit> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed" + t.getMessage(),Toast.LENGTH_SHORT).show();
                System.out.println("Suree: Error "+t.getMessage());
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topic_search, menu);
        MenuItem searchViewItem = menu.findItem(R.id.topic_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setQueryHint("Search Assignment");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                datePicker();
                searchTopicByDate(query);
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                datePicker();
                searchTopicByDate(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void searchTopicByDate(String s) {
        List<AssignmentDatum> filteredList = new ArrayList<>();
        try{
            for(int i=0;i<list.size();i++) {
                String Date = "" ;
                if(list.get (i).getStart_date()!=null   ){
                    Date= list.get (i).getStart_date();
                }

                if(Date.toLowerCase().contains(s.toLowerCase()) ) {
                    filteredList.add(list.get(i));
                }
            }

            assignmentToSubmitAdapter = new AssignmentToSubmitAdapter(AssignmentToSubmitActivity.this, filteredList);
            assignmentView.setAdapter (assignmentToSubmitAdapter);
            assignmentToSubmitAdapter.notifyDataSetChanged();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void datePicker(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


    private void logout() {
        SharedPrefManager.getInstance(this).logout();
        startActivity(new Intent(this, LoginActivity.class));
        Objects.requireNonNull(this).finish();
    }

}

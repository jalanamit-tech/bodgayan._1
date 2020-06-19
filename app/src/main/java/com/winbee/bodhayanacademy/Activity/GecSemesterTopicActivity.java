package com.winbee.bodhayanacademy.Activity;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.winbee.bodhayanacademy.Adapter.GecSemesterTopicAdapter;
import com.winbee.bodhayanacademy.LoginActivity;
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

public class GecSemesterTopicActivity extends AppCompatActivity {
    private SemesterName semester_data;
    private List<UrlName> list;
    private RecyclerView video_list_recycler;
    private GecSemesterTopicAdapter adapter;
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
        UserID=SharedPrefManager.getInstance(this).refCode().getUserId();
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
                Intent intent = new Intent(GecSemesterTopicActivity.this, GecHomeActivity.class);
                startActivity(intent);
            }
        });

    }
    private void callTopicApiService(){
        progressBarUtil.showProgress();
        ClientApi mService = ApiClient.getClient().create(ClientApi.class);
        Call<ArrayList<UrlName>> call = mService.getTopic(2,semester_data.getBucket_ID(),semester_data.getChild_Link(),UserID);
        call.enqueue(new Callback<ArrayList<UrlName>>() {
            @Override
            public void onResponse(Call<ArrayList<UrlName>> call, Response<ArrayList<UrlName>> response) {

                int statusCode = response.code();
                list = new ArrayList();
                if(statusCode==200){
                    System.out.println("Suree body: "+response.body());
                    list = response.body();
                    adapter = new GecSemesterTopicAdapter(GecSemesterTopicActivity.this,list);
                    video_list_recycler.setAdapter(adapter);
                    progressBarUtil.hideProgress();
                }
                else{
                    System.out.println("Suree: response code"+response.message());
                    Toast.makeText(getApplicationContext(),"NetWork Issue,Please Check Network Connection" ,Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<UrlName>> call, Throwable t) {
                System.out.println("Suree: "+t.getMessage());
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topic_search, menu);
        MenuItem searchViewItem = menu.findItem(R.id.topic_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setQueryHint("Search Topic");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchTopicByName(query);
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchTopicByName(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void searchTopicByName(String s) {
        List<UrlName> filteredList = new ArrayList<>();
        try{
            for(int i=0;i<list.size();i++) {
                String subjectName = "", topic="", type="" ;

                if(list.get (i).getSubject()!=null || list.get (i).getTopic()!=null  ){
                    subjectName= list.get (i).getSubject();
                    topic= list.get (i).getTopic();
                    //type= list.get(i).getType();
                }

                if(subjectName.toLowerCase().contains(s.toLowerCase()) || topic.toLowerCase().contains(s.toLowerCase()) ) {
                    filteredList.add(list.get(i));
                }
            }

            adapter = new GecSemesterTopicAdapter (GecSemesterTopicActivity.this, filteredList);
            video_list_recycler.setAdapter (adapter);
            adapter.notifyDataSetChanged();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void logout() {
        SharedPrefManager.getInstance(this).logout();
        startActivity(new Intent(this, LoginActivity.class));
        Objects.requireNonNull(this).finish();
    }


}

package com.winbee.bodhayanacademy.Activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.winbee.bodhayanacademy.Adapter.AllCourseAdapter;
import com.winbee.bodhayanacademy.Adapter.GecHomeAdapter;
import com.winbee.bodhayanacademy.Adapter.GecHomeLiveAdapter;
import com.winbee.bodhayanacademy.BuildConfig;
import com.winbee.bodhayanacademy.LoginActivity;
import com.winbee.bodhayanacademy.Model.BannerModel;
import com.winbee.bodhayanacademy.Model.BranchName;
import com.winbee.bodhayanacademy.Model.CourseDatum;
import com.winbee.bodhayanacademy.Model.LiveClass;
import com.winbee.bodhayanacademy.Model.PurchasedDataModel;
import com.winbee.bodhayanacademy.Model.PurchasedMainModel;
import com.winbee.bodhayanacademy.Model.RefCode;
import com.winbee.bodhayanacademy.Model.SectionDetailsDataModel;
import com.winbee.bodhayanacademy.R;
import com.winbee.bodhayanacademy.RetrofitApiCall.ApiClient;
import com.winbee.bodhayanacademy.SharedPrefManager;
import com.winbee.bodhayanacademy.Utils.AssignmentData;
import com.winbee.bodhayanacademy.Utils.LinePagerIndicatorDecoration;
import com.winbee.bodhayanacademy.Utils.ProgressBarUtil;
import com.winbee.bodhayanacademy.WebApi.ClientApi;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GecHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<CourseDatum> list;
    private ArrayList<BranchName> list1;
    private List<BannerModel> bannerModels;
    private ArrayList<LiveClass> liveList;
    private RecyclerView video_list_recycler,video_list_recycler1;
    private GecHomeAdapter adapter;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference messagedb;
    private AllCourseAdapter CourseAdapter;
    private GecHomeLiveAdapter liveAdapter;
    ScrollView scrollView;
    RelativeLayout home,histroy,logout,layout_courses,layout_all_course,layout_my_assignment,layout_online_test,courses,layout_buy_course;
    private ProgressBarUtil progressBarUtil;
    TextView viewAll,noclasses,nocourse,text_live;
    String sCurrentVersion,sLastestVersion,Userid,Username,UserPassword;
    private SwipeRefreshLayout home_page;
    ImageSlider imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gec_home);

        home_page = (SwipeRefreshLayout) findViewById(R.id.home_page);
        // Setup refresh listener which triggers new data loading
        home_page.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callLiveApiService();
                callApiService(Userid);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        home_page.setRefreshing(false);
                    }
                },4000);
            }
        });

        new GetLastesVersion().execute();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        scrollView=findViewById(R.id.scroll_view);
        video_list_recycler = findViewById(R.id.gec_home_recycle);
        GecHomeAdapter myAdapter = new GecHomeAdapter(this,list);
        video_list_recycler.setLayoutManager(new GridLayoutManager(this,2));
        video_list_recycler.setAdapter(myAdapter);
        AllCourseAdapter allCourseAdapter = new AllCourseAdapter(this,list1);
        video_list_recycler.setLayoutManager(new GridLayoutManager(this,2));
        video_list_recycler.setAdapter(allCourseAdapter);




        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        video_list_recycler1 = findViewById(R.id.gec_home_recycle1);
        video_list_recycler1.setLayoutManager(layoutManager);
        GecHomeLiveAdapter myAdapter1 = new GecHomeLiveAdapter(this,liveList);
        video_list_recycler1.setAdapter(myAdapter1);


        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(video_list_recycler1);
        video_list_recycler1.addItemDecoration(new LinePagerIndicatorDecoration());

        auth = FirebaseAuth.getInstance();
        home=findViewById(R.id.layout_home);
        histroy=findViewById(R.id.layout_history);
        logout=findViewById(R.id.layout_logout);
        layout_all_course=findViewById(R.id.layout_all_course);
        layout_my_assignment=findViewById(R.id.layout_my_assignment);
        layout_online_test=findViewById(R.id.layout_online_test);
        layout_buy_course=findViewById(R.id.layout_buy_course);
        viewAll=findViewById(R.id.text_viewAll);
        text_live=findViewById(R.id.text_live);
        text_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://a2znewslive.livebox.co.in/testhls/live.m3u8"));
                startActivity(intent);
            }
        });
        noclasses=findViewById(R.id.noclasses);
        nocourse=findViewById(R.id.nocourse);
        layout_courses=findViewById(R.id.layout_courses);
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GecHomeActivity.this,AllLiveClassActivity.class);
                startActivity(intent);
            }
        });
        Userid = SharedPrefManager.getInstance(this).refCode().getUserId();
        Username=SharedPrefManager.getInstance(this).refCode().getEmail();
        UserPassword=SharedPrefManager.getInstance(this).refCode().getPassword();
        layout_my_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GecHomeActivity.this,AssignmentToSubmitActivity.class);
                startActivity(intent);
            }
        });
        layout_online_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GecHomeActivity.this,SubjectActivity.class);
                startActivity(intent);
            }
        });
        layout_buy_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GecHomeActivity.this,AllCourseActivity.class);
                startActivity(intent);
            }
        });

        Uservalidation();


        progressBarUtil   =  new ProgressBarUtil(this);

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
                Intent intent = new Intent(GecHomeActivity.this,GecHomeActivity.class);
                startActivity(intent);
            }
        });


         imageSlider =(ImageSlider)findViewById(R.id.slider);

        List<SlideModel> slideModels=new ArrayList<>();
     slideModels.add(new SlideModel("https://etimg.etb2bimg.com/photo/74567470.cms"));
     slideModels.add(new SlideModel("https://s3.amazonaws.com/influencive.com/wp-content/uploads/2020/01/30131217/pexels-photo-2312369-e1580418773326.jpeg"));
        imageSlider.setImageList(slideModels,true);
       // callBannerService();
        callLiveApiService();
        callApiService(Userid);

    }

    private void callApiService(String Userid) {
        progressBarUtil.showProgress();
        ClientApi apiCAll = ApiClient.getClient().create(ClientApi.class);
        Call<PurchasedMainModel> call = apiCAll.getCourseById(1,Userid,"WB_004");
        call.enqueue(new Callback<PurchasedMainModel>() {
            @Override
            public void onResponse(Call<PurchasedMainModel> call, Response<PurchasedMainModel> response) {
                PurchasedMainModel purchasedMainModel=response.body();
                int statusCode = response.code();
                list = new ArrayList();
                if(statusCode==200){
                    if (purchasedMainModel==null) {
                        courses.setVisibility(View.VISIBLE);
                        list = new ArrayList<>(Arrays.asList(Objects.requireNonNull(purchasedMainModel).getData()));
                        System.out.println("Suree body: " + response.body());
                        adapter = new GecHomeAdapter(GecHomeActivity.this, list);
                        video_list_recycler.setAdapter(adapter);
                        progressBarUtil.hideProgress();
                    }else{

                        callApiService();
                    }
                }
                else{
                    System.out.println("Suree: response code"+response.message());
                    Toast.makeText(getApplicationContext(),"NetWork Issue,Please Check Network Connection" ,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PurchasedMainModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed" + t.getMessage(),Toast.LENGTH_SHORT).show();

                System.out.println("Suree: Error "+t.getMessage());
            }
        });
    }
    private void callApiService() {
        progressBarUtil.showProgress();
        ClientApi apiCAll = ApiClient.getClient().create(ClientApi.class);
        Call<ArrayList<BranchName>> call = apiCAll.getBranchId(1,"WB_004","WB_004",Userid);
        call.enqueue(new Callback<ArrayList<BranchName>>() {
            @Override
            public void onResponse(Call<ArrayList<BranchName>> call, Response<ArrayList<BranchName>> response) {
                int statusCode = response.code();
                list = new ArrayList();
                if(statusCode==200){
                    System.out.println("Suree body: "+response.body());
                    list1 = response.body();
                    CourseAdapter = new AllCourseAdapter(GecHomeActivity.this,list1);
                    video_list_recycler.setAdapter(CourseAdapter);

                    progressBarUtil.hideProgress();
                }
                else{
                    System.out.println("Suree: response code"+response.message());
                    Toast.makeText(getApplicationContext(),"NetWork Issue,Please Check Network Connection" ,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<BranchName>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed" + t.getMessage(),Toast.LENGTH_SHORT).show();

                System.out.println("Suree: Error "+t.getMessage());
            }
        });
    }
//for banner display
//    private void callBannerService() {
//        progressBarUtil.showProgress();
//        ClientApi apiCAll = ApiClient.getClient().create(ClientApi.class);
//        Call<ArrayList<BannerModel>> call = apiCAll.getBanner("WB_004");
//        call.enqueue(new Callback<ArrayList<BannerModel>>() {
//            @Override
//            public void onResponse(Call<ArrayList<BannerModel>> call, Response<ArrayList<BannerModel>> response) {
//                int statusCode = response.code();
//                bannerModels = new ArrayList();
//                if(statusCode==200 && response.body()!=null){
//                    BannerModel bannerModel = response.body().get(1);
//                    System.out.println("Suree body: "+response.body());
//                   // bannerModels = response.body();
//                   // List<BannerModel> bannerModelsList=new ArrayList<>(Arrays.asList())
//                   //   AssignmentData.Banner=bannerModels.getFile();
//                      imageSlider.setImageList(bannerModel,true);
////                    CourseAdapter = new AllCourseAdapter(GecHomeActivity.this,bannerModels);
////                    video_list_recycler.setAdapter(CourseAdapter);
//
//                    progressBarUtil.hideProgress();
//                }
//                else{
//                    System.out.println("Suree: response code"+response.message());
//                    Toast.makeText(getApplicationContext(),"NetWork Issue,Please Check Network Connection" ,Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<BannerModel>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),"Failed" + t.getMessage(),Toast.LENGTH_SHORT).show();
//
//                System.out.println("Suree: Error "+t.getMessage());
//            }
//        });
//    }
    private void callLiveApiService() {
        progressBarUtil.showProgress();
        ClientApi apiCAll = ApiClient.getClient().create(ClientApi.class);
        Call<ArrayList<LiveClass>> call = apiCAll.getLive(3,"WB_004",Userid);
        call.enqueue(new Callback<ArrayList<LiveClass>>() {
            @Override
            public void onResponse(Call<ArrayList<LiveClass>> call, Response<ArrayList<LiveClass>> response) {
                int statusCode = response.code();
                liveList = new ArrayList();
                if(statusCode==200){
                    System.out.println("Suree body: "+response.body());
                    if (response.body().size()!=0) {
                        liveList = response.body();
                        liveAdapter = new GecHomeLiveAdapter(GecHomeActivity.this, liveList);
                        video_list_recycler1.setAdapter(liveAdapter);
                        progressBarUtil.hideProgress();
                    }else{
                        progressBarUtil.hideProgress();
                        noclasses.setVisibility(View.VISIBLE);

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


    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.notification) {
            Intent intent = new Intent(GecHomeActivity.this,GecNotificationActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        }else if (id == R.id.nav_profile) {
            Intent intent = new Intent(GecHomeActivity.this, MyProfile.class);
            startActivity(intent);
        } else if (id == R.id.nav_about) {
            Intent d=  new Intent(GecHomeActivity.this, Aboutus.class);
            startActivity(d);
        } else if (id == R.id.nav_rate) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" +getPackageName())));

        } else if (id == R.id.nav_logout) {
            logout();
        }
        else if (id == R.id.nav_share) {
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Bodhayan Academy");
                String shareMessage= "\nBodhayan Academy is the biggest Defence Career Coaching Academy in  Chirawa( Rajasthan ) Bodhayan Coaching was established " +
                        "as a top ranking institute from three year,solely with the aim of helping students to achieve success";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id="+getPackageName() ;
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch(Exception e) {
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        SharedPrefManager.getInstance(this).logout();
        startActivity(new Intent(this, LoginActivity.class));
        Objects.requireNonNull(this).finish();
    }


    private class GetLastesVersion extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                sLastestVersion = Jsoup
                        .connect("https://play.google.com/store/apps/details?id="+getPackageName())
                        .timeout(3000)
                        .get()
                        .select("div.hAyfc:nth-child(4)>"+"span:nth-child(2)> div:nth-child(1)"+"> span:nth-child(1)")
                        .first()
                        .ownText();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sLastestVersion;
        }
        @Override
        protected void onPostExecute(String s) {
            sCurrentVersion = BuildConfig.VERSION_NAME;
            if (sLastestVersion !=null){
                float cVersion = Float.parseFloat(sCurrentVersion);
                float lVersion = Float.parseFloat(sLastestVersion);
                if (lVersion>cVersion){
                    updateAlertDialog();
                }
            }
        }
    }

    private void updateAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.app_name));
        builder.setMessage("update available");
        builder.setCancelable(false);
        builder.setPositiveButton("update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id="+getPackageName())));

                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();

            }
        });

        builder.show();
    }
    public void Uservalidation() {
        String Email = Username;
        String Password=UserPassword;
        if (!Email.equals("")&& !Password.equals("")){
            auth.signInWithEmailAndPassword(Email,Password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(GecHomeActivity.this, "welcome", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(GecYouTubeLiveActivity.this,GecYouTubeLiveActivity.class);
//                                startActivity(intent);

                            }else{
                                Toast.makeText(GecHomeActivity.this, "NetWork Issue Please Check Internet Connection", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }
}

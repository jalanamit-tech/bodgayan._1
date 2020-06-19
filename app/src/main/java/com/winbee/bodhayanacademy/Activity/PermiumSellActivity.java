package com.winbee.bodhayanacademy.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;
import com.squareup.picasso.Picasso;
import com.winbee.bodhayanacademy.LoginActivity;
import com.winbee.bodhayanacademy.Model.BranchName;
import com.winbee.bodhayanacademy.Model.CoursePay;
import com.winbee.bodhayanacademy.Model.DummyModel;
import com.winbee.bodhayanacademy.Model.PaymentModel;
import com.winbee.bodhayanacademy.Model.SemesterName;
import com.winbee.bodhayanacademy.R;
import com.winbee.bodhayanacademy.RetrofitApiCall.ApiClient;
import com.winbee.bodhayanacademy.SharedPrefManager;
import com.winbee.bodhayanacademy.Utils.AssignmentData;
import com.winbee.bodhayanacademy.Utils.ProgressBarUtil;
import com.winbee.bodhayanacademy.WebApi.ClientApi;
import org.json.JSONObject;
import java.util.List;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PermiumSellActivity extends Activity implements PaymentResultWithDataListener {
    Button pay_btn,view_demo;
    String TAG="payment activity";
    TextView course_id;
    TextView user_id;
    TextView amount_org_id;
    TextView org_id,bucket_id;
    private BranchName branchName;
    private String UserId,UserName;
    RelativeLayout home,histroy,logout;
    private ProgressBarUtil progressBarUtil;
    ImageView payment_image;

    TextView gec_branchname, someTextView,txt_total_video,txt_total_document,txt_actual_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permium_sell);


        UserId = SharedPrefManager.getInstance(this).refCode().getUserId();
        UserName = SharedPrefManager.getInstance(this).refCode().getUsername();
         someTextView = (TextView) findViewById(R.id.txt_discount);


        course_id=findViewById(R.id.course_id);
        user_id=findViewById(R.id.user_id);
        view_demo=findViewById(R.id.view_demo);
        histroy=findViewById(R.id.layout_history);
        logout=findViewById(R.id.layout_logout);
        payment_image=findViewById(R.id.payment_image);
        payment_image=findViewById(R.id.payment_image);
        txt_total_video=findViewById(R.id.txt_total_video);
        txt_total_document=findViewById(R.id.txt_total_document);
        txt_actual_price=findViewById(R.id.txt_actual_price);
        gec_branchname=findViewById(R.id.gec_branchname);
        bucket_id=findViewById(R.id.bucket_id);
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
        user_id.setText(UserId);
      amount_org_id=findViewById(R.id.amount_org_id);
        org_id=findViewById(R.id.org_id);
        Checkout.preload(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            branchName = (BranchName) bundle.getSerializable("branch_name");
             course_id.setText(branchName.getChild_Link());
             amount_org_id.setText(branchName.getDiscount_price());
            AssignmentData.Amount=branchName.getDiscount_price();
            view_demo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PermiumSellActivity.this, ViewDemoActivity.class);
                    intent.putExtra("courseID",branchName.getChild_Link());
                    startActivity(intent);
                }
            });
            callSemesterApiService();
        }
        pay_btn=findViewById(R.id.pay_btn);
        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userValidation();
            }
        });
    }
    public void startPayment() {
        Checkout checkout = new Checkout();

        String str = branchName.getDiscount_price();
        Double inum = Double.parseDouble(str);
        Double sum = inum*100;
        String str1 = Double.toString(sum);
        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", UserName);




            options.put("description", "Purchase Course");
           options.put("order_id",    AssignmentData.RazorpayOrderId);
            options.put("image", "http://edu.bodhayanacademy.in/assets/images/client/logo-bodhayan-academy.png");
            options.put("currency", "INR");
            options.put("amount",str1);

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID, PaymentData data) {
        String paymentId = data.getPaymentId();
        String signature = data.getSignature();
        String orderId = data.getOrderId();
        Toast.makeText(getApplicationContext(),"We have received your payment,Please for Confirmation " ,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PermiumSellActivity.this,GecHomeActivity.class);
        startActivity(intent);
    }
    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {

    }



    private void userValidation() {
        final String Course_id = course_id.getText().toString();
        final String User_id = user_id.getText().toString();
        final String Amount_org_id = amount_org_id.getText().toString();
        final String Org_id = org_id.getText().toString();



        PaymentModel paymentModel = new PaymentModel();
        paymentModel.setCourse_id(Course_id);
        paymentModel.setUser_id(User_id);
        paymentModel.setAmount_org_id(Amount_org_id);
        paymentModel.setOrg_id(Org_id);




        callPayment(paymentModel);

    }
    private void callPayment(final PaymentModel paymentModel){
        ClientApi apiCall = ApiClient.getClient().create(ClientApi.class);
        Call<PaymentModel> call =apiCall.fetchPaymentData(paymentModel.getCourse_id(),paymentModel.getUser_id(),paymentModel.getAmount_org_id(),paymentModel.getOrg_id());
        call.enqueue(new Callback<PaymentModel>() {
            @Override
            public void onResponse(Call<PaymentModel> call, Response<PaymentModel> response) {
                int statusCode = response.code();
                if(statusCode==200 && response.body()!=null){
                    AssignmentData.Org_id=response.body().getOrgOrderId();
                    AssignmentData.RazorpayOrderId=response.body().getRazorpayOrderId();
                    Toast.makeText(getApplicationContext(),"Payment started" ,Toast.LENGTH_SHORT).show();
                    startPayment();
                }
                else{
                    System.out.println("Sur: response code"+response.message());
                    Toast.makeText(getApplicationContext(),"NetWork Issue,Please Check Network Connection" ,Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PaymentModel> call, Throwable t) {
                 System.out.println("Suree: "+t.getMessage());

                Toast.makeText(getApplicationContext(),"Failed"+t.getMessage() ,Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void callSemesterApiService(){
        progressBarUtil.showProgress();
        ClientApi mService = ApiClient.getClient().create(ClientApi.class);
        Call<CoursePay> call = mService.getSemesterName(1,branchName.getBucket_ID(),branchName.getChild_Link());
        call.enqueue(new Callback<CoursePay>() {
            @Override
            public void onResponse(Call<CoursePay> call, Response<CoursePay> response) {

                int statusCode = response.code();
                if(statusCode==200 && response.body()!=null){
                    System.out.println("Suree body: "+response.body());
                    CoursePay semesterName = response.body();
                  Picasso.get().load(semesterName.getBucket_Cover_Image()).fit().into(payment_image);
                    gec_branchname.setText(Html.fromHtml(semesterName.getDescription()));
                    someTextView.setText(semesterName.getDisplay_price()); // SomeString = your old price
                    someTextView.setPaintFlags(someTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    txt_total_document.setText(String.valueOf(semesterName.getTotal_Document()));
                    txt_total_video.setText(semesterName.getTotal_Video());
                    txt_actual_price.setText(semesterName.getDiscount_price());
                    bucket_id.setText(semesterName.getBucket_Name());
                    progressBarUtil.hideProgress();
                }
                else{
                    System.out.println("Suree: response code"+response.message());
                    Toast.makeText(getApplicationContext(),"NetWork Issue,Please Check Network Connection" ,Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CoursePay> call, Throwable t) {
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

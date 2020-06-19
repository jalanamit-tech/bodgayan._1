package com.winbee.bodhayanacademy.Activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.winbee.bodhayanacademy.Model.AssignmentDatum;
import com.winbee.bodhayanacademy.Model.SubmitAssignment;
import com.winbee.bodhayanacademy.R;
import com.winbee.bodhayanacademy.RetrofitApiCall.ApiClient;
import com.winbee.bodhayanacademy.SharedPrefManager;
import com.winbee.bodhayanacademy.Utils.AssignmentData;
import com.winbee.bodhayanacademy.Utils.ProgressBarUtil;
import com.winbee.bodhayanacademy.WebApi.ClientApi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitAssignmentActivity extends AppCompatActivity {

    boolean isOnlyImageAllowed = false;

    private EditText description;
    private Button uploadButton,addImageButton;
    private TextView responseTextView;
    String UserId;
    private ProgressBarUtil progressBarUtil;
    AssignmentDatum assignmentDatum;
    private  static final int IMG_REQUEST=777;
    private Bitmap bitmap;
    ImageView image_view;
    LinearLayout layout_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_assignment);

        description = findViewById(R.id.description);
        addImageButton = findViewById(R.id.addImageButton);
        uploadButton = findViewById(R.id.uploadButton);
        image_view = findViewById(R.id.image_view);
        layout_description = findViewById(R.id.layout_description);
        progressBarUtil = new ProgressBarUtil(this);
        UserId= SharedPrefManager.getInstance(this).refCode().getUserId();
       final String AssignmentId=getIntent().getStringExtra("assignmentId");
       final String  CourseId=getIntent().getStringExtra("courseId");

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileValidation();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            assignmentDatum = (AssignmentDatum) bundle.getSerializable("assignmentDatum");


        }
    }
    private void fileValidation() {
         String Description = description.getText().toString();
         String Image =imageToString();


        //validating inputs
        if (TextUtils.isEmpty(Description)) {
            description.setError("Please enter your answer");
            description.requestFocus();
            return;
        }

        callSubmitAssignment(Description,Image);
    }
    private void callSubmitAssignment(String Description,String Image){

        progressBarUtil.showProgress();
        ClientApi mService = ApiClient.getClient().create(ClientApi.class);
        Call<SubmitAssignment> call = mService.getSubmitAssignment("WB_004",UserId, AssignmentData.BucketId,AssignmentData.AssignmentId,Image,Description);
        call.enqueue(new Callback<SubmitAssignment>() {
            @Override
            public void onResponse(Call<SubmitAssignment> call, Response<SubmitAssignment> response) {
                int statusCode  = response.code();
                if(statusCode==200  ) {
                    progressBarUtil.hideProgress();
                    Intent intent = new Intent(SubmitAssignmentActivity.this,GecHomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(SubmitAssignmentActivity.this, "Submit Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    progressBarUtil.hideProgress();
                    Toast.makeText(getApplicationContext(),"NetWork Issue,Please Check Network Connection" ,Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SubmitAssignment> call, Throwable t) {
                Toast.makeText(SubmitAssignmentActivity.this,"Failed"+t.getMessage(),Toast.LENGTH_LONG).show();
                System.out.println(t.getLocalizedMessage());
            }
        });
    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data!=null) {
            Uri selectedImage = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);

                image_view.setImageBitmap(bitmap);

                Toast.makeText(this, "File Selected", Toast.LENGTH_SHORT).show();
    addImageButton.setVisibility(View.GONE);
    uploadButton.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private String imageToString()
    {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }


}
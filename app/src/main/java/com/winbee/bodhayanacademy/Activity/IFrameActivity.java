package com.winbee.bodhayanacademy.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.winbee.bodhayanacademy.R;
import com.winbee.bodhayanacademy.Utils.FullScreenMediaController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class IFrameActivity extends AppCompatActivity {
    VideoView vimeo_video;
    Button play_btn;
    String videoUrl="https://vimeo.com/430952632";
    String v_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iframe);

        vimeo_video=findViewById(R.id.vimeo_video);
        String fullScreen =  getIntent().getStringExtra("fullScreenInd");
        if("y".equals(fullScreen)){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        }

        String urlreplace= videoUrl.replace("https://vimeo.com/","https://player.vimeo.com/video/");
        String Url=urlreplace;
        Url+="/config";
        vimeoVideo(Url);
    }

    private void vimeoVideo(String Url) {
       StringRequest str = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {

              try {
                  JSONObject jsonObject = new JSONObject(response);
                  JSONObject req=jsonObject.getJSONObject("request");
                  JSONObject files=req.getJSONObject("files");
                  JSONArray progressive=files.getJSONArray("progressive");
                  JSONObject array1=progressive.getJSONObject(1);
                   v_url=array1.getString("url");

              }catch (JSONException e){
                  e.printStackTrace();
              }

               MediaController mediaController = new FullScreenMediaController(IFrameActivity.this);
               mediaController.setAnchorView(mediaController);
               vimeo_video.setVideoURI(Uri.parse(v_url));
               vimeo_video.setMediaController(mediaController);
               vimeo_video.start();
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(str);
    }


}

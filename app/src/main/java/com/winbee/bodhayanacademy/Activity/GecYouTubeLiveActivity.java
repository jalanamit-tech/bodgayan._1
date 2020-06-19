package com.winbee.bodhayanacademy.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.winbee.bodhayanacademy.Adapter.MessageAdapter;
import com.winbee.bodhayanacademy.Adapter.TodayLiveAdapter;
import com.winbee.bodhayanacademy.LoginActivity;
import com.winbee.bodhayanacademy.Model.AllModel;
import com.winbee.bodhayanacademy.Model.FireBaseUserId;
import com.winbee.bodhayanacademy.Model.LiveClass;
import com.winbee.bodhayanacademy.Model.Message;
import com.winbee.bodhayanacademy.R;
import com.winbee.bodhayanacademy.RetrofitApiCall.ApiClient;
import com.winbee.bodhayanacademy.SharedPrefManager;
import com.winbee.bodhayanacademy.Utils.ProgressBarUtil;
import com.winbee.bodhayanacademy.WebApi.ClientApi;

import java.util.ArrayList;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

import static com.google.android.exoplayer2.Player.STATE_BUFFERING;
import static com.google.android.exoplayer2.Player.STATE_ENDED;
import static com.google.android.exoplayer2.Player.STATE_IDLE;
import static com.google.android.exoplayer2.Player.STATE_READY;


public class GecYouTubeLiveActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String API_KEY = "AIzaSyBlEPocq2s2bDmWDMBRXAf8Mhf3wlFNYGI";
    private LiveClass liveClass;
    RelativeLayout home,histroy,logout,layout_details;
    TextView video_topic,video_info,video_started,video_subject;
    Button btn_less;
    ImageButton btn_more;
    private RecyclerView video_list_recycler;
    private ProgressBarUtil progressBarUtil;
    private TodayLiveAdapter adapter;
    String UserId,UserName;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference messagedb;
    MessageAdapter messageAdapter;
    FireBaseUserId u;
    List<Message> messages;
    private SimpleExoPlayer player;
    private WebView simpleExoPlayerView;
    private String videoUrl = "http://winbeesolutions.livebox.co.in/BodhayanAcademyhls/LiveClasses.m3u8";


    RecyclerView messageView;
    EditText txt_message;
    ImageButton btn_send;
    private boolean fullscreen=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gec_you_tube_live);
        init();
        home=findViewById(R.id.layout_home);
        auth = FirebaseAuth.getInstance();
        histroy=findViewById(R.id.layout_history);
        logout=findViewById(R.id.layout_logout);
        video_topic = findViewById(R.id.video_topic);
        video_info = findViewById(R.id.video_info);
        video_started = findViewById(R.id.video_started);
        btn_more = findViewById(R.id.btn_more);
        btn_less = findViewById(R.id.btn_less);
        video_subject = findViewById(R.id.video_subject);
        layout_details = findViewById(R.id.layout_details);
        video_list_recycler = findViewById(R.id.gec_semester_recycle);
        progressBarUtil   =  new ProgressBarUtil(this);




        UserId=SharedPrefManager.getInstance(this).refCode().getUserId();
        UserName=SharedPrefManager.getInstance(this).refCode().getName();


        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_less.setVisibility(View.VISIBLE);
                btn_more.setVisibility(View.GONE);
                layout_details.setVisibility(View.VISIBLE);
            }
        });

        btn_less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_less.setVisibility(View.GONE);
                btn_more.setVisibility(View.VISIBLE);
                layout_details.setVisibility(View.GONE);
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
                Intent intent = new Intent(GecYouTubeLiveActivity.this, GecHomeActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            liveClass = (LiveClass) bundle.getSerializable("ContentLink");
            if(liveClass!=null){
                System.out.println("Suree:"+liveClass.getContentLink().equalsIgnoreCase("ContentLink"));            }
        }



        video_topic.setText(liveClass.getTopic());
        video_info.setText(liveClass.getContent_Info());
        video_started.setText(liveClass.getDuration());
        video_subject.setText(liveClass.getSubject());

        Handler mainHandler = new Handler();


        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        LoadControl loadControl = new DefaultLoadControl();

        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);

        simpleExoPlayerView =  findViewById(R.id.exoplayer_view);
        simpleExoPlayerView.loadUrl(videoUrl);
        //simpleExoPlayerView.setPlayer(player);

        DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "Exo2"), defaultBandwidthMeter);
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        HlsMediaSource hlsMediaSource = new HlsMediaSource.Factory(dataSourceFactory)
                .setAllowChunklessPreparation(true)
                .createMediaSource(Uri.parse(videoUrl));


        /*player.prepare(hlsMediaSource);
        simpleExoPlayerView.requestFocus();
        player.setPlayWhenReady(true);
        player.addListener(new Player.EventListener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                switch (playbackState){

                    case STATE_BUFFERING:
                        Toast.makeText(GecYouTubeLiveActivity.this, "buffering", Toast.LENGTH_SHORT).show();
                        break;
                    case STATE_READY:
                        Toast.makeText(GecYouTubeLiveActivity.this, "ready", Toast.LENGTH_SHORT).show();

                        break;
                    case STATE_ENDED:
                        Toast.makeText(GecYouTubeLiveActivity.this, "Ended", Toast.LENGTH_SHORT).show();

                        break;
                    case STATE_IDLE:
                        Toast.makeText(GecYouTubeLiveActivity.this, "Ideal", Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        });
*/

  /*      final ImageView fullscreenButton = simpleExoPlayerView.findViewById(R.id.exo_fullscreen_icon);
        fullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fullscreen) {
                    fullscreenButton.setImageDrawable(ContextCompat.getDrawable(GecYouTubeLiveActivity.this, R.drawable.ic_fullscreen_black_24dp));
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    if(getSupportActionBar() != null){
                        getSupportActionBar().show();
                    }
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) simpleExoPlayerView.getLayoutParams();
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    params.height = (int) ( 200 * getApplicationContext().getResources().getDisplayMetrics().density);
                    simpleExoPlayerView.setLayoutParams(params);
                    fullscreen = false;
                }else{
                    fullscreenButton.setImageDrawable(ContextCompat.getDrawable(GecYouTubeLiveActivity.this, R.drawable.ic_fullscreen_exit_black_24dp));
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                            |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                    if(getSupportActionBar() != null){
                        getSupportActionBar().hide();
                    }
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) simpleExoPlayerView.getLayoutParams();
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    simpleExoPlayerView.setLayoutParams(params);
                    fullscreen = true;
                }
            }
        });

*/
    }

    @Override
    protected void onDestroy() {

        player.release();
        player = null;
        super.onDestroy();

    }


    @Override
    protected void onStop() {
        super.onStop();


        pausePlayer();


    }


    private void pausePlayer() {
        player.setPlayWhenReady(false);

    }

    private void startPlayer() {
        player.setPlayWhenReady(true);


    }

//    @Override
//    private void onResume() {
//        super.onResume();
//        startPlayer();
//
//    }


    private void init() {
        database=FirebaseDatabase.getInstance();
        u=new FireBaseUserId();
        messageView=findViewById(R.id.recycler_message);
        txt_message=findViewById(R.id.txt_message);
        btn_send=findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
        messages= new ArrayList<>();
    }


//

    private void logout() {
        SharedPrefManager.getInstance(this).logout();
        startActivity(new Intent(this, LoginActivity.class));
        Objects.requireNonNull(this).finish();
    }

    @Override
    public void onClick(View view) {
        Log.d("tag", "onClick: "+u.getName());

        if (!TextUtils.isEmpty(txt_message.getText().toString())){
            Message message = new Message(txt_message.getText().toString(),u.getName());
            txt_message.setText("");
            messagedb.push().setValue(message);

        }else{
            Toast.makeText(this, "Message cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();

        final FirebaseUser firebaseUser = auth.getCurrentUser();

        u.setEmail(firebaseUser.getEmail());
        u.setUid(firebaseUser.getUid());
        u.setName(UserName);

        database.getReference("Users").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                u=dataSnapshot.getValue(FireBaseUserId.class);
                u.setUid(firebaseUser.getUid());
                u.setName(UserName);
                AllModel.name=u.getName();
                Log.d("tag", "onDataChange: "+AllModel.name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        messagedb=database.getReference("messages");
        messagedb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Message message = dataSnapshot.getValue(Message.class);
                message.setKey(dataSnapshot.getKey());
                messages.add(message);
                displayMessages(messages);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Message message = dataSnapshot.getValue(Message.class);
                message.setKey(dataSnapshot.getKey());
                List<Message> newMessages  = new ArrayList<Message>();
                for (Message m:messages){
                    if (m.getKey().equals(message.getKey())){
                        newMessages.add(message);
                    }else{
                        newMessages.add(m);
                    }
                }
                messages = newMessages;
                displayMessages(messages);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                Message message = dataSnapshot.getValue(Message.class);
                message.setKey(dataSnapshot.getKey());
                List<Message> newMessages = new ArrayList<Message>();

                for (Message m:messages){
                    if (!m.getKey().equals(message.getKey())){
                        newMessages.add(m);
                    }
                }
                messages = newMessages;
                displayMessages(messages);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected  void onResume(){
        super.onResume();
        messages=new ArrayList<>();


    }

    private void displayMessages(List<Message> messages) {
        messageView.setLayoutManager(new LinearLayoutManager(GecYouTubeLiveActivity.this));
        messageAdapter = new MessageAdapter(GecYouTubeLiveActivity.this,messages,messagedb);
        messageView.setAdapter(messageAdapter);
    }
}
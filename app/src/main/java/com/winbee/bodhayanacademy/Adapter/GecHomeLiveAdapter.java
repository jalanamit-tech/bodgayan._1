package com.winbee.bodhayanacademy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.winbee.bodhayanacademy.Activity.GecYouTubeLiveActivity;
import com.winbee.bodhayanacademy.Model.LiveClass;
import com.winbee.bodhayanacademy.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GecHomeLiveAdapter extends RecyclerView.Adapter<GecHomeLiveAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LiveClass> list;

    public GecHomeLiveAdapter(Context context, ArrayList<LiveClass> List){
        this.context = context;
        this.list = List;
    }

    @NonNull
    @Override
    public GecHomeLiveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gec_home_adepter_live,parent, false);
        return  new GecHomeLiveAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GecHomeLiveAdapter.ViewHolder holder, final int position) {
            holder.subjectName.setText(list.get(position).getSubject());
            holder.subjectTopic.setText(list.get(position).getTopic());
            holder.StartingTime.setText(list.get(position).getStart_Time());
            holder.video_started.setText(list.get(position).getCS_type_name());

                holder.branch_live.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if (list.get(position).getCS_type_code().equals(1)) {
                            Bundle bundle = new Bundle();
                            Intent intent = new Intent(context, GecYouTubeLiveActivity.class);
                            bundle.putSerializable("ContentLink", list.get(position));
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                        } else if (list.get(position).getCS_type_code().equals(0)) {
                            Toast.makeText(context, "Class Not Started yet", Toast.LENGTH_SHORT).show();
                        } else if (list.get(position).getCS_type_code().equals(2)) {
                            Toast.makeText(context, "Live Class Ended", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Error Occur,Contact Support", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public int getItemCount() {
        return list==null ? 0 : 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView subjectName,subjectTopic,StartingTime,video_started;
        ImageView branch_image;
        private RelativeLayout branch_live,layout_no_live_class;
        RelativeLayout cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.gec_live_subject);
            subjectTopic = itemView.findViewById(R.id.live_topic);
            StartingTime = itemView.findViewById(R.id.live_started);
            video_started = itemView.findViewById(R.id.video_started);
            cardView = itemView.findViewById(R.id.branch_sem);
            branch_image=itemView.findViewById(R.id.branch_image);
            branch_live=itemView.findViewById(R.id.branch_live);
            layout_no_live_class=itemView.findViewById(R.id.layout_no_live_class);

            branch_live.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    FirebaseLogin();
                }
            });
        }
    }



}


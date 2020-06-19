package com.winbee.bodhayanacademy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.winbee.bodhayanacademy.Activity.GecYouTubeLiveActivity;
import com.winbee.bodhayanacademy.Model.LiveClass;
import com.winbee.bodhayanacademy.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TodayLiveAdapter extends RecyclerView.Adapter<TodayLiveAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LiveClass> list;

    public TodayLiveAdapter(Context context, ArrayList<LiveClass> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TodayLiveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.today_live_class_adapter,parent, false);
        return  new TodayLiveAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodayLiveAdapter.ViewHolder holder, final int position) {
        holder.live_topic.setText(list.get(position).getTopic());
        holder.live_time.setText(list.get(position).getStart_Time());
        holder.live_subject.setText(list.get(position).getSubject());
        holder.live_teacher.setText(list.get(position).getTeacher());


        holder.branch_live.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                Intent intent = new Intent(context, GecYouTubeLiveActivity.class);
                bundle.putSerializable("ContentLink",list.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView live_topic,live_subject,live_teacher,live_time,video_started;
        private RelativeLayout branch_live;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            live_topic = itemView.findViewById(R.id.live_topic);
            live_time = itemView.findViewById(R.id.live_started);
            live_subject = itemView.findViewById(R.id.live_subject);
            live_teacher = itemView.findViewById(R.id.live_teacher);
            video_started = itemView.findViewById(R.id.video_started);
            branch_live = itemView.findViewById(R.id.branch_live);
        }
    }
}


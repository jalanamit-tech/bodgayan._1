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


import com.squareup.picasso.Picasso;
import com.winbee.bodhayanacademy.Activity.ExoPlayer;
import com.winbee.bodhayanacademy.Activity.GecYouTubeLiveActivity;
import com.winbee.bodhayanacademy.Activity.LiveVideoActivity;
import com.winbee.bodhayanacademy.Activity.WebActivity;
import com.winbee.bodhayanacademy.Model.LiveClass;
import com.winbee.bodhayanacademy.R;

import java.util.ArrayList;

public class GecAllLiveClassesAdapter extends RecyclerView.Adapter<GecAllLiveClassesAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LiveClass> list;

    public GecAllLiveClassesAdapter(Context context, ArrayList<LiveClass> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GecAllLiveClassesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gec_all_live_classes,parent, false);
        return  new GecAllLiveClassesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GecAllLiveClassesAdapter.ViewHolder holder, final int position) {
        holder.live_topic.setText(list.get(position).getTopic());
        holder.live_subject.setText(list.get(position).getSubject());
        holder.live_teacher.setText(list.get(position).getTeacher());
        holder.live_time.setText(list.get(position).getStart_Time());
       // holder.video_started.setText(list.get(position).getCS_type_name());

        if(list.get(position).getCS_type_code().equals(1)){
            Picasso.get().load(R.drawable.live).into(holder.view_image);
//            holder.view_image.setImageResource(R.drawable.live);
            holder.branch_live.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (list.get(position).getCS_type_code().equals(1)) {
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(context, ExoPlayer.class);
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
        }else if (list.get(position).getCS_type_code().equals(0)){
            holder.view_image.setImageResource(R.drawable.ic_clock);
        }else if (list.get(position).getCS_type_code().equals(2)){
            holder.view_image.setImageResource(R.drawable.ic_tick);
        }else {
            Toast.makeText(context, "Error Occur,Contact Support", Toast.LENGTH_SHORT).show();
        }
//            holder.branch_live.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (list.get(position).getCS_type_code().equals(1)) {
//                        Bundle bundle = new Bundle();
//                        Intent intent = new Intent(context, ExoPlayer.class);
//                        bundle.putSerializable("ContentLink", list.get(position));
//                        intent.putExtras(bundle);
//                        context.startActivity(intent);
//                    } else if (list.get(position).getCS_type_code().equals(0)) {
//                        Toast.makeText(context, "Class Not Started yet", Toast.LENGTH_SHORT).show();
//                    } else if (list.get(position).getCS_type_code().equals(2)) {
//                        Toast.makeText(context, "Live Class Ended", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(context, "Error Occur,Contact Support", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            });
//        holder.branch_live.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (list.get(position).getCS_type_code().equals(1)) {
//                    Intent intent = new Intent(context, LiveVideoActivity.class);
//                    context.startActivity(intent);
//                } else if (list.get(position).getCS_type_code().equals(0)) {
//                    Toast.makeText(context, "Class Not Started yet", Toast.LENGTH_SHORT).show();
//                } else if (list.get(position).getCS_type_code().equals(2)) {
//                    Toast.makeText(context, "Live Class Ended", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(context, "Error Occur,Contact Support", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
        }

    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView live_topic,live_subject,live_teacher,live_time;
        private RelativeLayout branch_live;
        private ImageView view_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            live_topic = itemView.findViewById(R.id.live_topic);
            live_subject = itemView.findViewById(R.id.live_subject);
            live_teacher = itemView.findViewById(R.id.live_teacher);
            live_time = itemView.findViewById(R.id.live_time);
            view_image = itemView.findViewById(R.id.view_image);
            branch_live = itemView.findViewById(R.id.branch_live);
        }
    }
}

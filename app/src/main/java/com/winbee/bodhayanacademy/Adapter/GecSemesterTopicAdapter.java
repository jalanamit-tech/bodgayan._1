package com.winbee.bodhayanacademy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.winbee.bodhayanacademy.Activity.GecYouTubeActivity;
import com.winbee.bodhayanacademy.Activity.WebActivity;
import com.winbee.bodhayanacademy.Model.UrlName;
import com.winbee.bodhayanacademy.R;

import java.util.List;


public class GecSemesterTopicAdapter extends RecyclerView.Adapter<GecSemesterTopicAdapter.ViewHolder> {
    private Context context;
    private List<UrlName> list;

    public GecSemesterTopicAdapter(Context context, List<UrlName> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gec_semester_topic_adapter,parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        //setting data toAd apter List
        holder.subject.setText(list.get(position).getSubject());
        holder.topic.setText(list.get(position).getTopic());
        holder.faculty_name.setText(list.get(position).getFaculty());
        holder.faculty_designation.setText(list.get(position).getDesignation());
        holder.released_date.setText(list.get(position).getPublished());
        if(list.get(position).getType().equalsIgnoreCase("PDF")){
            holder.document_type.setImageResource(R.drawable.ic_pdf);
            holder.branch_sem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(context, WebActivity.class);
                    bundle.putSerializable("URL",list.get(position));
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
        else if(list.get(position).getType().equalsIgnoreCase("Video")){
           holder.document_type.setImageResource(R.drawable.ic_clapperboard);
            holder.branch_sem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.addCategory(Intent.CATEGORY_BROWSABLE);
                            intent.setData(Uri.parse(list.get(position).getURL()));
                            context.startActivity(intent);
                        }

                  //  Toast.makeText(view.getContext(), "video not supported ,plzz open web broswer", Toast.LENGTH_LONG).show();

            });

        }
        else if(list.get(position).getType().equalsIgnoreCase("PPT")){
            holder.document_type.setImageResource(R.drawable.ic_document);
            holder.branch_sem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(list.get(position).getURL()));
                    context.startActivity(intent);
                }

                //  Toast.makeText(view.getContext(), "video not supported ,plzz open web broswer", Toast.LENGTH_LONG).show();

            });
        }
        else if(list.get(position).getType().equalsIgnoreCase("YouTube")){
            holder.document_type.setImageResource(R.drawable.youtube_logo);
            holder.branch_sem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(context, GecYouTubeActivity.class);
                    bundle.putSerializable("URL",list.get(position));
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }

                //  Toast.makeText(view.getContext(), "video not supported ,plzz open web broswer", Toast.LENGTH_LONG).show();

            });
        }

    }



    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView subject,topic,faculty_name,faculty_designation,released_date,document_type_text;
        ImageView document_type;
        private RelativeLayout branch_sem,layout_onclick;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.gec_subject);
            topic = itemView.findViewById(R.id.gec_topic);
            faculty_name = itemView.findViewById(R.id.faculty_name);
            faculty_designation = itemView.findViewById(R.id.faculty_designation);
            released_date = itemView.findViewById(R.id.released_date);
            document_type = itemView.findViewById(R.id.document_type);
             branch_sem = itemView.findViewById(R.id.branch_sem_topic);
            layout_onclick = itemView.findViewById(R.id.layout_onclick);

        }

    }

}




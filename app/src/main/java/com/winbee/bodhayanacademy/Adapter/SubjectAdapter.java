package com.winbee.bodhayanacademy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.winbee.bodhayanacademy.Activity.OnlineTestActivity;
import com.winbee.bodhayanacademy.Model.SectionDetailsDataModel;
import com.winbee.bodhayanacademy.R;
import com.winbee.bodhayanacademy.Utils.OnlineTestData;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.CustomViewHolder> {
    private Context context;
    private List<SectionDetailsDataModel> sectionDetailsDataModelList;

    public SubjectAdapter(Context context, List<SectionDetailsDataModel> sectionDetailsDataModelList) {
        this.context=context;
        this.sectionDetailsDataModelList = sectionDetailsDataModelList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_online_subject,parent,false);
        return new CustomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewHolder, int position) {
        final SectionDetailsDataModel sectionDetailsDataModel = sectionDetailsDataModelList.get(position);
        viewHolder.online_subjectname.setText(sectionDetailsDataModel.getBucketName());
        viewHolder.branch_live1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnlineTestData.CoachingID=sectionDetailsDataModel.getCoachingID();
                OnlineTestData.bucketID=sectionDetailsDataModel.getBucketID();
                OnlineTestData.bucketName=sectionDetailsDataModel.getBucketName();
                OnlineTestData.bucketInfo=sectionDetailsDataModel.getBucketInfo();
                OnlineTestData.logData=sectionDetailsDataModel.getLogData();
                OnlineTestData.status=sectionDetailsDataModel.getStatus();
                OnlineTestData.totalTest=sectionDetailsDataModel.getTotalTest();
                Intent intent=new Intent(context, OnlineTestActivity.class);
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return sectionDetailsDataModelList.size();
    }
    static class CustomViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout branch_live1;
        ImageView live_image;
        TextView online_subjectname;
        CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            branch_live1=itemView.findViewById(R.id.branch_live1);
            live_image=itemView.findViewById(R.id.live_image);
            online_subjectname=itemView.findViewById(R.id.online_subjectname);
        }
    }
}

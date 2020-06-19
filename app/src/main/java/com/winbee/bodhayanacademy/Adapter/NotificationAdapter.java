package com.winbee.bodhayanacademy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.winbee.bodhayanacademy.Activity.GecSemesterTopicActivity;
import com.winbee.bodhayanacademy.Model.NotificationModel;
import com.winbee.bodhayanacademy.Model.SemesterName;
import com.winbee.bodhayanacademy.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private Context context;
    private List<NotificationModel> list;

    public NotificationAdapter(Context context, List<NotificationModel> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_adapter,parent, false);
        return  new NotificationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, final int position) {
        //setting data toAd apter List
        holder.notification_sender.setText(list.get(position).getSender());
        holder.notification_message.setText(Html.fromHtml(list.get(position).getMessage()));
        holder.notification_date.setText(String.valueOf(list.get(position).getLogDetails()));
    }

    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView notification_sender,notification_message,notification_date;
        private RelativeLayout branch_sem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notification_sender = itemView.findViewById(R.id.notification_sender);
            notification_message = itemView.findViewById(R.id.notification_message);
            notification_date = itemView.findViewById(R.id.notification_date);
            branch_sem = itemView.findViewById(R.id.branch_sem);
        }
    }
}

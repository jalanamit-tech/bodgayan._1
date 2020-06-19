package com.winbee.bodhayanacademy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.winbee.bodhayanacademy.Activity.InstructionsActivity;
import com.winbee.bodhayanacademy.Model.MyListData;
import com.winbee.bodhayanacademy.R;

public class MySubjectAdapter extends RecyclerView.Adapter<MySubjectAdapter.ViewHolder>{
    private MyListData[] listdata;
    private Context context;
    public MySubjectAdapter(MyListData[] listdata) {
        this.listdata = listdata;
        this.context=context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.online_subject_adapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);  
        return viewHolder;  
    }  
  
    @Override  
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(listdata[position].getDescription());  
        holder.imageView.setImageResource(listdata[position].getImgId());  
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {  
            @Override  
            public void onClick(View view) {
                Intent intent = new Intent(context, InstructionsActivity.class);
                context.startActivity(intent);
            }  
        });  
    }  
  
  
    @Override  
    public int getItemCount() {  
        return listdata.length;  
    }  
  
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {  
            super(itemView);  
            this.imageView = (ImageView) itemView.findViewById(R.id.live_image);
            this.textView = (TextView) itemView.findViewById(R.id.online_subjectname);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.branch_sem);
        }  
    }  
}  
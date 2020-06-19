package com.winbee.bodhayanacademy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.winbee.bodhayanacademy.Activity.GecBranchSemesterActivity;
import com.winbee.bodhayanacademy.Activity.PermiumSellActivity;
import com.winbee.bodhayanacademy.Model.BranchName;
import com.winbee.bodhayanacademy.R;

import java.util.ArrayList;

public class AllCourseAdapter extends RecyclerView.Adapter<AllCourseAdapter.ViewHolder> {
    private Context context;
    private ArrayList<BranchName> list1;

    public AllCourseAdapter(Context context, ArrayList<BranchName> horizontalList){
        this.context = context;
        this.list1 = horizontalList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gec_home_all_course_adapter,parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
         if (list1.get(position).getVersion().equalsIgnoreCase("1")) {
             if (list1.get(position).getPaid()==0) {
                 holder.purchase_btn.setVisibility(View.VISIBLE);
                 holder.free_btn.setVisibility(View.GONE);
                 holder.branchname.setText(list1.get(position).getBucket_Name());
                 Picasso.get().load(list1.get(position).getBucket_Image()).into(holder.branch_image);
                 holder.purchase_btn.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Bundle bundle = new Bundle();
                         Intent intent = new Intent(context, PermiumSellActivity.class);
                         bundle.putSerializable("branch_name", list1.get(position));
                         intent.putExtras(bundle);
                         context.startActivity(intent);
                     }
                 });
             }else{
                 holder.purchase_btn.setVisibility(View.GONE);
                  holder.purchased_btn.setVisibility(View.VISIBLE);
                 holder.branchname.setText(list1.get(position).getBucket_Name());
                 Picasso.get().load(list1.get(position).getBucket_Image()).into(holder.branch_image);
                 holder.branch_name1.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Bundle bundle = new Bundle();
                         Intent intent = new Intent(context, GecBranchSemesterActivity.class);
                         bundle.putSerializable("branch_name", list1.get(position));
                         intent.putExtras(bundle);
                         context.startActivity(intent);
                     }
                 });


             }
         }else{
             holder.purchase_btn.setVisibility(View.GONE);
             holder.free_btn.setVisibility(View.VISIBLE);
             holder.branchname.setText(list1.get(position).getBucket_Name());
             Picasso.get().load(list1.get(position).getBucket_Image()).into(holder.branch_image);
             holder.branch_name1.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Bundle bundle = new Bundle();
                     Intent intent = new Intent(context, GecBranchSemesterActivity.class);
                     bundle.putSerializable("branch_name", list1.get(position));
                     intent.putExtras(bundle);
                     context.startActivity(intent);
                 }
             });

         }

    }

    @Override
    public int getItemCount() {
        return list1==null ? 0 : list1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView branchname;
        private ImageView branch_image;

        private RelativeLayout branch_name1;
        private Button purchase_btn,free_btn,purchased_btn;
        RelativeLayout cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            branchname = itemView.findViewById(R.id.gec_branchname);
            cardView = itemView.findViewById(R.id.branch_sem);
            branch_image=itemView.findViewById(R.id.branch_image);
            purchased_btn=itemView.findViewById(R.id.purchased_btn);
            branch_name1=itemView.findViewById(R.id.branch_name1);
            purchase_btn=itemView.findViewById(R.id.purchase_btn);
            free_btn=itemView.findViewById(R.id.free_btn);
        }
    }
}


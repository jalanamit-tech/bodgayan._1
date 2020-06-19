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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.winbee.bodhayanacademy.Activity.GecBranchSemesterActivity;
import com.winbee.bodhayanacademy.Activity.MyCourseSubjectActivity;
import com.winbee.bodhayanacademy.Activity.PermiumSellActivity;
import com.winbee.bodhayanacademy.Model.BranchName;
import com.winbee.bodhayanacademy.Model.CourseDatum;
import com.winbee.bodhayanacademy.Model.PurchasedDataModel;
import com.winbee.bodhayanacademy.Model.PurchasedMainModel;
import com.winbee.bodhayanacademy.R;

import java.util.ArrayList;
import java.util.List;

public class GecHomeAdapter extends RecyclerView.Adapter<GecHomeAdapter.ViewHolder> {
    private Context context;
    private List<CourseDatum> courseDatumList;

    public GecHomeAdapter(Context context, List<CourseDatum> courseDatumList){
        this.context = context;
        this.courseDatumList = courseDatumList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gec_home_adapter,parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //setting data toAd apter List

            holder.branchname.setText(courseDatumList.get(position).getBucketName());

            Picasso.get().load(courseDatumList.get(position).getBucket_Image()).into(holder.branch_image);
            holder.branch_name1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(context, MyCourseSubjectActivity.class);
                    bundle.putSerializable("my_course", courseDatumList.get(position));
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

        }


    @Override
    public int getItemCount() {
        return courseDatumList==null ? 0 : courseDatumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView branchname;
         private ImageView branch_image;
       private RelativeLayout branch_name1;
        RelativeLayout cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            branchname = itemView.findViewById(R.id.gec_branchname);
            cardView = itemView.findViewById(R.id.branch_sem);
            branch_image=itemView.findViewById(R.id.branch_image);
            branch_name1=itemView.findViewById(R.id.branch_name1);
        }
    }
}


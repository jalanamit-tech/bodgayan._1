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
import com.winbee.bodhayanacademy.Activity.GecHomeActivity;
import com.winbee.bodhayanacademy.Activity.MyCourseSubjectActivity;
import com.winbee.bodhayanacademy.Model.CourseDatum;
import com.winbee.bodhayanacademy.Model.ViewResult;
import com.winbee.bodhayanacademy.R;
import com.winbee.bodhayanacademy.Utils.OnlineTestData;

import java.util.List;

public class ViewResultAdapter extends RecyclerView.Adapter<ViewResultAdapter.ViewHolder> {
    private Context context;
    private List<ViewResult> courseDatumList;

    public ViewResultAdapter(Context context, List<ViewResult> courseDatumList){
        this.context = context;
        this.courseDatumList = courseDatumList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_result_adapter,parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_paper_name.setText(OnlineTestData.PaperID);
        holder.tv_section_name.setText(OnlineTestData.PaperID);
        holder.tv_total_question.setText(courseDatumList.get(position).getTotalQuestion());
        holder.tv_total_attempt.setText(courseDatumList.get(position).getTotalQuestion());
        holder.tv_total_correct.setText(courseDatumList.get(position).getCorrect());
        holder.tv_total_review.setText(courseDatumList.get(position).getReview());
        holder.tv_total_wrong.setText(courseDatumList.get(position).getWrong());
        holder.tv_total_marks.setText(courseDatumList.get(position).getTotalMarks());
        holder.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GecHomeActivity.class);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return courseDatumList==null ? 0 : courseDatumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_paper_name,tv_section_name,tv_total_question,tv_total_attempt,tv_total_correct,tv_total_review,tv_total_wrong,tv_total_marks;
        private Button backbtn;
        private RelativeLayout branch_name1;
        RelativeLayout cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_paper_name = itemView.findViewById(R.id.gec_branchname);
            tv_section_name = itemView.findViewById(R.id.total_video);
            tv_total_question = itemView.findViewById(R.id.total_document);
            tv_total_attempt = itemView.findViewById(R.id.tv_total_attempt);
            tv_total_review = itemView.findViewById(R.id.tv_total_review);
            tv_total_wrong = itemView.findViewById(R.id.tv_total_wrong);
            tv_total_correct = itemView.findViewById(R.id.tv_total_correct);
            tv_total_marks = itemView.findViewById(R.id.tv_total_marks);
            backbtn=itemView.findViewById(R.id.backbtn);
        }
    }
}



package com.winbee.bodhayanacademy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.winbee.bodhayanacademy.Model.SubmittedDatum;
import com.winbee.bodhayanacademy.R;

import java.util.List;

public class SubmittedAdapter extends RecyclerView.Adapter<SubmittedAdapter.ViewHolder> {
    private Context context;
    private List<SubmittedDatum> assignmentDatumList;

    public SubmittedAdapter(Context context, List<SubmittedDatum> assignmentDatumList){
        this.context = context;
        this.assignmentDatumList = assignmentDatumList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.submitted_assignment_adapter,parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final SubmittedDatum submittedAdapter = assignmentDatumList.get(position);
       holder.assignment_topic.setText(assignmentDatumList.get(position).getSubject());
       holder.assignment_teacher.setText(assignmentDatumList.get(position).getTopic());
    holder.assignment_dead_line.setText(assignmentDatumList.get(position).getDescription());
    holder.assignment_dead_line.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse(assignmentDatumList.get(position).getTeacherContent()));
            context.startActivity(intent);
        }
    });
        holder.branch_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(assignmentDatumList.get(position).getStudentContent()));
                context.startActivity(intent);
            }

            //  Toast.makeText(view.getContext(), "video not supported ,plzz open web broswer", Toast.LENGTH_LONG).show();

        });

    }


    @Override
    public int getItemCount() {
        return assignmentDatumList==null ? 0 : assignmentDatumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView assignment_subject,assignment_topic,assignment_teacher,assignment_dead_line;
        private RelativeLayout branch_live;
        RelativeLayout cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            assignment_subject = itemView.findViewById(R.id.assignment_subject);
            assignment_topic = itemView.findViewById(R.id.assignment_topic);
            assignment_teacher = itemView.findViewById(R.id.assignment_teacher);
            assignment_dead_line = itemView.findViewById(R.id.assignment_dead_line);
            cardView = itemView.findViewById(R.id.branch_sem);
            branch_live=itemView.findViewById(R.id.branch_live);
        }
    }
}


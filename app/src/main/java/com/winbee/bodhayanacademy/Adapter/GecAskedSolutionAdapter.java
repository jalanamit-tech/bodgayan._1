package com.winbee.bodhayanacademy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.winbee.bodhayanacademy.Model.UrlSolution;
import com.winbee.bodhayanacademy.R;

import java.util.ArrayList;

public class GecAskedSolutionAdapter  extends RecyclerView.Adapter<GecAskedSolutionAdapter.ViewHolder> {
    private Context context;
    private ArrayList<UrlSolution> list;

    public GecAskedSolutionAdapter(Context context, ArrayList<UrlSolution> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GecAskedSolutionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.asksolutionadapter,parent, false);
        return  new GecAskedSolutionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GecAskedSolutionAdapter.ViewHolder holder, final int position) {
        if (list.get(position).getType().equalsIgnoreCase("Asked")) {
            holder.text_question.setText(list.get(position).getQuestion());
            holder.text_date.setText(list.get(position).getDATE());
            holder.text_user.setText(list.get(position).getUser());

        }else if(list.get(position).getType().equalsIgnoreCase("Solution")){
            holder.text_solution.setText(list.get(position).getQuestion());
            holder.text_date_solution.setText(list.get(position).getDATE());
            holder.text_user_solution.setText(list.get(position).getUser());

        }



    }


    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text_question,text_user,text_date,text_solution,text_user_solution,text_date_solution;
        private RelativeLayout branch_live;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_question = itemView.findViewById(R.id.text_question);
            text_user = itemView.findViewById(R.id.text_user);
            text_date = itemView.findViewById(R.id.text_date);
            text_solution = itemView.findViewById(R.id.text_solution);
            text_user_solution = itemView.findViewById(R.id.text_user_solution);
            text_date_solution = itemView.findViewById(R.id.text_date_solution);
            branch_live = itemView.findViewById(R.id.branch_live);
        }
    }
}

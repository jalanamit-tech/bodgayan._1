package com.winbee.bodhayanacademy.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.winbee.bodhayanacademy.Model.AllModel;
import com.winbee.bodhayanacademy.Model.Message;
import com.winbee.bodhayanacademy.R;


import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageAdapterViewHolder> {
    private static final String TAG = "MessageAdapter";

   private Context context;
   private List<Message> messages;
   private DatabaseReference messageDb;

    public MessageAdapter(Context context, List<Message> messages, DatabaseReference messageDb)
    {
        this.context=context;
        this.messages=messages;
        this.messageDb=messageDb;
    }

    @NonNull
    @Override
    public MessageAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return  new MessageAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageAdapterViewHolder holder, int position)
    {
        Log.d(TAG, "onBindViewHolder: "+messages);
            Message message= messages.get(position);
        if (message.getName().equals(AllModel.name)){
            holder.txtView.setText("you :"+ message.getMessage());
            holder.txtView.setGravity(Gravity.START);
            holder.ll.setBackgroundColor(Color.parseColor("#BD5252"));
        }else{
            holder.txtView.setText(message.getName()+":"+ message.getMessage());
            holder.btn_delete.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageAdapterViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtView;
        ImageButton btn_delete;
        LinearLayout ll;

        public MessageAdapterViewHolder( View itemView) {
            super(itemView);

            txtView =itemView.findViewById(R.id.txtView);
            btn_delete=itemView.findViewById(R.id.btn_delete);
            ll=itemView.findViewById(R.id.layout_message);
            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    messageDb.child(messages.get(getAdapterPosition()).getKey()).removeValue();
                }
            });
        }
    }
}

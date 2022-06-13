package com.example.ucasproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ucasproject.Models.Invoice;
import com.example.ucasproject.Models.Notifications;
import com.example.ucasproject.R;
import com.example.ucasproject.listener.OnRVItemClickListener1;
import java.util.ArrayList;

public class Notifications_Adapter extends RecyclerView.Adapter<Notifications_Adapter.NotificationsHolder> {
    ArrayList<Notifications> notificationsList;
    OnRVItemClickListener1 listener;

    public Notifications_Adapter(ArrayList<Notifications> notificationsList, OnRVItemClickListener1 listener) {
        this.notificationsList = notificationsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Notifications_Adapter.NotificationsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_notifications,parent,false);
        return new Notifications_Adapter.NotificationsHolder(v);       }

    @Override
    public void onBindViewHolder(@NonNull Notifications_Adapter.NotificationsHolder holder, int position) {
        Notifications e = notificationsList.get(position);
        holder.c = e;
        holder.title.setText(e.getTitle());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class NotificationsHolder extends RecyclerView.ViewHolder {
        Notifications c;
        TextView title;

        public NotificationsHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notification_msg_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(c);
                }
            });
        }
    }
}

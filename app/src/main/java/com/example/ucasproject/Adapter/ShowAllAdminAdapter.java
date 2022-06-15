package com.example.ucasproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ucasproject.Models.Admin;
import com.example.ucasproject.Models.Invoice;
import com.example.ucasproject.R;
import com.example.ucasproject.listener.OnRVItemClickListener2;

import java.util.ArrayList;

public class ShowAllAdminAdapter extends RecyclerView.Adapter<ShowAllAdminAdapter.ShowAllAdminHolder> {
    ArrayList<Admin> adminArrayList;
    OnRVItemClickListener2 listener;

    public ShowAllAdminAdapter(ArrayList<Admin> adminArrayList, OnRVItemClickListener2 listener) {
        this.adminArrayList = adminArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ShowAllAdminAdapter.ShowAllAdminHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin,parent,false);
        return new ShowAllAdminAdapter.ShowAllAdminHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowAllAdminHolder holder, int position) {
        Admin e = adminArrayList.get(position);
        holder.c = e;
        holder.PhotoAdmin.setImageResource(Integer.parseInt(e.getAdminImgUrl()));
        holder.AdminName.setText(e.getAdminFirstName());
        holder.AdminAddress.setText(e.getAdminAddress());
        holder.AdminPhone.setText(e.getAdminPhoneNumber());
    }


    @Override
    public int getItemCount() {
        return adminArrayList.size();
    }

    public class ShowAllAdminHolder extends RecyclerView.ViewHolder {
        Admin c;
        ImageView PhotoAdmin;
        TextView AdminName,AdminPhone,AdminAddress;

        public ShowAllAdminHolder(@NonNull View itemView) {
            super(itemView);
            PhotoAdmin = itemView.findViewById(R.id.image_admin);
            AdminName = itemView.findViewById(R.id.name_admin);
            AdminPhone = itemView.findViewById(R.id.phone_admin);
            AdminAddress = itemView.findViewById(R.id.address_admin);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(c);
                }
            });
        }
    }
}

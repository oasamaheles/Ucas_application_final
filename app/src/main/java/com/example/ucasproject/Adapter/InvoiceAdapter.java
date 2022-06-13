package com.example.ucasproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ucasproject.Models.Invoice;
import com.example.ucasproject.R;
import com.example.ucasproject.listener.OnRVItemClickListener;

import java.util.ArrayList;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceHolder>  {
    ArrayList<Invoice> invoicesList;
    OnRVItemClickListener listener;

    public InvoiceAdapter(ArrayList<Invoice> invoicesList, OnRVItemClickListener listener) {
        this.invoicesList = invoicesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public InvoiceAdapter.InvoiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.invouce,parent,false);
        return new InvoiceAdapter.InvoiceHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceAdapter.InvoiceHolder holder, int position) {
        Invoice e = invoicesList.get(position);
        holder.c = e;
        holder.photo_user.setImageResource(Integer.parseInt(e.getImage_user()));
        holder.numId.setText(e.getNumId());
        holder.date.setText(e.getDate());
        holder.typePayment.setText(e.getTypePayment());
    }

    @Override
    public int getItemCount() {
        return invoicesList.size();
    }


    public class InvoiceHolder extends RecyclerView.ViewHolder {
        Invoice c;
        ImageView photo_user;
        TextView numId,date,typePayment;

        public InvoiceHolder(@NonNull View itemView) {
            super(itemView);
            photo_user = itemView.findViewById(R.id.image_user);
            date = itemView.findViewById(R.id.date_invouce);
            typePayment = itemView.findViewById(R.id.typePayment_invouce);
            numId = itemView.findViewById(R.id.numId_invouce);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(c);
                }
            });
        }
    }
}

package com.example.ucasproject.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ucasproject.Add_user_admin.AddNewAdminActivity;
import com.example.ucasproject.Add_user_admin.AddNewUserActivity;
import com.example.ucasproject.IssuingActivity;
import com.example.ucasproject.R;

public class Home_Fr extends Fragment {
    CardView cardAddAdmin , cardAddUser , cardAddBill;

    public Home_Fr() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home_, container, false);
        cardAddAdmin = rootView. findViewById(R.id.cardAddAddmin);
        cardAddUser =  rootView.findViewById(R.id.cardAddCustomer);
        cardAddBill =  rootView.findViewById(R.id.cardAddBill);


        cardAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddNewUserActivity.class);
                startActivity(intent);
            }
        });

        cardAddAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddNewAdminActivity.class);
                startActivity(intent);
            }
        });

        cardAddBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), IssuingActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }


}
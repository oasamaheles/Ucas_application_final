package com.example.ucasproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ucasproject.ExampleDialog;
import com.example.ucasproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Messages_Fr extends Fragment implements ExampleDialog.ExampleDialogListener {
    FloatingActionButton floatingActionButton;

    public Messages_Fr() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_messages_, container, false);

        floatingActionButton = rootView. findViewById(R.id.floatingActionButton);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });


        return rootView;
    }

    public void openDialog() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getParentFragmentManager(), "example dialog");
    }

//    @Override
//    public void applyTexts(String username, String password) {
//        textViewUsername.setText(username);
//        textViewPassword.setText(password);
//    }

    @Override
    public void applyTexts(String notifications) {

    }
}
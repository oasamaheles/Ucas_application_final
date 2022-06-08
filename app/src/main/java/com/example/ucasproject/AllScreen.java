package com.example.ucasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.example.ucasproject.ui.Calculator_Fr;
import com.example.ucasproject.ui.Home_Fr;
import com.example.ucasproject.ui.Messages_Fr;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AllScreen  extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_screen);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View view, MotionEvent motionEvent) {
                return false;
            }
        });


    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new Home_Fr()).commit();
                return true;

            case R.id.navigation_calculator:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new Calculator_Fr()).commit();
                return true;

            case R.id.navigation_messages:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new Messages_Fr()).commit();
                return true;
        }
        return false;
    }
}
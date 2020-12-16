package com.leydere.findtoolsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Button button_find_nav, button_add_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO need to add find by view id's
        button_find_nav = findViewById(R.id.button_find_nav);
        button_add_nav = findViewById(R.id.button_add_nav);

        //code to support override of bottom nav animation
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        //supports correct icon being highlighted
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.ic_home:
                        break;

                    case R.id.ic_search:
                        Intent intentFind = new Intent(MainActivity.this, FindToolActivity.class);
                        startActivity(intentFind);
                        break;

                    case R.id.ic_add:
                        Intent intentAdd = new Intent(MainActivity.this, AddToolActivity.class);
                        startActivity(intentAdd);
                        break;
                }


                return false;
            }
        });

        button_find_nav.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               Intent intentFind = new Intent(MainActivity.this, FindToolActivity.class);
               startActivity(intentFind);
           }
        });

        button_add_nav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(MainActivity.this, AddToolActivity.class);
                startActivity(intentAdd);
            }
        });

    }
}
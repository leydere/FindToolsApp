package com.leydere.findtoolsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FindToolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_tool);

        TextView title = (TextView) findViewById(R.id.activityFindTitle);
        title.setText("This is the Find Tool activity.");

        //code to support override of bottom nav animation
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        //supports correct icon being highlighted
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.ic_home:
                        Intent intentHome = new Intent(FindToolActivity.this, MainActivity.class);
                        startActivity(intentHome);
                        break;

                    case R.id.ic_search:
                        break;

                    case R.id.ic_add:
                        Intent intentAdd = new Intent(FindToolActivity.this, AddToolActivity.class);
                        startActivity(intentAdd);
                        break;
                }


                return false;
            }
        });
    }
}

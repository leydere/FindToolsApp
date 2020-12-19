package com.leydere.findtoolsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ToolDetailActivity extends AppCompatActivity {

ImageButton button_close_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_detail);

        button_close_detail = findViewById(R.id.button_close_detail);



        button_close_detail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentFind = new Intent(ToolDetailActivity.this, FindToolActivity.class);
                startActivity(intentFind);
            }
        });

    }
}
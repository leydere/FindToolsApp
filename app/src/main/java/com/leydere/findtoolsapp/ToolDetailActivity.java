package com.leydere.findtoolsapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static java.lang.Integer.parseInt;

public class ToolDetailActivity extends AppCompatActivity {

ImageButton button_close_detail, button_checkout;
int clickedToolId;
ToolModel clickedTool;
DatabaseHelper databaseHelper;
TextView toolNameTextHere, toolLocationTextHere, toolSubLocationTextHere, toolStatusTextHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_detail);

        button_close_detail = findViewById(R.id.button_close_detail);
        button_checkout = findViewById(R.id.button_checkout);
        toolNameTextHere = findViewById(R.id.toolNameTextHere);
        toolLocationTextHere = findViewById(R.id.toolLocationTextHere);
        toolSubLocationTextHere = findViewById(R.id.toolSubLocationTextHere);
        toolStatusTextHere = findViewById(R.id.toolStatusTextHere);
        databaseHelper = new DatabaseHelper(ToolDetailActivity.this);

        try {
            clickedToolId = getIntent().getIntExtra("CLICKED_TOOL_ID", -1);
            clickedTool = databaseHelper.getRequestedTool(clickedToolId);
            toolNameTextHere.setText(clickedTool.getToolName());
            toolLocationTextHere.setText(clickedTool.getLocation());
            toolSubLocationTextHere.setText(clickedTool.getSubLocation());
            //boolean isCheckedOut = cursor.getInt(5) == 1 ? true: false;
            toolStatusTextHere.setText(clickedTool.getIsCheckedOut() == true ? "Checked out" : "Checked in");
        } catch (Exception e) {
            Toast.makeText(ToolDetailActivity.this, "tool detail failed to load", Toast.LENGTH_SHORT).show();
        }



        button_close_detail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentFind = new Intent(ToolDetailActivity.this, FindToolActivity.class);
                startActivity(intentFind);

                //TODO maintain state of FindToolActivity for when navigated back to
            }
        });

        button_checkout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                //TODO functionality where item is checkedout/checkedin toggle when clicked
                //TODO refresh display once checkout status is displayed
            }
        });










    }
}
package com.leydere.findtoolsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class ReturnToolPopUpWindow extends AppCompatActivity {

    ImageButton button_close_detail, button_checkout;
    int clickedToolId;
    ToolModel clickedTool;
    DatabaseHelper databaseHelper;
    TextView toolNameTextHere, toolLocationTextHere, toolSubLocationTextHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_tool_pop_up_window);




        button_close_detail = findViewById(R.id.button_close_detail);
        button_checkout = findViewById(R.id.button_checkout);
        toolNameTextHere = findViewById(R.id.toolNameTextHere);
        toolLocationTextHere = findViewById(R.id.toolLocationTextHere);
        toolSubLocationTextHere = findViewById(R.id.toolSubLocationTextHere);
        databaseHelper = new DatabaseHelper(ReturnToolPopUpWindow.this);

        try {
            clickedToolId = getIntent().getIntExtra("CLICKED_TOOL_ID", -1);
            clickedTool = databaseHelper.getRequestedTool(clickedToolId);
            toolNameTextHere.setText(clickedTool.getToolName());
            toolLocationTextHere.setText(clickedTool.getLocation());
            toolSubLocationTextHere.setText(clickedTool.getSubLocation());
        } catch (Exception e) {
            Toast.makeText(ReturnToolPopUpWindow.this, "tool detail failed to load", Toast.LENGTH_SHORT).show();
        }



        button_close_detail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentFind = new Intent(ReturnToolPopUpWindow.this, WorkbenchActivity.class);
                startActivity(intentFind);
            }
        });

        button_checkout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                databaseHelper.toggleCheckedOutStatus(clickedToolId, clickedTool.getIsCheckedOut());
                Intent intentFind = new Intent(ReturnToolPopUpWindow.this, WorkbenchActivity.class);
                startActivity(intentFind);
            }
        });


    }
}
package com.leydere.findtoolsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class ReturnToolPopUpWindow extends AppCompatActivity {

    ImageButton button_close_detail, button_checkout;
    int clickedToolId;
    ToolModel clickedTool;
    DatabaseHelper databaseHelper;
    TextView toolNameTextHere, toolLocationTextHere, toolSubLocationTextHere, toolStatusTextHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_tool_pop_up_window);

        button_close_detail = findViewById(R.id.button_close_detail);
        button_checkout = findViewById(R.id.button_checkout);
        toolNameTextHere = findViewById(R.id.toolNameTextHere);
        toolLocationTextHere = findViewById(R.id.toolLocationTextHere);
        toolSubLocationTextHere = findViewById(R.id.toolSubLocationTextHere);
        toolStatusTextHere = findViewById(R.id.toolStatusTextHere);

        clickedToolId = getIntent().getIntExtra("CLICKED_TOOL_ID", -1);
        clickedTool = databaseHelper.getRequestedTool(clickedToolId);


    }
}
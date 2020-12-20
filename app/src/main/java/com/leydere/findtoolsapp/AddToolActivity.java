package com.leydere.findtoolsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class AddToolActivity extends AppCompatActivity {

    Button button_add, button_camera;
    EditText editTextToolName, editTextLocation, editTextSubLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tool);

        button_add = findViewById(R.id.button_add);
        button_camera = findViewById(R.id.button_camera);
        editTextToolName = findViewById(R.id.editTextToolName);
        editTextLocation = findViewById(R.id.editTextLocaton);
        editTextSubLocation = findViewById(R.id.editTextSubLocation);

        button_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                ToolModel toolModel;
                try{
                    toolModel = new ToolModel(editTextToolName.getText().toString(), editTextLocation.getText().toString(), editTextSubLocation.getText().toString(), "dummyPath", false);
                }
                catch (Exception e) {
                    Toast.makeText(AddToolActivity.this, "input error", Toast.LENGTH_SHORT).show();
                    toolModel = new ToolModel("error", "error", "error", "error", false);
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(AddToolActivity.this);
                boolean success = databaseHelper.addRecord(toolModel);

                if (success == true) {
                    Toast.makeText(AddToolActivity.this, "record added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddToolActivity.this, "record added failure", Toast.LENGTH_SHORT).show();
                }


                editTextToolName.setText("");
                editTextLocation.setText("");
                editTextSubLocation.setText("");
                findViewById(R.id.addLinearLayout).requestFocus();
            }
        });



        button_camera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(AddToolActivity.this, "camera button", Toast.LENGTH_SHORT).show();

            }
        });


        //region Bottom Nav Bar and Support
        //code to support override of bottom nav animation
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        //supports correct icon being highlighted
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.ic_home:
                        Intent intentHome = new Intent(AddToolActivity.this, MainActivity.class);
                        startActivity(intentHome);
                        break;

                    case R.id.ic_search:
                        Intent intentFind = new Intent(AddToolActivity.this, FindToolActivity.class);
                        startActivity(intentFind);
                        break;

                    case R.id.ic_add:
                        break;
                    case R.id.ic_workbench:
                        Intent intentWorkbench = new Intent(AddToolActivity.this, WorkbenchActivity.class);
                        startActivity(intentWorkbench);
                        break;
                }


                return false;
            }
        });
        //endregion

    }

}

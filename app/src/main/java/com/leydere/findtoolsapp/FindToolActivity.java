package com.leydere.findtoolsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class FindToolActivity extends AppCompatActivity {

    EditText editTextFindTool;
    Button button_find;
    ListView find_tool_listview;
    ArrayAdapter toolArrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_tool);

        editTextFindTool = findViewById(R.id.editTextFindTool);
        button_find = findViewById(R.id.button_find);
        find_tool_listview = findViewById(R.id.find_tool_listview);

        //code to support override of bottom nav animation
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        //supports correct icon being highlighted
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        button_find.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String searchQuery = editTextFindTool.getText().toString();
                //Toast.makeText(FindToolActivity.this, "SEARCH: " + searchQuery, Toast.LENGTH_SHORT).show();

                DatabaseHelper databaseHelper = new DatabaseHelper(FindToolActivity.this);
                List<ToolModel> resultsFound = databaseHelper.getSearchResults(searchQuery);
                //Toast.makeText(FindToolActivity.this, resultsFound.toString(), Toast.LENGTH_LONG).show();

                //TODO clean up search results
                toolArrayAdapter = new ArrayAdapter<ToolModel>(FindToolActivity.this, android.R.layout.simple_list_item_1, resultsFound);
                find_tool_listview.setAdapter(toolArrayAdapter);
            }
        });

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

    // create method that displays results found from upper method

}

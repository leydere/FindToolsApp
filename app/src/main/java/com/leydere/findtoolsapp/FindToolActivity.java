package com.leydere.findtoolsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_tool);

        editTextFindTool = findViewById(R.id.editTextFindTool);
        button_find = findViewById(R.id.button_find);
        find_tool_listview = findViewById(R.id.find_tool_listview);
        databaseHelper = new DatabaseHelper(FindToolActivity.this);



        button_find.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String searchQuery = editTextFindTool.getText().toString();

                List<String> resultsFound =databaseHelper.nameSearchResults(searchQuery);
                ArrayAdapter namesArrayAdapter = new ArrayAdapter<String>(FindToolActivity.this, android.R.layout.simple_list_item_1, resultsFound);
                find_tool_listview.setAdapter(namesArrayAdapter);

                /*
                //could bypass resultsFound by calling directly in array adapter below
                //keeping for now so I can better track the flow
                List<ToolModel> resultsFound = databaseHelper.getSearchResults(searchQuery);
                //TODO clean up search results
                toolArrayAdapter = new ArrayAdapter<ToolModel>(FindToolActivity.this, android.R.layout.simple_list_item_1, resultsFound);
                find_tool_listview.setAdapter(toolArrayAdapter);

                 */

            }
        });

        //function to be used to navigate to tool details
        find_tool_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ToolModel clickedTool = (ToolModel) parent.getItemAtPosition(position);
                //int clickedToolId = clickedTool.getId();
                //TODO get toolID, navigate to new activity, display details in details activity
                Toast.makeText(FindToolActivity.this, "you clicked a list item", Toast.LENGTH_SHORT).show();
            }
        });


        //region Bottom Nav Bar and Support
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
        //endregion
    }

    // create method that displays results found from upper method

}

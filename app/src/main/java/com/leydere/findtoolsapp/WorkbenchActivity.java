package com.leydere.findtoolsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class WorkbenchActivity extends AppCompatActivity {

    ListView workbench_listview;
    DatabaseHelper databaseHelper;
    ArrayAdapter toolArrayAdapter;
    int clickedToolId;
    Intent popupwindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workbench);

        workbench_listview = findViewById(R.id.workbench_listview);
        databaseHelper = new DatabaseHelper(WorkbenchActivity.this);

        List<ToolModel> resultsFound = databaseHelper.getCheckedOutToolsList();
        toolArrayAdapter = new ToolResultsArrayAdapter(WorkbenchActivity.this, resultsFound);
        workbench_listview.setAdapter(toolArrayAdapter);

        workbench_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToolModel clickedTool = (ToolModel) parent.getItemAtPosition(position);
                clickedToolId = clickedTool.getId();

                //calls "popupwindow" (not convinced that is what I created)
                openPopUpWindow();
                //TODO current version navigate to tool details <NOOOOOOOOOOOOOOOOOOOO>
                //this is slop garbage
                /*Intent intentDetailNav = new Intent(WorkbenchActivity.this, ToolDetailActivity.class);
                intentDetailNav.putExtra("CLICKED_TOOL_ID", clickedToolId);
                startActivity(intentDetailNav);*/

            }

            private void openPopUpWindow() {
                popupwindow = new Intent(WorkbenchActivity.this, ReturnToolPopUpWindow.class);
                popupwindow.putExtra("CLICKED_TOOL_ID", clickedToolId);
                startActivity(popupwindow);
            }
        });


        //region Bottom Nav Bar and Support
        //code to support override of bottom nav animation
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        //supports correct icon being highlighted
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.ic_home:
                        Intent intentMain = new Intent(WorkbenchActivity.this, MainActivity.class);
                        startActivity(intentMain);
                        break;

                    case R.id.ic_search:
                        Intent intentFind = new Intent(WorkbenchActivity.this, FindToolActivity.class);
                        startActivity(intentFind);
                        break;

                    case R.id.ic_add:
                        Intent intentAdd = new Intent(WorkbenchActivity.this, AddToolActivity.class);
                        startActivity(intentAdd);
                        break;
                    case R.id.ic_workbench:
                        break;
                }


                return false;
            }
        });
        //endregion

    }
}
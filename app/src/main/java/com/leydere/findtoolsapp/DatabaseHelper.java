package com.leydere.findtoolsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_TOOL_COLLECTION = "TOOL_COLLECTION";
    public static final String COLUMN_TOOL_NAME = "TOOL_NAME";
    public static final String COLUMN_LOCATION = "LOCATION";
    public static final String COLUMN_SUB_LOCATION = "SUB_LOCATION";
    public static final String COLUMN_IMAGE_PATH = "IMAGE_PATH";
    public static final String COLUMN_IS_CHECKED_OUT = "IS_CHECKED_OUT";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "toolCollection.db", null, 1);
    }

    //this is called the first time the database is accessed. needs code to create new DB
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + TABLE_TOOL_COLLECTION +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TOOL_NAME + " TEXT, " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_SUB_LOCATION + " TEXT, " +
                COLUMN_IMAGE_PATH + " TEXT, " +
                COLUMN_IS_CHECKED_OUT + " BOOL)";

        db.execSQL(createTableStatement);
    }

    //this is called if DB version # changes, prevents previous users apps from breaking when db design is changed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addRecord(ToolModel toolModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_TOOL_NAME, toolModel.getToolName());
        contentValues.put(COLUMN_LOCATION, toolModel.getLocation());
        contentValues.put(COLUMN_SUB_LOCATION, toolModel.getSubLocation());
        contentValues.put(COLUMN_IMAGE_PATH, toolModel.getImagePath());
        contentValues.put(COLUMN_IS_CHECKED_OUT, toolModel.getIsCheckedOut());

        long insert = db.insert(TABLE_TOOL_COLLECTION, null, contentValues);
        if (insert == -1) { return false; } else { return true; }

    }

    //So I just rebuilt the example method but with a string input
    public List<ToolModel> getSearchResults(String textInput){
        List<ToolModel> compiledResults = new ArrayList();
        String queryString = "SELECT * FROM " + TABLE_TOOL_COLLECTION +
                " WHERE TOOL_NAME LIKE '%" + textInput + "%'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        //In Mitch video he skips the list<> and just returns the cursor from db.rawquery
        //turns out he does something similar to the below but in his activity class instead of DBhelper


        if (cursor.moveToFirst()) {
            do {
                int toolID = cursor.getInt(0);
                String toolName = cursor.getString(1);
                String location = cursor.getString(2);
                String subLocation = cursor.getString(3);
                String imagePath = cursor.getString(4);
                boolean isCheckedOut = cursor.getInt(5) == 1 ? true: false; //ternary operator = basically an if statement that reads if result is equal to 1 return true else return false

                ToolModel newTool = new ToolModel(toolID, toolName, location,subLocation,imagePath,isCheckedOut);
                compiledResults.add(newTool);
                //TODO probably need to check if tool is checked out or not at some point

            } while (cursor.moveToNext());
        } else {
            //TODO maybe some sort of exception or feedback?
        }

        cursor.close();
        db.close();

        return compiledResults;
    }

    //names only based on search result (pretty sure I used this in SENG420-demo-branch and then removed use)
    public List<String> nameSearchResults(String textInput){
        List<String> compiledResults = new ArrayList();
        String queryString = "SELECT * FROM " + TABLE_TOOL_COLLECTION +
                " WHERE TOOL_NAME LIKE '%" + textInput + "%'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                String toolName = cursor.getString(1);
                compiledResults.add(toolName);
            } while (cursor.moveToNext());
        } else {
            //TODO maybe some sort of exception or feedback?
        }

        cursor.close();
        db.close();

        return compiledResults;
    }

    // SQL Query that returns a single ToolModel based off an int input, that is the ToolID within the db
    public ToolModel getRequestedTool(int clickedToolID){
        String queryString = "SELECT * FROM " + TABLE_TOOL_COLLECTION +
                " WHERE " + COLUMN_ID + "=" + clickedToolID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        //dev note: database stores information about tool models, not tool models
        //cont: cursor.moveToFirst() seems to be essential as it points to where in the cursor
        cursor.moveToFirst();
        int toolID = cursor.getInt(0);
        String toolName = cursor.getString(1);
        String location = cursor.getString(2);
        String subLocation = cursor.getString(3);
        String imagePath = cursor.getString(4);
        boolean isCheckedOut = cursor.getInt(5) == 1 ? true: false; //ternary operator = basically an if statement that reads if result is equal to 1 return true else return false

        ToolModel requestedTool = new ToolModel(toolID, toolName, location,subLocation,imagePath,isCheckedOut);

        cursor.close();
        db.close();

        return requestedTool;
    }

    //method that changes boolean value of isCheckedOut
    public void toggleCheckedOutStatus(int selectedToolID, boolean selectedToolStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean flipperBool = selectedToolStatus == true ? false : true;
        String queryString = "UPDATE " + TABLE_TOOL_COLLECTION +
                " SET " + COLUMN_IS_CHECKED_OUT + " = " + flipperBool +
                " WHERE " + COLUMN_ID + "=" + selectedToolID;

        db.execSQL(queryString);
    }

    //method to query database for all isCheckedOut == true records
    public List<ToolModel> getCheckedOutToolsList(){
        List<ToolModel> compiledResults = new ArrayList();
        String queryString = "SELECT * FROM " + TABLE_TOOL_COLLECTION +
                " WHERE " + COLUMN_IS_CHECKED_OUT + " = 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int toolID = cursor.getInt(0);
                String toolName = cursor.getString(1);
                String location = cursor.getString(2);
                String subLocation = cursor.getString(3);
                String imagePath = cursor.getString(4);
                boolean isCheckedOut = cursor.getInt(5) == 1 ? true: false; //ternary operator = basically an if statement that reads if result is equal to 1 return true else return false

                ToolModel newTool = new ToolModel(toolID, toolName, location,subLocation,imagePath,isCheckedOut);
                compiledResults.add(newTool);
                //TODO probably need to check if tool is checked out or not at some point

            } while (cursor.moveToNext());
        } else {
            //TODO maybe some sort of exception or feedback?
        }

        cursor.close();
        db.close();

        return compiledResults;
    }
}

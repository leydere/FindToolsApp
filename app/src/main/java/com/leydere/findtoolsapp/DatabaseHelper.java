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
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TOOL_NAME, toolModel.getToolName());
        cv.put(COLUMN_LOCATION, toolModel.getLocation());
        cv.put(COLUMN_SUB_LOCATION, toolModel.getSubLocation());
        cv.put(COLUMN_IMAGE_PATH, toolModel.getImagePath());
        cv.put(COLUMN_IS_CHECKED_OUT, toolModel.getIsCheckedOut());

        long insert = db.insert(TABLE_TOOL_COLLECTION, null, cv);
        if (insert == -1) { return false; } else { return true; }

    }

    //So I just rebuilt the example method but with a string input
    public List<ToolModel> getSearchResults(String textInput){
        List<ToolModel> compiledResults = new ArrayList();
        String queryString = "SELECT * FROM " + TABLE_TOOL_COLLECTION +
                " WHERE TOOL_NAME LIKE '%" + textInput + "%'";
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

    // this is select all function in demo; example only - no use in this app
    public List<ToolModel> getAllRecords(){

        List<ToolModel> returnList = new ArrayList();
        String queryString = "SELECT * FROM " + TABLE_TOOL_COLLECTION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            //loop through cursor (result set) and create new customer objects
            //put them into the return list
            do {
                int toolID = cursor.getInt(0);
                String toolName = cursor.getString(1);
                String location = cursor.getString(2);
                String subLocation = cursor.getString(3);
                String imagePath = cursor.getString(4);
                boolean isCheckedOut = cursor.getInt(5) == 1 ? true: false; //ternary operator = basically an if statement that reads if result is equal to 1 return true else return false

                ToolModel newTool = new ToolModel(toolID, toolName, location,subLocation,imagePath,isCheckedOut);
                returnList.add(newTool);

            } while (cursor.moveToNext());

        } else {
            // failure: do not add anything to list
        }

        //close both cursor and db when done
        cursor.close();
        db.close();

        return returnList;
    }
}

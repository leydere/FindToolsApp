package com.leydere.findtoolsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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

    public boolean addOne(ToolModel toolModel) {
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
}

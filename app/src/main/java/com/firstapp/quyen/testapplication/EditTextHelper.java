package com.firstapp.quyen.testapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EditTextHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "textViewContent.db";
    private static final String TABLE_NAME = "textviewcontent";
    public static final String TIMETRACKER_COLUMN_ID = "id";
    public static final String TIMETRACKER_COLUMN_CONTENT = "content";

    private EditTextOpenHelper openHelper;
    private SQLiteDatabase database;


    public EditTextHelper(Context context) {
        openHelper = new EditTextOpenHelper(context);
        database = openHelper.getWritableDatabase();
    }
    public void saveEditText(String edittextcontent) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TIMETRACKER_COLUMN_CONTENT, edittextcontent);
        if(isTableNull() == true){
            database.insert(TABLE_NAME, null, contentValues);
        }
        else {
            database.update(TABLE_NAME,contentValues,"id = ?", new String[] {"1"});
        }
    }
    public Cursor getEditTextList() {
        return database.rawQuery("select * from " + TABLE_NAME, null);
    }

    public boolean isTableNull(){
        String count = "SELECT COUNT(*) FROM " + TABLE_NAME;
        Cursor mcursor = database.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount>0) {
            mcursor.close();
            return false;
        }
        else{
            mcursor.close();
            return true;
        }

    }


    private class EditTextOpenHelper extends SQLiteOpenHelper {
        EditTextOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE " + TABLE_NAME + "( "
                            + TIMETRACKER_COLUMN_ID + " INTEGER PRIMARY KEY, "
                            + TIMETRACKER_COLUMN_CONTENT + " TEXT )"
            );
        }
        public void onUpgrade(SQLiteDatabase database,
                              int oldVersion, int newVersion) {
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(database);
        }
    }
}


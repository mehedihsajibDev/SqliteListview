package com.example.sajib.sqlitelistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String Database_Name = "students.db";
    private static final String Table_Name = "studentsDetails";
    private static final String Id = "id";
    private static final String Name = "name";
    private static final int version = 3;
    private Context context;
    private static final String CREATE_TABLE = " CREATE TABLE " + Table_Name + "(" + Id + " INTEGER PRIMARY KEY," + Name + " VARCHAR(250));";

    public DatabaseHelper(Context context) {
        super(context, Database_Name, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Toast.makeText(context, "oncreate is called", Toast.LENGTH_SHORT).show();
            db.execSQL(CREATE_TABLE);

        } catch (Exception e) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Toast.makeText(context, "Onupgrade is called ", Toast.LENGTH_SHORT).show();
            db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
            onCreate(db);

        } catch (Exception e) {
            Toast.makeText(context, "Error On OnUpgrademethod", Toast.LENGTH_SHORT).show();
        }
    }

    public long saveData(String id, String name) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Id, id);
        contentValues.put(Name, name);
        long result = sqLiteDatabase.insert(Table_Name, null, contentValues);
        return result;
    }

    public Cursor showallData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + Table_Name, null);
        return cursor;
    }
}

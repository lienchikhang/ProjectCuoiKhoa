package com.example.projectcuoikhoa;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_USERNAME = Ultils.DATABASE_USERNAME;
    private static final int DATABASE_VERSION = 2;


    public UserDBHelper(Context context) {
        super(context,  DATABASE_USERNAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE " + Ultils.TABLE_USER + "("
                + Ultils.COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Ultils.COLUMN_USER_NAME + " TEXT, "
                + Ultils.COLUMN_USER_PASSWORD + " TEXT, "
                + Ultils.COLUMN_USER_GENDER + " TEXT, "
                + Ultils.COLUMN_USER_EMAIL + " TEXT, "
                + Ultils.COLUMN_USER_PHONE + " TEXT, "
                + Ultils.COLUMN_USER_ROLE + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Ultils.TABLE_USER);
        onCreate(sqLiteDatabase);
    }

    public Cursor getData(String content) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(content, null);
    }

    public void dropTable() {
        SQLiteDatabase db = getReadableDatabase();
        String drop = "DROP TABLE IF EXISTS " + Ultils.TABLE_USER;
        db.execSQL(drop);
    }

    public void createTable() {
        SQLiteDatabase db = getWritableDatabase();
        String CREATE_USER_TABLE = "CREATE TABLE " + Ultils.TABLE_USER + "("
                + Ultils.COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Ultils.COLUMN_USER_NAME + " TEXT, "
                + Ultils.COLUMN_USER_PASSWORD + " TEXT, "
                + Ultils.COLUMN_USER_GENDER + " TEXT, "
                + Ultils.COLUMN_USER_EMAIL + " TEXT, "
                + Ultils.COLUMN_USER_PHONE + " TEXT, "
                + Ultils.COLUMN_USER_ROLE + " TEXT"
                + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

}
